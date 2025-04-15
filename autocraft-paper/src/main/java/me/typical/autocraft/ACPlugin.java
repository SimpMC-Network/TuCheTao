package me.typical.autocraft;

import com.tcoded.folialib.FoliaLib;
import lombok.Getter;
import me.devnatan.inventoryframework.ViewFrame;
import me.typical.autocraft.commands.CommandHandler;
import me.typical.autocraft.config.ConfigManager;
import me.typical.autocraft.registry.RecipeRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class ACPlugin extends JavaPlugin {

    @Getter
    private static ACPlugin instance;
    @Getter
    private static ConfigManager configManager;
    @Getter
    private CommandHandler commandHandler;
    @Getter
    private FoliaLib foliaLib;
    @Getter
    private ViewFrame viewFrame;

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
        foliaLib = new FoliaLib(this);
        configManager = new ConfigManager(this);
        recipeRegistry = new RecipeRegistry();
        registerInventoryFramework();


    }

    private void registerInventoryFramework() {
        viewFrame = ViewFrame.create(this)
                .with(new SelfTransferHistoryView(), new AdminTransferHistoryView())
                .disableMetrics()
                .register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
