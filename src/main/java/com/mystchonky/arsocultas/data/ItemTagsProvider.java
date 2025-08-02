package com.mystchonky.arsocultas.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ItemTagsProvider extends net.minecraft.data.tags.ItemTagsProvider {
    public ItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
