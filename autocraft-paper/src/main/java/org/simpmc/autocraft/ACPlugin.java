package org.simpmc.autocraft;

import com.tcoded.folialib.FoliaLib;
import lombok.Getter;
import me.devnatan.inventoryframework.ViewFrame;
import org.bukkit.plugin.java.JavaPlugin;
import org.simpmc.autocraft.bstats.Metrics;
import org.simpmc.autocraft.commands.CommandHandler;
import org.simpmc.autocraft.config.ConfigManager;
import org.simpmc.autocraft.manager.AutoCraftManager;
import org.simpmc.autocraft.menu.RecipeView;
import org.simpmc.autocraft.menu.RecipesListView;
import org.simpmc.autocraft.registry.RecipeRegistry;

import java.io.File;
import java.io.IOException;

public final class ACPlugin extends JavaPlugin {

    @Getter
    private static ACPlugin instance;
    @Getter
    private static ConfigManager configManager;
    private final boolean dev = false; // change this on release
    @Getter
    private CommandHandler commandHandler;
    @Getter
    private FoliaLib foliaLib;
    @Getter
    private ViewFrame viewFrame;
    @Getter
    private AutoCraftManager autoCraftManager;
    @Getter
    private RecipeRegistry recipeRegistry;

    @Override
    public void onLoad() {
        commandHandler = new CommandHandler(this);
        commandHandler.onLoad();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        foliaLib = new FoliaLib(this);
        new Metrics(this, 25553);

        // Reset config
        if (dev) {
            try {
                File autocraftFolder = new File(getDataFolder().getParentFile(), getPluginMeta().getName());
                if (deleteDirectory(autocraftFolder)) {
                    getLogger().info("Successfully deleted plugins/autocraft.");
                } else {
                    getLogger().warning("plugins/autocraft did not exist or could not be deleted.");
                }
            } catch (IOException e) {
                getLogger().severe("Error deleting plugins/autocraft: " + e.getMessage());
            }
        }
        configManager = new ConfigManager(this);
        recipeRegistry = new RecipeRegistry();
        recipeRegistry.loadRecipes();
        autoCraftManager = new AutoCraftManager(this);
        commandHandler.onEnable();
        registerInventoryFramework();
    }

    private boolean deleteDirectory(File dir) throws IOException {
        if (!dir.exists()) {
            return true;
        }

        File[] entries = dir.listFiles();
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    deleteDirectory(entry);
                } else {
                    if (!entry.delete()) {
                        throw new IOException("Failed to delete file: " + entry.getAbsolutePath());
                    }
                }
            }
        }

        if (!dir.delete()) {
            throw new IOException("Failed to delete directory: " + dir.getAbsolutePath());
        }

        return true;
    }

    private void registerInventoryFramework() {
        viewFrame = ViewFrame.create(this)
                .with(new RecipesListView(), new RecipeView())
                .disableMetrics()
                .register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
        commandHandler.onDisable();
    }
}
