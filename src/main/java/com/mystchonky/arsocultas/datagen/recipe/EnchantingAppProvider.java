package com.mystchonky.arsocultas.datagen.recipe;

import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeProvider;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.klikli_dev.occultism.registry.OccultismItems;
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
        recipes.add(builder()
                .withReagent(ItemsRegistry.SOURCE_GEM)
                .withPedestalItem(1, ItemsRegistry.CONJURATION_ESSENCE)
                .withPedestalItem(1, ItemsRegistry.MANIPULATION_ESSENCE)
                .withPedestalItem(1, OccultismItems.DATURA)
                .withResult(OccultismItems.SPIRIT_ATTUNED_GEM)
                .withSourceCost(500)
                .build()
        );

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
        return "Ars Ocultas - Enchanting Apparatus";
    }
}
