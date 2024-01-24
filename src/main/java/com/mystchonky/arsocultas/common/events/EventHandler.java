package com.mystchonky.arsocultas.common.events;

import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.mystchonky.arsocultas.ArsOcultas;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = ArsOcultas.MODID)
public class EventHandler {
//    @SubscribeEvent
//    public static void clientTickEnd(TickEvent.ClientTickEvent event) {
//        if (event.phase == TickEvent.Phase.END) {
//            ClientInfo.ticksInGame++;
//        }
//    }

    @SubscribeEvent
    public static void attachItemCapability(AttachCapabilitiesEvent<BlockEntity> event) {
        if (event.getObject() instanceof MobJarTile tile) {
            ICapabilityProvider provider = new ICapabilityProvider() {
                @Override
                public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                    if (cap == ForgeCapabilities.ITEM_HANDLER) {
                        if (tile.getEntity() instanceof SpiritEntity spiritEntity) {
                            return spiritEntity.getCapability(cap);
                        }
                    }
                    return LazyOptional.empty();
                }
            };

            event.addCapability(ArsOcultas.prefix("extra_item_handler"), provider);
        }
    }
}
