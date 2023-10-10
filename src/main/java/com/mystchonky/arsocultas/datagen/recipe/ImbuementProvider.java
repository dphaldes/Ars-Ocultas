package com.mystchonky.arsocultas.datagen.recipe;

import com.hollingsworth.arsnouveau.common.crafting.recipes.ImbuementRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ImbuementRecipeProvider;
import com.mystchonky.arsocultas.datagen.DataProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;

import java.nio.file.Path;

public class ImbuementProvider extends ImbuementRecipeProvider {


    public ImbuementProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void collectJsons(CachedOutput pOutput) {

            /*
            recipes.add(new ImbuementRecipe("example_focus", Ingredient.of(Items.AMETHYST_SHARD), new ItemStack(ItemsRegistry.SUMMONING_FOCUS, 1), 5000)
                    .withPedestalItem(ItemsRegistry.WILDEN_TRIBUTE)
            );
            */

        for (ImbuementRecipe g : recipes) {
            Path path = getRecipePath(output, g.getId().getPath());
            saveStable(pOutput, g.asRecipe(), path);
        }

    }

    protected Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/" + DataProvider.root + "/recipes/" + str + ".json");
    }

    @Override
    public String getName() {
        return "Ars Ocultas - Imbuement";
    }

}
