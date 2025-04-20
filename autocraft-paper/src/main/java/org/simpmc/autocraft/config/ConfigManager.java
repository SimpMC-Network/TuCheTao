package org.simpmc.autocraft.config;

import de.exlll.configlib.ConfigLib;
import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.simpmc.autocraft.ACPlugin;
import org.simpmc.autocraft.config.types.MainConfig;
import org.simpmc.autocraft.config.types.MessageConfig;
import org.simpmc.autocraft.config.types.RecipesConfig;
import org.simpmc.autocraft.config.types.menu.RecipeListMenuConfig;
import org.simpmc.autocraft.config.types.menu.RecipeMenuConfig;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class ConfigManager {

    @Getter
    private static MessageConfig messageConfig;
    @Getter
    private static MainConfig mainConfig;
    @Getter
    private static RecipeListMenuConfig recipeListMenuConfig;
    @Getter
    private static RecipeMenuConfig recipeMenuConfig;
    @Getter
    private static RecipesConfig recipesConfig;

    private final ACPlugin plugin;
    @Getter
    private final HashMap<String, Component> miniMessageCache = new HashMap<>();

    private Path messageFile;
    private Path mainConfigFile;
    private Path recipesFile;

    private Path recipeMenuFile;
    private Path recipeListMenuFile;

    public ConfigManager(ACPlugin plugin) {
        this.plugin = plugin;
        register();
    }

    private File getDataFolder() {
        return plugin.getDataFolder();
    }

    private void register() {
        YamlConfigurationProperties properties = ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder().build();

        plugin.getLogger().info("Loading config");
        messageFile = Paths.get(getDataFolder() + "/" + "message.yml");
        mainConfigFile = Paths.get(getDataFolder() + "/" + "config.yml");
        recipesFile = Paths.get(getDataFolder() + "/" + "recipes.yml");

        recipeMenuFile = Paths.get(getDataFolder() + "/menus/" + "recipe_menu.yml");
        recipeListMenuFile = Paths.get(getDataFolder() + "/menus/" + "recipelist_menu.yml");

        if (!getDataFolder().exists()) {
            YamlConfigurations.save(messageFile, MessageConfig.class, new MessageConfig());
            YamlConfigurations.save(mainConfigFile, MainConfig.class, new MainConfig());
            YamlConfigurations.save(recipesFile, RecipesConfig.class, new RecipesConfig());


            YamlConfigurations.save(recipeListMenuFile, RecipeListMenuConfig.class, new RecipeListMenuConfig());
            YamlConfigurations.save(recipeMenuFile, RecipeMenuConfig.class, new RecipeMenuConfig());
        } else {
            YamlConfigurations.update(messageFile, MessageConfig.class);
            YamlConfigurations.update(mainConfigFile, MainConfig.class);
            YamlConfigurations.update(recipesFile, RecipesConfig.class);

            YamlConfigurations.update(recipeListMenuFile, RecipeListMenuConfig.class);
            YamlConfigurations.update(recipeMenuFile, RecipeMenuConfig.class);
        }

        messageConfig = YamlConfigurations.load(messageFile, MessageConfig.class, properties);
        mainConfig = YamlConfigurations.load(mainConfigFile, MainConfig.class, properties);
        recipesConfig = YamlConfigurations.load(recipesFile, RecipesConfig.class, properties);

        recipeListMenuConfig = YamlConfigurations.load(recipeListMenuFile, RecipeListMenuConfig.class, properties);
        recipeMenuConfig = YamlConfigurations.load(recipeMenuFile, RecipeMenuConfig.class, properties);
        plugin.getLogger().info("Loaded config successfully");
    }

    public void reloadConfig() {
        messageConfig = YamlConfigurations.load(messageFile, MessageConfig.class);
        mainConfig = YamlConfigurations.load(mainConfigFile, MainConfig.class);
        recipesConfig = YamlConfigurations.load(recipesFile, RecipesConfig.class);

        recipeMenuConfig = YamlConfigurations.load(recipeMenuFile, RecipeMenuConfig.class);
        recipeListMenuConfig = YamlConfigurations.load(recipeListMenuFile, RecipeListMenuConfig.class);
        miniMessageCache.clear();
    }

    public Object getParsedStringValue(String key) {
        try {
            // Get the field from the Message class reflectively
            var field = messageConfig.getClass().getField(key);
            Object value = field.get(messageConfig);

            // If the value is a String, parse it and cache it as a Component
            if (value instanceof String stringValue) {

                // Check if the Component is already cached
                if (miniMessageCache.containsKey(stringValue)) {
                    return miniMessageCache.get(stringValue);
                }

                // Parse and cache the Component if not already cached
                Component parsedComponent = MiniMessage.miniMessage().deserialize(stringValue);
                miniMessageCache.put(stringValue, parsedComponent);
                return parsedComponent;
            }

            // If the value is not a String, just return it directly (handle ints, etc.)
            return value;

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null; // Handle this appropriately (e.g., log an error, return default value)
        }
    }

}
