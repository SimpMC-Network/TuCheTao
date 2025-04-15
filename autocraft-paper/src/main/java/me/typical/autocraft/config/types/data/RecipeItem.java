package me.typical.autocraft.config.types.data;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import dev.lone.itemsadder.api.CustomStack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RecipeItem {
    public ItemType type; // The type of item (e.g., "MMOITEM", "ITEMSADDER")
    public String name; // The name of the item (e.g., "DIAMOND_SWORD", "DIAMOND_PICKAXE")
    @Comment("MMOItems category")
    public String category;
    public int amount;

    public Component getDisplayName() {
        return getItemStack().displayName();
    }

    public ItemStack getItemStack() {
        if (type == ItemType.VANILLA) {
            return new ItemStack(Material.valueOf(name), amount);
        }
        if (type == ItemType.MMOITEMS) {
            return MMOItems.plugin.getItem(MMOItems.plugin.getTypes().get(category), name);
        }
        if (type == ItemType.ITEMSADDER) {
            CustomStack stack = CustomStack.getInstance(name);
            if (stack != null) {
                return stack.getItemStack();
            }
        }
        return new ItemStack(Material.DIRT, amount);
    }
}
