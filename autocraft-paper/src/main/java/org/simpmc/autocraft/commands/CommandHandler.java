package org.simpmc.autocraft.commands;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import org.simpmc.autocraft.ACPlugin;
import org.simpmc.autocraft.commands.root.AutoCraftCommand;
import org.simpmc.autocraft.commands.root.ViewRecipeListCommand;
import org.simpmc.autocraft.commands.sub.admin.ReloadCommand;

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
        new AutoCraftCommand();
    }

    public void onDisable() {
        CommandAPI.onDisable();
    }

}
