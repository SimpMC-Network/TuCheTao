package me.typical.autocraft.config.types.menu;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import me.typical.autocraft.config.types.menu.data.DisplayItem;
import me.typical.autocraft.config.types.menu.data.RoleType;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration

public class RecipeListMenuConfig {

    public String title = "Recipe list ({page})";
    public List<String> layout = Arrays.asList(
            "#########",
            " OOOOOOO ",
            " OOOOOOO ",
            " OOOOOOO ",
            " OOOOOOO",
            "###L#R###"
    );
    @Comment({"Items mapping by Char", "'O' is where the recipe will be placed"})
    public Map<Character, DisplayItem> displayItems = Map.of(
            '#', DisplayItem.builder()
                    .material(Material.GRAY_STAINED_GLASS_PANE)
                    .role(RoleType.NONE)
                    .build(),
            'L', DisplayItem.builder()
                    .material(Material.ARROW)
                    .role(RoleType.PREV_PAGE)
                    .name("&cPrevious page")
                    .lores(List.of("&7Click to go to the previous page"))
                    .build(),
            'R', DisplayItem.builder()
                    .material(Material.ARROW)
                    .role(RoleType.NEXT_PAGE)
                    .name("&cNext page")
                    .lores(List.of("&7Click to go to the next page"))
                    .build()
    );

    public List<String> recipePreviewLore = List.of(
            "&7ID: {id}",
            "&eRequires:",
            "{ingredients}",
            "&6Click to view recipe"
    );

    public String ingredientLore = "&7{display_name} x{amount}";

}

