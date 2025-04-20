package org.simpmc.autocraft.commands.sub.admin;

import dev.jorel.commandapi.CommandAPICommand;
import org.simpmc.autocraft.ACPlugin;
import org.simpmc.autocraft.config.ConfigManager;
import org.simpmc.autocraft.util.MessageUtil;

public class ReloadCommand {
    public ReloadCommand() {
        new CommandAPICommand("tuchetaoreload")
                .withPermission("tuchetao.admin.reload")
                .executesPlayer((player, args) -> {
                    ACPlugin plugin = ACPlugin.getInstance();
                    plugin.getFoliaLib().getScheduler().runAsync(task -> {
                        ACPlugin.getConfigManager().reloadConfig();
                        MessageUtil.sendMessage(player, ConfigManager.getMessageConfig().configReloaded);
                    });
                });
    }
}
