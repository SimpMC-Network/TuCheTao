package org.simpmc.autocraft.config.types.data;

import de.exlll.configlib.Configuration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.simpmc.autocraft.config.ConfigManager;
import org.simpmc.autocraft.util.MessageUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@Builder
public class Recipe {
    public String id;
    public String permission;
    public Map<String, RecipeItem> ingredients;
    public RecipeItem result;

    public List<String> getMenuLore() {
        List<String> lore = ConfigManager.getRecipeListMenuConfig().recipePreviewLore;
        String ingredientLore = MessageUtil.trans(ConfigManager.getRecipeListMenuConfig().ingredientLore);
        List<String> processedLore = new ArrayList<>();
        for (String line : lore) {
            line = MessageUtil.trans(line);
            if (line.contains("{id}")) {
                processedLore.add(line.replace("{id}", this.getId()));
                continue;
            }
            if (line.contains("{ingredients}")) {
                for (RecipeItem recipeItem : this.getIngredients().values()) {
                    processedLore.add(ingredientLore.replace("{display_name}", recipeItem.getDisplayName()).replace("{amount}", String.valueOf(recipeItem.getAmount())));
                }
                continue;
            }
            processedLore.add(line);
        }
        return processedLore;
    }


    // get all ingredient needed as list<itemstack>
    public List<ItemStack> getAllIngredients() {
        List<ItemStack> allIngredients = new ArrayList<>();
        for (RecipeItem recipeItem : this.getIngredients().values()) {
            ItemStack[] itemStacks = recipeItem.getItemStackArray();
            Collections.addAll(allIngredients, itemStacks);
        }
        return allIngredients;
    }
}
