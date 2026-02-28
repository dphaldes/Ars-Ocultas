package com.mystchonky.arsocultas.data.client;

import com.hollingsworth.arsnouveau.common.datagen.PatchouliProvider;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.ApparatusPage;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.PatchouliBuilder;
import com.klikli_dev.occultism.registry.OccultismItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;

import java.util.concurrent.CompletableFuture;

public class BookProvider extends PatchouliProvider {
    public BookProvider(DataGenerator generatorIn, CompletableFuture<HolderLookup.Provider> registries) {
        super(generatorIn, registries);
    }

    @Override
    public void addEntries() {

        addPage(new PatchouliBuilder(RESOURCES, OccultismItems.SPIRIT_ATTUNED_GEM)
                        .withTextPage("Alternate recipe when diamonds are scarce.")
                        .withPage(new ApparatusPage(OccultismItems.SPIRIT_ATTUNED_GEM)),
                getPath(RESOURCES, "spirit_attuned_gem"));
    }
}
