package com.mystchonky.arsocultas.datagen.recipe;

import com.hollingsworth.arsnouveau.common.crafting.recipes.GlyphRecipe;
import com.hollingsworth.arsnouveau.common.datagen.GlyphRecipeProvider;
import com.mystchonky.arsocultas.datagen.DataProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;

import java.nio.file.Path;

import static com.hollingsworth.arsnouveau.setup.registry.RegistryHelper.getRegistryName;

public class GlyphProvider extends GlyphRecipeProvider {

    public GlyphProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void collectJsons(CachedOutput pOutput) {
        for (GlyphRecipe recipe : recipes) {
            Path path = getScribeGlyphPath(output, recipe.output.getItem());
            saveStable(pOutput, recipe.asRecipe(), path);
        }

    }

    protected static Path getScribeGlyphPath(Path pathIn, Item glyph) {
        return pathIn.resolve("data/" + DataProvider.root + "/recipes/" + getRegistryName(glyph).getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Example Glyph Recipes";
    }
}
