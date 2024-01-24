package com.mystchonky.arsocultas.common.network;

import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.klikli_dev.occultism.network.MessageBase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class MessageSpiritSetFilter extends MessageBase {

    public boolean isBlacklistFilter;
    public BlockPos blockPos;

    public MessageSpiritSetFilter(FriendlyByteBuf buf) {
        this.decode(buf);
    }

    public MessageSpiritSetFilter(boolean isBlacklistFilter, BlockPos blockPos) {
        this.isBlacklistFilter = isBlacklistFilter;
        this.blockPos = blockPos;
    }

    @Override
    public void onServerReceived(MinecraftServer minecraftServer, ServerPlayer player, NetworkEvent.Context context) {
        if (player.level().getBlockEntity(this.blockPos) instanceof MobJarTile jar) {
            if (jar.getEntity() instanceof SpiritEntity spirit) {
                spirit.setFilterBlacklist(this.isBlacklistFilter);
            }
        }
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeBoolean(this.isBlacklistFilter);
        buf.writeBlockPos(this.blockPos);
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        this.isBlacklistFilter = buf.readBoolean();
        this.blockPos = buf.readBlockPos();

    }
}
