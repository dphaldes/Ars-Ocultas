package com.mystchonky.arsocultas.datagen;

import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.datagen.recipe.ImbuementProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArsOcultas.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataProvider {

    public static String root = ArsOcultas.MODID;
    //use runData configuration to generate stuff, event.includeServer() for data, event.includeClient() for assets
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        net.minecraft.data.DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();

        gen.addProvider(event.includeServer(), new ImbuementProvider(gen));
//        gen.addProvider(event.includeServer(), new ArsProviders.GlyphProvider(gen));
//        gen.addProvider(event.includeServer(), new ArsProviders.EnchantingAppProvider(gen));

        gen.addProvider(event.includeServer(), new PatchouliProvider(gen));

        gen.addProvider(event.includeClient(), new GlyphItemModelProvider(output, event.getExistingFileHelper()));
    }

}
