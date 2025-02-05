package com.mystchonky.arsocultas.common.registrar;

import com.hollingsworth.arsnouveau.ArsNouveau;
import com.klikli_dev.occultism.Occultism;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class Registrar {

    public static void init(IEventBus modbus) {
        ItemRegistrar.register(modbus);
        BlockRegistrar.register(modbus);
        BlockEntityRegistrar.register(modbus);
        MenuTypeRegistrar.register(modbus);
        IntegrationRegistrar.init();
        LangRegistrar.register();

        modbus.addListener(Registrar::commonSetup);
        modbus.addListener(Registrar::clientSetup);
        modbus.addListener(Registrar::buildContents);
    }

    private static void commonSetup(final FMLCommonSetupEvent event) {
        IntegrationRegistrar.postInit();
    }

    private static void clientSetup(final FMLClientSetupEvent event) {

    }

    public static void buildContents(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == ResourceKey.create(Registries.CREATIVE_MODE_TAB, ArsNouveau.prefix("general"))) {
            event.accept(BlockRegistrar.ALTAR.get());
        }

        if (event.getTabKey() == ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(Occultism.MODID, "occultism"))) {
            event.accept(BlockRegistrar.ALTAR.get());
        }
    }
}
