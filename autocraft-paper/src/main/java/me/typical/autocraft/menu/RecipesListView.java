package me.typical.autocraft.menu;

import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.ViewConfigBuilder;
import me.devnatan.inventoryframework.component.Pagination;
import me.devnatan.inventoryframework.context.RenderContext;
import me.devnatan.inventoryframework.state.State;
import me.typical.autocraft.ACPlugin;
import me.typical.autocraft.config.ConfigManager;
import me.typical.autocraft.config.types.menu.RecipeListMenuConfig;
import me.typical.autocraft.config.types.menu.data.DisplayItem;
import me.typical.autocraft.config.types.menu.data.RoleType;
import me.typical.autocraft.util.MessageUtil;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipesListView extends View {

    private final State<Pagination> paginationState = buildLazyPaginationState(context -> {
        return ACPlugin.getInstance().getRecipeRegistry().getRecipes();
    }).elementFactory((ctx, bukkitItemComponentBuilder, i, recipes) -> {
        RecipeListMenuConfig config = ConfigManager.getRecipeListMenuConfig();
        List<String> processedLore = config.recipePreviewLore.stream()
                .flatMap(line -> {
                    // Perform the basic replacements first.
                    String processedLine = MessageUtil.trans(line)
                            .replace("{id}", String.valueOf(recipes.getId()));

                    // Check if this line contains the placeholder for items.
                    if (processedLine.contains("{items}")) {
                        // For each item, replace the placeholder with a dash-prefixed entry.
                        return recipes.getIngredients().entrySet().stream()
                                .map(itemEntry -> processedLine.replace("{items}",


                                        ConfigManager.getRecipeListMenuConfig().ingredientLore
                                                .replace("{diplay_name}", String.valueOf(itemEntry.getValue().getDisplayName())) // TODO: make getting item stack method in the RecipeItem.class
                                                .replace("{amount}", String.valueOf(itemEntry.getValue().getAmount()))));
                    } else {
                        // Otherwise, return the processed line as a single-element stream.
                        return Stream.of(processedLine);
                    }
                })
                .collect(Collectors.toList());

        ItemStack item = CXItem.builder()
                .name(processedDisplayName)
                .material(config.displayItem.getMaterial())
                .lores(processedLore)
                .amount(config.displayItem.getAmount())
                .customModelData(config.displayItem.getCustomModelData())
                .build().getItemStack();

        bukkitItemComponentBuilder.withItem(item);
    }).build();


    @Override
    public void onInit(ViewConfigBuilder config) {
        config.title(ConfigManager.getRecipeListMenuConfig().title);
        config.cancelInteractions();
        config.layout(ConfigManager.getRecipeListMenuConfig().layout.toArray(new String[0]));
    }

    @Override
    public void onFirstRender(@NotNull RenderContext render) {
        Pagination pagination = paginationState.get(render);

        Map<Character, DisplayItem> displayedItems = ConfigManager.getRecipeListMenuConfig().displayItems;

        for (Map.Entry<Character, DisplayItem> entry : displayedItems.entrySet()) {
            if (entry.getValue().getRole() == RoleType.NONE) {
                render.layoutSlot(entry.getKey(), entry.getValue().toItemStack());
            }

        }
    }
}
