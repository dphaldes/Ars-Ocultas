package com.mystchonky.arsocultas.client.events;

import com.mystchonky.arsocultas.ArsOcultas;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ArsOcultas.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerOverlays(final RegisterGuiOverlaysEvent event) {
//        event.registerAboveAll("essence_hud", GuiLifeEssenceHUD.OVERLAY);
    }
}
