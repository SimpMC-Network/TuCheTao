package me.typical.autocraft.config.types.data;

import de.exlll.configlib.Configuration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@Builder
public class Recipe {
    public String id;
    public String permission;
    public Map<String, RecipeItem> ingredients;
    public RecipeItem result;
}
