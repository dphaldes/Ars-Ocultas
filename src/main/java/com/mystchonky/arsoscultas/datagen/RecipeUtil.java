package com.mystchonky.arsoscultas.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RecipeUtil {
    /**
     * Register a recipe wrapped in a {@link ConditionalRecipe}. Useful for mod compat recipes, as those recipes
     * would otherwise crash if the other mod is not installed
     *
     * @param condition     The condition for this recipe
     * @param recipeBuilder The recipe (pass reference to {@code XyzRecipeBuilder.build(Consumer, ResourceLocation)})
     * @param consumer      The recipe consumer given by {@link RecipeProvider#buildCraftingRecipes(Consumer)}
     * @param id            The recipe id
     */
    protected static void conditionalRecipe(ICondition condition, BiConsumer<Consumer<FinishedRecipe>, ResourceLocation> recipeBuilder, Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ConditionalRecipe.builder().addCondition(condition).addRecipe(callable -> recipeBuilder.accept(callable, id)).build(consumer, id);
    }

    protected static void modCompatRecipe(String modid, BiConsumer<Consumer<FinishedRecipe>, ResourceLocation> recipeBuilder, Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        conditionalRecipe(new ModLoadedCondition(modid), recipeBuilder, consumer, id);
    }

    protected static JsonObject modCompatGlyphRecipe(String modid, JsonElement recipe) {
        JsonObject json = new JsonObject();
        JsonArray conds = new JsonArray();
        conds.add(CraftingHelper.serialize(new ModLoadedCondition(modid)));
        json.add("conditions", conds);
        recipe.getAsJsonObject().entrySet().stream().forEach((entry) -> json.add(entry.getKey(), entry.getValue()));
        return json;
    }
}
