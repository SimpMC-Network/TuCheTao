package org.simpmc.autocraft.registry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.simpmc.autocraft.config.ConfigManager;
import org.simpmc.autocraft.config.types.data.Recipe;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecipeRegistry {

    private List<Recipe> recipes;

    public List<Recipe> getRecipesFromConf() {
        return ConfigManager.getRecipesConfig().recipes.entrySet().stream()
                .peek(entry -> entry.getValue().setId(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public void loadRecipes() {
        this.recipes = getRecipesFromConf();
        for (Recipe recipe : recipes) {
            Bukkit.getServer().getPluginManager().addPermission(new Permission(recipe.getPermission()));
        }
    }

    public List<Recipe> getRecipesAvailable(Player player) {
        return getRecipes().stream()
                .filter(recipe -> player.hasPermission(recipe.getPermission()))
                .collect(Collectors.toList());
    }

    public List<Recipe> getRecipes() {
        if (recipes == null) {
            loadRecipes();
        }
        return recipes;
    }


}
