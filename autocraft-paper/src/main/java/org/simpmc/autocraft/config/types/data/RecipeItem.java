package org.simpmc.autocraft.config.types.data;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import dev.lone.itemsadder.api.CustomStack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@SuppressWarnings("deprecated")
public class RecipeItem {
    public ItemType type; // The type of item (e.g., "MMOITEM", "ITEMSADDER")
    public String name; // The name of the item (e.g., "DIAMOND_SWORD", "DIAMOND_PICKAXE")
    @Comment("MMOItems category")
    public String category;
    public int amount;

    public String getDisplayName() {
        return getItemStackSample().getItemMeta().getDisplayName();
    }

    public ItemStack getItemStackSample() {
        if (type == ItemType.VANILLA) {
            Material material = Material.valueOf(name.toUpperCase());
            return new ItemStack(material, 1);
        }
        if (type == ItemType.MMOITEMS) {
            ItemStack item = MMOItems.plugin.getItem(MMOItems.plugin.getTypes().get(category), name.toUpperCase());
            if (item != null) {
                return item;
            }
        }
        if (type == ItemType.ITEMSADDER) {
            CustomStack stack = CustomStack.getInstance(name);
            if (stack != null) {
                return stack.getItemStack();
            }
        }
        return new ItemStack(Material.DIRT, 1);
    }

    public ItemStack[] getItemStackArray() {
        return createItemStacks(type, name, category, amount);
    }

    private ItemStack[] createItemStacks(ItemType type, String name, String category, int amount) {
        if (type == ItemType.VANILLA) {
            Material material = Material.valueOf(name.toUpperCase());
            return splitIntoStacks(new ItemStack(material), amount);
        }
        if (type == ItemType.MMOITEMS) {
            ItemStack item = MMOItems.plugin.getItem(MMOItems.plugin.getTypes().get(category), name.toUpperCase());
            if (item != null) {
                return splitIntoStacks(item, amount);
            }
        }
        if (type == ItemType.ITEMSADDER) {
            CustomStack stack = CustomStack.getInstance(name);
            if (stack != null) {
                return splitIntoStacks(stack.getItemStack(), amount);
            }
        }
        return splitIntoStacks(new ItemStack(Material.DIRT), amount);
    }

    private ItemStack[] splitIntoStacks(ItemStack baseItem, int amount) {
        int fullStacks = amount / 64;
        int remainder = amount % 64;

        ItemStack[] stacks = new ItemStack[fullStacks + (remainder > 0 ? 1 : 0)];
        for (int i = 0; i < fullStacks; i++) {
            ItemStack stack = baseItem.clone();
            stack.setAmount(64);
            stacks[i] = stack;
        }
        if (remainder > 0) {
            ItemStack stack = baseItem.clone();
            stack.setAmount(remainder);
            stacks[fullStacks] = stack;
        }
        return stacks;
    }

    public ItemStack getPreviewItemStack(List<String> lore) {
        ItemStack originalItem = getItemStackSample();

        ItemStack previewItem = new ItemStack(originalItem.getType(), 1);
        ItemMeta meta = previewItem.getItemMeta();

        meta.setDisplayName(originalItem.getItemMeta().getDisplayName());
        meta.setLore(lore);
        meta.setItemModel(originalItem.getItemMeta().getItemModel());
        meta.setCustomModelData(originalItem.getItemMeta().hasCustomModelData() ? originalItem.getItemMeta().getCustomModelData() : null);

        // if original item has any enchant, apply luck 1 enchant on preview item with hidden enchantments
        // for enchant glint
        if (!originalItem.getEnchantments().isEmpty()) {
            meta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        previewItem.setItemMeta(meta);

        return previewItem;
    }
}
