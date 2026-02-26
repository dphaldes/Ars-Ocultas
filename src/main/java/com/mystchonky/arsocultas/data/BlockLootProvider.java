package com.mystchonky.arsocultas.data;

import com.mystchonky.arsocultas.init.BlockRegistrar;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Set;

public class BlockLootProvider extends BlockLootSubProvider {

    protected BlockLootProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, provider);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(BlockRegistrar.ALTAR.value());
    }

    @Override
    protected void generate() {
        dropSelf(BlockRegistrar.ALTAR.get());
    }

}
