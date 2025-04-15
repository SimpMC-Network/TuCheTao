package me.typical.autocraft.menu;

import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewConfigBuilder;
import me.devnatan.inventoryframework.context.RenderContext;
import me.typical.autocraft.config.ConfigManager;
import org.jetbrains.annotations.NotNull;

public class RecipeView extends View {

    @Override
    public void onInit(ViewConfigBuilder config) {
        config.title(ConfigManager.getRecipeMenuConfig().title);
        config.cancelInteractions();
        config.layout(ConfigManager.getRecipeMenuConfig().layout.toArray(new String[0]));
    }

    @Override
    public void onFirstRender(@NotNull RenderContext render) {


    }
}
