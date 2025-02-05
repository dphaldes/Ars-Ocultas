package com.mystchonky.arsocultas.common.registrar;

import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.common.block.AltarBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistrar {
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ArsOcultas.MODID);
    private static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(ArsOcultas.MODID);

    public static final DeferredBlock<AltarBlock> ALTAR = BLOCKS.registerBlock("altar",
            AltarBlock::new, BlockBehaviour.Properties.of()
    );
    private static final DeferredItem<BlockItem> ALTAR_ITEM = BLOCK_ITEMS.registerSimpleBlockItem(ALTAR);

    public static void register(IEventBus modbus) {
        BLOCKS.register(modbus);
        BLOCK_ITEMS.register(modbus);
    }
}
