package org.simpmc.autocraft.commands.root;

import dev.jorel.commandapi.CommandAPICommand;
import org.simpmc.autocraft.ACPlugin;

public class AutoCraftCommand {
    public AutoCraftCommand() {
        new CommandAPICommand("autocraft")
                .withAliases("tuchetao")
                .withPermission("autocraft.use")
                .executesPlayer((player, args) -> {
                    ACPlugin.getInstance().getAutoCraftManager().toggleAutoCraft(player);
                })
                .register();
    }
}
