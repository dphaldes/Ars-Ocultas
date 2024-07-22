package com.mystchonky.arsocultas.common.network;

import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.mystchonky.arsocultas.ArsOcultas;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public record MessageSpiritSetFilter(boolean isBlacklistFilter, BlockPos blockPos) implements Message.Server {

    public static final Type<MessageSpiritSetFilter> TYPE = new Type<>(ArsOcultas.prefix("sync_arsenal"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageSpiritSetFilter> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.BOOL, MessageSpiritSetFilter::isBlacklistFilter, BlockPos.STREAM_CODEC, MessageSpiritSetFilter::blockPos, MessageSpiritSetFilter::new);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void onServerReceived(ServerPlayer player) {
        if (player.level().getBlockEntity(this.blockPos) instanceof MobJarTile jar) {
            if (jar.getEntity() instanceof SpiritEntity spirit) {
                spirit.setFilterBlacklist(this.isBlacklistFilter);
            }
        }
    }


}
