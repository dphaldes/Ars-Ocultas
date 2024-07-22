package com.mystchonky.arsocultas.common.network;


import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class MessageHandler {

    public static <T extends CustomPacketPayload> void bidirectional(T payload, IPayloadContext context) {
        if (context.flow().isServerbound()) {
            server(payload, context);
        } else if (context.flow().isClientbound()) {
            client(payload, context);
        }
    }

    public static <T extends CustomPacketPayload> void server(T payload, IPayloadContext context) {
        if (payload instanceof Message.Server message)
            message.onServerReceived((ServerPlayer) context.player());
    }

    public static <T extends CustomPacketPayload> void client(T payload, IPayloadContext context) {
        Client.handle(payload, context);
    }

    private static class Client {

        public static <T extends CustomPacketPayload> void handle(T payload, IPayloadContext context) {
            if (payload instanceof Message.Client message)
                message.onClientReceived(context.player());
        }

    }
}
