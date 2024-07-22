package com.mystchonky.arsocultas.common.registrar;

import com.mystchonky.arsocultas.common.network.MessageRegistrar;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

public class Registrar {

    public static void init(IEventBus modbus) {
        ItemRegistrar.register(modbus);
        MenuTypeRegistrar.register(modbus);
        IntegrationRegistrar.init();
        LangRegistrar.register();

        modbus.addListener(Registrar::commonSetup);
        modbus.addListener(Registrar::clientSetup);
        modbus.addListener(MessageRegistrar::registerMessages);
    }

    private static void commonSetup(final FMLCommonSetupEvent event) {
        IntegrationRegistrar.postInit();
    }

    private static void clientSetup(final FMLClientSetupEvent event) {

    }
}
