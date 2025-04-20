package org.simpmc.autocraft.config.types;

import de.exlll.configlib.Configuration;
import org.simpmc.autocraft.config.types.data.ItemType;
import org.simpmc.autocraft.config.types.data.Recipe;
import org.simpmc.autocraft.config.types.data.RecipeItem;

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
                                    .category("MATERIAL")
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
                                    .category("MATERIAL")
                                    .name("superstone")
                                    .amount(1)
                                    .build()
                    )
                    .build(),
            "supersuperstone",
            Recipe.builder()
                    .permission("autocraft.supersuperstone")
                    .ingredients(Map.of(
                            "superstone", RecipeItem.builder()
                                    .type(ItemType.MMOITEMS)
                                    .category("MATERIAL")
                                    .name("superstone")
                                    .amount(64)
                                    .build(),
                            "superdirt", RecipeItem.builder()
                                    .type(ItemType.MMOITEMS)
                                    .name("superdirt")
                                    .category("MATERIAL")
                                    .amount(128)
                                    .build()
                    ))
                    .result(
                            RecipeItem.builder()
                                    .type(ItemType.MMOITEMS)
                                    .category("MATERIAL")
                                    .name("supersuperstone")
                                    .amount(1)
                                    .build()
                    )
                    .build()
    );
}
