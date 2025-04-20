package org.simpmc.autocraft.manager;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.simpmc.autocraft.ACPlugin;
import org.simpmc.autocraft.config.ConfigManager;
import org.simpmc.autocraft.config.types.data.Recipe;
import org.simpmc.autocraft.config.types.data.RecipeItem;
import org.simpmc.autocraft.registry.RecipeRegistry;
import org.simpmc.autocraft.util.InventoryUtils;
import org.simpmc.autocraft.util.MessageUtil;

import java.util.*;

public class AutoCraftManager {
    // Logic taken from simple-skygencore by Cortez_Romeo
    private final HashSet<String> processingPlayers = new HashSet<>();
    private final HashSet<String> enabledPlayers = new HashSet<>();
    private final RecipeRegistry recipeRegistry;
    private final ACPlugin plugin;

    public AutoCraftManager(ACPlugin plugin) {
        this.plugin = plugin;
        this.recipeRegistry = plugin.getRecipeRegistry();
    }

    public void toggleAutoCraft(Player player) {
        if (isPlayerAutoCrafting(player)) {
            if (stopAutoCraft(player)) {
                player.sendMessage(MessageUtil.trans(ConfigManager.getMessageConfig().toggleAutoCraftOff));
            }
            return;
        }
        startAutoCraft(player);
        player.sendMessage(MessageUtil.trans(ConfigManager.getMessageConfig().toggleAutoCraftOn));


    }

    public void startAutoCraft(Player player) {
        if (processingPlayers.contains(player.getName())) {
            player.sendMessage(MessageUtil.trans(ConfigManager.getMessageConfig().autocraftToggleWait));
            return;
        }

        if (!isPlayerAutoCrafting(player)) {
            sendActionBar(player, ConfigManager.getMessageConfig().autocraftToggleWait);
            processingPlayers.add(player.getName());

            plugin.getFoliaLib().getScheduler().runAtEntityLater(player, task -> {
                double cooldown = getCooldown(player);
                sendActionBar(player, ConfigManager.getMessageConfig().autocraftWaiting.replace("{time}", String.valueOf(cooldown / 20)));
                processAutoCraft(player);
                processingPlayers.remove(player.getName());
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 2);
            }, 20 * 2);

            sendStartMessages(player);
        }
    }

    public boolean stopAutoCraft(Player player) {
        if (isPlayerAutoCrafting(player)) {
            enabledPlayers.remove(player.getName());
            return true;
        } else {
            player.sendMessage(MessageUtil.trans(ConfigManager.getMessageConfig().autocraftToggleWait));
            return false;
        }
    }

    private void processAutoCraft(Player player) {
        if (!isPlayerAutoCrafting(player)) {
            enabledPlayers.add(player.getName());
        }

        int cooldown = getCooldown(player);

        plugin.getFoliaLib().getScheduler().runAtEntityTimer(player, task -> {
            if (!isPlayerAutoCrafting(player)) {
                task.cancel();
                return;
            }
            if (isPlayerAutoCrafting(player)) {
                if (cooldown != getCooldown(player)) {
                    task.cancel();
                    processAutoCraft(player);
                    return;
                }

                executeAutoCraft(player);
            }
        }, 0L, cooldown);
    }

    private void executeAutoCraft(Player player) {
        if (!isPlayerAutoCrafting(player)) return;

        for (Recipe recipe : recipeRegistry.getRecipesAvailable(player)) {

            Map<ItemStack, Integer> craftableItems = new HashMap<>();
            List<ItemStack> requiredItems = new ArrayList<>();

            for (Map.Entry<String, RecipeItem> entry : recipe.ingredients.entrySet()) {
                RecipeItem ingredient = entry.getValue();
                ItemStack item = createItemStack(ingredient);

                if (item == null) {
                    Bukkit.getConsoleSender().sendMessage("[AutoCraft] Invalid item: " + ingredient.name);
                    continue;
                }

                int playerAmount = InventoryUtils.getPlayerAmount(player, item);
                int requiredAmount = ingredient.amount;

                if (playerAmount >= requiredAmount) {
                    item.setAmount(requiredAmount);
                    craftableItems.put(item, playerAmount / requiredAmount);
                    requiredItems.add(item);
                }
            }

            if (!requiredItems.isEmpty()) {
                processRecipe(player, requiredItems, craftableItems, recipe.result);
            }
        }
    }

    private void processRecipe(Player player, List<ItemStack> requiredItems,
                               Map<ItemStack, Integer> craftableItems, RecipeItem result) {
        boolean fastCraft = craftableItems.size() == 1;
        ItemStack fastCraftItem = null;

        for (ItemStack item : requiredItems) {
            if (fastCraft) {
                InventoryUtils.removeItems(player, item, (long) item.getAmount() * craftableItems.get(item));
                fastCraftItem = item;
            } else {
                InventoryUtils.removeItems(player, item, item.getAmount());
            }
        }

        ItemStack resultItem = createItemStack(result);
        if (resultItem == null) {
            Bukkit.getConsoleSender().sendMessage("[AutoCraft] Invalid result item: " + result.name);
            return;
        }

        if (fastCraft) {
            resultItem.setAmount(craftableItems.get(fastCraftItem));
        }

        player.getInventory().addItem(resultItem);
    }

    private ItemStack createItemStack(RecipeItem recipeItem) {
        return recipeItem.getItemStackSample();
    }

    private int getCooldown(Player player) {
        if (player.isOp()) return 10; // 0.5 seconds

        for (Integer delay : ConfigManager.getMainConfig().craftdelay) {
            if (player.hasPermission("tuchetao.timecraft." + delay)) {
                return delay * 20; // Convert seconds to ticks
            }
        }

        return 300; // Default to 15 seconds
    }

    private boolean isPlayerAutoCrafting(Player player) {
        return enabledPlayers.contains(player.getName());
    }

    private void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                TextComponent.fromLegacyText(MessageUtil.trans(message)));
    }

    private void sendStartMessages(Player player) {
        double cooldown = getCooldown(player);
        for (String str : plugin.getConfig().getStringList("message.autocompressors-on")) {
            str = str.replace("%time%", String.valueOf(cooldown));
            player.sendMessage(MessageUtil.trans(str));
        }
    }
}