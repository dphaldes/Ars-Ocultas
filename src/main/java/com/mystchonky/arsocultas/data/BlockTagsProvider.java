package com.mystchonky.arsocultas.data;

import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.init.BlockRegistrar;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagsProvider extends net.neoforged.neoforge.common.data.BlockTagsProvider {

    public BlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ArsOcultas.MODID, null);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockRegistrar.ALTAR.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockRegistrar.ALTAR.get());

    }
}
