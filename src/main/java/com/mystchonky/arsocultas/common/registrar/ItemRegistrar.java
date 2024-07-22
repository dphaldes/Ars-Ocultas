package com.mystchonky.arsocultas.common.registrar;

import com.mystchonky.arsocultas.ArsOcultas;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistrar {
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ArsOcultas.MODID);

    public static void register(IEventBus modbus) {
        ITEMS.register(modbus);
    }
}
