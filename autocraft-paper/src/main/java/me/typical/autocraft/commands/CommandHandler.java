package me.typical.autocraft.commands;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import me.typical.autocraft.ACPlugin;
import me.typical.autocraft.commands.root.ViewRecipeListCommand;
import me.typical.autocraft.commands.sub.admin.ReloadCommand;

public class CommandHandler {
    private final ACPlugin plugin;

    public CommandHandler(ACPlugin plugin) {
        this.plugin = plugin;
    }

    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(plugin).shouldHookPaperReload(true).silentLogs(true));
    }

    public void onEnable() {
        CommandAPI.onEnable();
        new ReloadCommand();
        new ViewRecipeListCommand();
    }

    public void onDisable() {
        CommandAPI.onDisable();
    }

}
