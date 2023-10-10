package com.mystchonky.arsocultas.datagen.recipe;

import com.hollingsworth.arsnouveau.common.crafting.recipes.ImbuementRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ImbuementRecipeProvider;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.klikli_dev.occultism.registry.OccultismItems;
import com.mystchonky.arsocultas.datagen.DataProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.nio.file.Path;

public class ImbuementProvider extends ImbuementRecipeProvider {


    public ImbuementProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void collectJsons(CachedOutput pOutput) {

        recipes.add(new ImbuementRecipe("silver_transmute", Ingredient.of(Items.GOLD_INGOT), new ItemStack(OccultismItems.SILVER_INGOT.get(), 1), 2000)
                .withPedestalItem(ItemsRegistry.MANIPULATION_ESSENCE)
                .withPedestalItem(OccultismItems.DATURA)
                .withPedestalItem(OccultismItems.SPIRIT_ATTUNED_GEM)
            );

        recipes.add(new ImbuementRecipe("gold_transmute", Ingredient.of(OccultismItems.SILVER_INGOT.get()), new ItemStack(Items.GOLD_INGOT, 1), 2000)
                .withPedestalItem(ItemsRegistry.MANIPULATION_ESSENCE)
                .withPedestalItem(ItemsRegistry.MAGE_BLOOM)
                .withPedestalItem(OccultismItems.SPIRIT_ATTUNED_GEM)
        );

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
