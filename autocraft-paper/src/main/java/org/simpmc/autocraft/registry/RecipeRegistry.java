package org.simpmc.autocraft.registry;

import org.simpmc.autocraft.config.ConfigManager;
import org.simpmc.autocraft.config.types.data.Recipe;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecipeRegistry {

    public List<Recipe> getRecipes() {
        return ConfigManager.getRecipesConfig().recipes.entrySet().stream()
                .peek(entry -> entry.getValue().setId(entry.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
