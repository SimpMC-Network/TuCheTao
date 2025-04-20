package org.simpmc.autocraft.menu;

import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewConfigBuilder;
import me.devnatan.inventoryframework.component.Pagination;
import me.devnatan.inventoryframework.context.OpenContext;
import me.devnatan.inventoryframework.context.RenderContext;
import me.devnatan.inventoryframework.state.State;
import org.jetbrains.annotations.NotNull;
import org.simpmc.autocraft.ACPlugin;
import org.simpmc.autocraft.config.ConfigManager;
import org.simpmc.autocraft.config.types.menu.data.DisplayItem;
import org.simpmc.autocraft.config.types.menu.data.RoleType;
import org.simpmc.autocraft.util.MessageUtil;

import java.util.List;
import java.util.Map;

public class RecipesListView extends View {

    private final State<Pagination> paginationState = buildLazyPaginationState(context -> {
        return ACPlugin.getInstance().getRecipeRegistry().getRecipesAvailable(context.getPlayer());
    }).elementFactory((ctx, bukkitItemComponentBuilder, i, recipe) -> {
        List<String> processedLore = recipe.getMenuLore();

        bukkitItemComponentBuilder.withItem(recipe.getResult().getPreviewItemStack(processedLore))
                .onClick(click -> click.openForPlayer(RecipeView.class, recipe));
    }).build();


    @Override
    public void onInit(ViewConfigBuilder config) {
        config.cancelInteractions();
        config.layout(ConfigManager.getRecipeListMenuConfig().layout.toArray(new String[0]));
    }

    @Override
    public void onOpen(@NotNull OpenContext open) {
        Pagination pagination = paginationState.get(open);
        String title = MessageUtil.trans(ConfigManager.getRecipeListMenuConfig().title)
                .replace("{page}", String.valueOf(pagination.currentPage()))
                .replace("{maxPage}", String.valueOf(pagination.lastPage()));
        open.modifyConfig().title(title);
    }

    @Override
    public void onFirstRender(@NotNull RenderContext render) {
        Pagination pagination = paginationState.get(render);

        Map<Character, DisplayItem> displayedItems = ConfigManager.getRecipeListMenuConfig().displayItems;

        for (Map.Entry<Character, DisplayItem> entry : displayedItems.entrySet()) {
            DisplayItem item = entry.getValue();
            if (item.getRole() == RoleType.NONE) {
                render.layoutSlot(entry.getKey(), entry.getValue().getItemStack());
            }
            if (item.getRole() == RoleType.PREV_PAGE) {
                render.layoutSlot(entry.getKey(), item.getItemStack())
                        .updateOnStateChange(paginationState)
                        .onClick((ctx) -> {
                            paginationState.get(ctx).back();
                        });
            }
            if (item.getRole() == RoleType.NEXT_PAGE) {
                render.layoutSlot(entry.getKey(), item.getItemStack())
                        .updateOnStateChange(paginationState)
                        .onClick((ctx) -> {
                            paginationState.get(ctx).advance();
                        });
            }
        }
    }
}
