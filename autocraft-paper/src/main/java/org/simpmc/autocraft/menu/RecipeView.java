package org.simpmc.autocraft.menu;

import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewConfigBuilder;
import me.devnatan.inventoryframework.component.Pagination;
import me.devnatan.inventoryframework.context.OpenContext;
import me.devnatan.inventoryframework.context.RenderContext;
import me.devnatan.inventoryframework.context.SlotContext;
import me.devnatan.inventoryframework.state.State;
import org.jetbrains.annotations.NotNull;
import org.simpmc.autocraft.config.ConfigManager;
import org.simpmc.autocraft.config.types.data.Recipe;
import org.simpmc.autocraft.config.types.menu.data.DisplayItem;
import org.simpmc.autocraft.config.types.menu.data.RoleType;
import org.simpmc.autocraft.util.MessageUtil;

import java.util.Map;

public class RecipeView extends View {

    private final State<Pagination> paginationState = buildLazyPaginationState(context -> {
        if (context.getInitialData() == null) {
            return null;
        }
        Recipe recipe = (Recipe) context.getInitialData();

        return recipe.getAllIngredients();

    }).elementFactory((ctx, bukkitItemComponentBuilder, i, recipeItemStack) -> {
        bukkitItemComponentBuilder.withItem(recipeItemStack);
    }).build();


    @Override
    public void onInit(ViewConfigBuilder config) {
        config.cancelInteractions();
        config.layout(ConfigManager.getRecipeMenuConfig().layout.toArray(new String[0]));
    }

    @Override
    public void onOpen(OpenContext open) {
        Pagination pagination = paginationState.get(open);
        String title = MessageUtil.trans(ConfigManager.getRecipeMenuConfig().title)
                .replace("{itemName}", open.getInitialData() == null ? "Unknown" : ((Recipe) open.getInitialData()).getResult().getDisplayName())
                .replace("{page}", String.valueOf(pagination.currentPage()))
                .replace("{maxPage}", String.valueOf(pagination.lastPage()));
        open.modifyConfig().title(title);
    }

    // expect initital data is passin a recipe id
    @Override
    public void onFirstRender(@NotNull RenderContext render) {
        if (render.getInitialData() == null) {
            return;
        }
        Pagination pagination = paginationState.get(render);
        Recipe recipe = (Recipe) render.getInitialData();
        Map<Character, DisplayItem> displayedItems = ConfigManager.getRecipeMenuConfig().displayItems;
        for (Map.Entry<Character, DisplayItem> entry : displayedItems.entrySet()) {
            DisplayItem item = entry.getValue();
            if (item.getRole() == RoleType.NONE) {
                render.layoutSlot(entry.getKey(), item.getItemStack());
            }
            if (item.getRole() == RoleType.BACK) {
                render.layoutSlot(entry.getKey(), item.getItemStack())
                        .onClick(SlotContext::back);
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
        render.layoutSlot(ConfigManager.getRecipeMenuConfig().resultKey, recipe.getResult().getItemStackSample());
    }
}
