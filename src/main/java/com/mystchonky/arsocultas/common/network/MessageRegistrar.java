package com.mystchonky.arsocultas.common.network;

import com.mystchonky.arsocultas.ArsOcultas;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class MessageRegistrar {
    public static void registerMessages(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(ArsOcultas.MODID);
        registrar.playToServer(MessageSpiritSetFilter.TYPE, MessageSpiritSetFilter.STREAM_CODEC, MessageHandler::server);
    }
}
