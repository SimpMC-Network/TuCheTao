package me.typical.autocraft.commands.sub.admin;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandArguments;
import me.typical.autocraft.ACPlugin;
import me.typical.autocraft.config.ConfigManager;
import me.typical.autocraft.util.MessageUtil;
import org.bukkit.entity.Player;

public class ReloadCommand {
    public ReloadCommand() {
        new CommandAPICommand("reload")
                .withPermission("autocraft.admin.reload")
                .executesPlayer(ReloadCommand::execute);
    }

    public static void execute(Player player, CommandArguments args) {
        ACPlugin plugin = ACPlugin.getInstance();
        plugin.getFoliaLib().getScheduler().runAsync(task -> {
            ACPlugin.getConfigManager().reloadConfig();
            MessageUtil.sendMessage(player, ConfigManager.getMessageConfig().configReloaded);
        });
    }
}
