package com.mystchonky.arsocultas.datagen.recipe;

import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeProvider;
import com.mystchonky.arsocultas.datagen.DataProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;

import java.nio.file.Path;

public class EnchantingAppProvider extends ApparatusRecipeProvider {

    public EnchantingAppProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void collectJsons(CachedOutput pOutput) {
        //example of an apparatus recipe
        /*
        recipes.add(builder()
                .withReagent(ItemsRegistry.SOURCE_GEM)
                .withPedestalItem(4, Recipes.SOURCE_GEM)
                .withResult(ItemsRegistry.BUCKET_OF_SOURCE)
                .withSource(100)
                .build()
        );
         */

        for (EnchantingApparatusRecipe g : recipes) {
            if (g != null) {
                Path path = getRecipePath(output, g.getId().getPath());
                saveStable(pOutput, g.asRecipe(), path);
            }
        }

    }

    protected static Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/" + DataProvider.root + "/recipes/" + str + ".json");
    }

    @Override
    public String getName() {
        return "Example Apparatus";
    }
}
