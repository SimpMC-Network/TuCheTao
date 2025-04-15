package me.typical.autocraft.config.types.menu;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import me.typical.autocraft.config.types.menu.data.DisplayItem;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class RecipeMenuConfig {

    public String title = "Recipe for {itemName}";
    public List<String> layout = Arrays.asList(
            "#########",
            "####OOOO#",
            "#S##OOOO#",
            "####OOOO#",
            "#########",
            "####B####"
    );
    @Comment({"Items mapping by Char", "'O' is where the recipe will be placed"})
    public Map<Character, DisplayItem> displayItems = Map.of(
            'B', DisplayItem.builder()
                    .material(Material.BARRIER)
                    .name("Back")
                    .lores(List.of("Click to back"))
                    .build()
    );
    public Character resultKey = 'S';

}
