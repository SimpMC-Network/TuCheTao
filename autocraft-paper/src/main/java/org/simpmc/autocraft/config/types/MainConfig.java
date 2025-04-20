package org.simpmc.autocraft.config.types;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;

import java.util.List;

@Configuration
public class MainConfig {
    public boolean debug = true;
    @Comment("The delay between crafting items in seconds")
    public List<Integer> craftdelay = List.of(5, 100, 200, 300);
}
