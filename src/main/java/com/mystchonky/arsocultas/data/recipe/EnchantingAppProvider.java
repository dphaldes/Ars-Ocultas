package com.mystchonky.arsocultas.data.recipe;

import com.hollingsworth.arsnouveau.common.crafting.recipes.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeBuilder;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeProvider;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.klikli_dev.occultism.registry.OccultismItems;
import com.mystchonky.arsocultas.data.DataProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;

import java.nio.file.Path;

public class EnchantingAppProvider extends ApparatusRecipeProvider {

    public EnchantingAppProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void collectJsons(CachedOutput pOutput) {
        recipes.add(builder()
                .withReagent(ItemsRegistry.SOURCE_GEM)
                .withPedestalItem(1, ItemsRegistry.CONJURATION_ESSENCE)
                .withPedestalItem(1, ItemsRegistry.MANIPULATION_ESSENCE)
                .withPedestalItem(1, OccultismItems.DEMONS_DREAM_ESSENCE)
                .withResult(OccultismItems.SPIRIT_ATTUNED_GEM)
                .withSourceCost(500)
                .build()
        );

        for (ApparatusRecipeBuilder.RecipeWrapper<? extends EnchantingApparatusRecipe> wrapper : recipes) {
            if (wrapper != null) {
                Path path = getRecipePath(output, wrapper.id().getPath());
                saveStable(pOutput, wrapper.serialize(), path);
            }
        }

    }

    protected static Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/" + DataProvider.root + "/recipe/" + str + ".json");
    }

    @Override
    public String getName() {
        return "Ars Ocultas - Enchanting Apparatus";
    }
}
