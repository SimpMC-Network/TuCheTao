package org.simpmc.autocraft.commands.root;

import dev.jorel.commandapi.CommandAPICommand;
import org.simpmc.autocraft.ACPlugin;
import org.simpmc.autocraft.config.ConfigManager;

public class AutoCraftCommand {
    public AutoCraftCommand() {
        new CommandAPICommand("autocraft")
                .withAliases("tuchetao")
                .withPermission("autocraft.autocraft")
                .executesPlayer((player, args) -> {
                    if (ACPlugin.getInstance().getAutoCraftManager().toggleAutoCraft(player)) { // enable
                        player.sendMessage(ConfigManager.getMessageConfig().toggleAutoCraftOn);
                    } else { // disable
                        player.sendMessage(ConfigManager.getMessageConfig().toggleAutoCraftOff);
                    }
                })
                .register();
    }
}
