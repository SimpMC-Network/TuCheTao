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
public class RecipeMenuConfig {

    public String title = "{itemName} &r&7( {page} / {maxPage} )";
    public List<String> layout = Arrays.asList(
            "#########",
            "#OOOOOOO#",
            "#OOOOOOO#",
            "#OOOOOOO#",
            "#########",
            "B#L#S#R##"
    );
    @Comment({"Items mapping by Char", "'O' is where the recipe will be placed"})
    public Map<Character, DisplayItem> displayItems = Map.of(
            'B', DisplayItem.builder()
                    .material(Material.BARRIER)
                    .role(RoleType.BACK)
                    .amount(1)
                    .name("&cQuay lại")
                    .lores(List.of("Về menu chính"))
                    .build(),
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
    public Character resultKey = 'S';

}
