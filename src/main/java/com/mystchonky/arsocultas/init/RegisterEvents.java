package com.mystchonky.arsocultas.init;

import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.klikli_dev.occultism.client.gui.spirit.SpiritGui;
import com.klikli_dev.occultism.common.container.spirit.SpiritContainer;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.content.spirit_jar.MessageSpiritSetFilter;
import com.mystchonky.arsocultas.content.spirit_jar.SpiritScreenWrapper;
import com.mystchonky.arsocultas.foundation.network.MessageHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@EventBusSubscriber(modid = ArsOcultas.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegisterEvents {

    @SubscribeEvent
    public static void attachItemCapability(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                BlockRegistry.MOB_JAR_TILE.get(),
                (blockEntity, side) -> {
                    if (blockEntity.getEntity() instanceof SpiritEntity spiritEntity) {
                        return spiritEntity.getCapability(Capabilities.ItemHandler.ENTITY);
                    }
                    return null;
                }
        );
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(MenuTypeRegistrar.SPIRIT_WRAPPER.get(), SpiritGui<SpiritContainer>::new);
        event.register(MenuTypeRegistrar.SPIRIT_TRANSPORT_WRAPPER.get(), SpiritScreenWrapper::wrapTransporterGui);
    }

    @SubscribeEvent
    public static void registerMessages(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar(ArsOcultas.MODID);
        registrar.playToServer(MessageSpiritSetFilter.TYPE, MessageSpiritSetFilter.STREAM_CODEC, MessageHandler::server);
    }
}
