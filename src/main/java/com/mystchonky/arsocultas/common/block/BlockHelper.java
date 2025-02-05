package com.mystchonky.arsocultas.common.block;

import com.mystchonky.arsocultas.common.blockentity.TickingBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;

import javax.annotation.Nullable;

public class BlockHelper {

    @SuppressWarnings("unchecked")
    @Nullable
    private static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTicker(
            BlockEntityType<A> typeA, BlockEntityType<E> typeB, BlockEntityTicker<? super E> ticker) {
        if (typeB == typeA)
            return (BlockEntityTicker<A>) ticker;
        return null;
    }

    @Nullable
    public static <E extends BlockEntity & TickingBlockEntity, A extends BlockEntity> BlockEntityTicker<A> getTicker(
            BlockEntityType<A> typeA, BlockEntityType<E> typeB) {
        return createTicker(typeA, typeB, (level, blockPos, state, blockEntity) -> {
            blockEntity.tick(level, blockPos, state);
        });
    }

}
