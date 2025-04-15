package me.typical.autocraft.config.types;

import de.exlll.configlib.Configuration;
import me.typical.autocraft.config.types.data.ItemType;
import me.typical.autocraft.config.types.data.Recipe;
import me.typical.autocraft.config.types.data.RecipeItem;

import java.util.Map;

@Configuration
public class RecipesConfig {
    public Map<String, Recipe> recipes = Map.of(
            "superdirt",
            Recipe.builder()
                    .permission("autocraft.superdirt")
                    .ingredients(Map.of(
                            "dirt", RecipeItem.builder()
                                    .type(ItemType.VANILLA)
                                    .name("dirt")
                                    .amount(64)
                                    .build()
                    ))
                    .result(
                            RecipeItem.builder()
                                    .type(ItemType.MMOITEMS)
                                    .name("superdirt")
                                    .amount(1)
                                    .build()
                    )
                    .build(),
            "superstone",
            Recipe.builder()
                    .permission("autocraft.superstone")
                    .ingredients(Map.of(
                            "stone", RecipeItem.builder()
                                    .type(ItemType.VANILLA)
                                    .name("stone")
                                    .amount(64)
                                    .build()
                    ))
                    .result(
                            RecipeItem.builder()
                                    .type(ItemType.MMOITEMS)
                                    .name("superstone")
                                    .amount(1)
                                    .build()
                    )
                    .build()
    );
}
