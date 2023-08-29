package com.mystchonky.arsocultas.common.events;

import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.client.ClientInfo;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArsOcultas.MODID)
public class EventHandler {
    @SubscribeEvent
    public static void clientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ClientInfo.ticksInGame++;
        }
    }

//    @SubscribeEvent
//    public static void updateAugmentsforMimic(FMLLoadCompleteEvent event) {
//        ArsNouveauAPI.getInstance()
//                .getGlyphItemMap()
//                .values()
//                .stream()
//                .filter(spell -> spell instanceof AbstractAugment)
//                .forEach(spell -> {
//                    ((AbstractAugment) spell).getCompatibleAugments().add(AugmentMimic.INSTANCE);
//                });
//    }
}
