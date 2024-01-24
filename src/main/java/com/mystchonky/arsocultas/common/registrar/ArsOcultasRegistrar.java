package com.mystchonky.arsocultas.common.registrar;

import com.klikli_dev.occultism.client.gui.spirit.SpiritGui;
import com.klikli_dev.occultism.client.gui.spirit.SpiritTransporterGui;
import com.klikli_dev.occultism.common.container.spirit.SpiritContainer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ArsOcultasRegistrar {

    public static void init(IEventBus modbus) {
        ItemRegistrar.register();
        MenuTypeRegistrar.CONTAINERS.register(modbus);
        IntegrationRegistrar.init();
        LangRegistrar.register();
    }

    public static void commonSetup(final FMLCommonSetupEvent event) {
        IntegrationRegistrar.postInit();
    }

    public static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(MenuTypeRegistrar.SPIRIT_WRAPPER.get(), SpiritGui<SpiritContainer>::new);
            MenuScreens.register(MenuTypeRegistrar.SPIRIT_TRANSPORT_WRAPPER.get(), SpiritTransporterGui::new);
        });
    }
}
