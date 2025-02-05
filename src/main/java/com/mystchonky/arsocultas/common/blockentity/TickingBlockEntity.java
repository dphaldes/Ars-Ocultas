package com.mystchonky.arsocultas.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface TickingBlockEntity {
    void tick(final Level level, final BlockPos pos, final BlockState state);
}