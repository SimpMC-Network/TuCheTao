package me.typical.autocraft.commands.root;

import dev.jorel.commandapi.CommandAPICommand;
import me.typical.autocraft.ACPlugin;
import me.typical.autocraft.menu.RecipesListView;

public class ViewRecipeListCommand {
    public ViewRecipeListCommand() {
        new CommandAPICommand("xemcongthuc")
                .withPermission("autocraft.viewrecipe")
                .executesPlayer((player, args) -> {
                    ACPlugin.getInstance().getViewFrame().open(RecipesListView.class, player);

                })
                .register();
    }
}
