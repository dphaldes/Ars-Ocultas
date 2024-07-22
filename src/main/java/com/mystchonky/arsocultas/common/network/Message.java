package com.mystchonky.arsocultas.common.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public sealed interface Message extends CustomPacketPayload {

    non-sealed interface Server extends Message {
        void onServerReceived(ServerPlayer player);
    }

    non-sealed interface Client extends Message {
        void onClientReceived(Player player);
    }

}