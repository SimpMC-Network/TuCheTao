package org.simpmc.autocraft.commands.root;

import dev.jorel.commandapi.CommandAPICommand;
import org.simpmc.autocraft.ACPlugin;
import org.simpmc.autocraft.menu.RecipesListView;

public class ViewRecipeListCommand {
    public ViewRecipeListCommand() {
        new CommandAPICommand("xemcongthuc")
                .withPermission("tuchetao.xemcongthuc")
                .executesPlayer((player, args) -> {
                    ACPlugin.getInstance().getViewFrame().open(RecipesListView.class, player);

                })
                .register();
    }
}
