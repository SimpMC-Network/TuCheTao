package org.simpmc.autocraft.config.types.menu;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import org.bukkit.Material;
import org.simpmc.autocraft.config.types.menu.data.DisplayItem;
import org.simpmc.autocraft.config.types.menu.data.RoleType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration

public class RecipeListMenuConfig {

    public String title = "Chế tạo vật phẩm ( {page} / {maxPage} )";
    public List<String> layout = Arrays.asList(
            "#########",
            " OOOOOOO ",
            " OOOOOOO ",
            " OOOOOOO ",
            " OOOOOOO ",
            "###L#R###"
    );
    @Comment({"Items mapping by Char", "'O' is where the recipe will be placed"})
    public Map<Character, DisplayItem> displayItems = Map.of(
            '#', DisplayItem.builder()
                    .material(Material.GRAY_STAINED_GLASS_PANE)
                    .role(RoleType.NONE)
                    .amount(1)
                    .build(),
            'L', DisplayItem.builder()
                    .amount(1)
                    .material(Material.ARROW)
                    .role(RoleType.PREV_PAGE)
                    .name("&aQuay lại trang trước")
                    .lores(List.of(" "))
                    .build(),
            'R', DisplayItem.builder()
                    .material(Material.ARROW)
                    .amount(1)
                    .role(RoleType.NEXT_PAGE)
                    .name("&aChuyển sang trang sau")
                    .lores(List.of(" "))
                    .build()
    );

    public List<String> recipePreviewLore = List.of(
            "&8ID: {id}",
            "&e&lYêu cầu:",
            "{ingredients}",
            "&aClick để xem chi tiết công thức"
    );

    public String ingredientLore = " &7- &r{display_name} &r&7x{amount}";

}

