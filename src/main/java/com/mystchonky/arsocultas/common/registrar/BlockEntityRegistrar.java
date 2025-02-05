package com.mystchonky.arsocultas.common.registrar;

import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.common.blockentity.AltarBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockEntityRegistrar {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ArsOcultas.MODID);

    public static final Supplier<BlockEntityType<AltarBlockEntity>> ALTAR = BLOCK_ENTITY_TYPES.register(
            "altar",
            () -> BlockEntityType.Builder.of(
                    AltarBlockEntity::new,
                    BlockRegistrar.ALTAR.get()
            ).build(null)
    );

    public static void register(IEventBus bus) {
        BLOCK_ENTITY_TYPES.register(bus);
    }
}
