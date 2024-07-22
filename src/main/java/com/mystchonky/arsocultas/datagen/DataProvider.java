package com.mystchonky.arsocultas.datagen;

import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.datagen.recipe.EnchantingAppProvider;
import com.mystchonky.arsocultas.datagen.recipe.ImbuementProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ArsOcultas.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataProvider {
    public static String root = ArsOcultas.MODID;

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        net.minecraft.data.DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();

        gen.addProvider(event.includeServer(), new ImbuementProvider(event.getLookupProvider(), gen));
        gen.addProvider(event.includeServer(), new EnchantingAppProvider(gen));

        gen.addProvider(event.includeClient(), new LanguageProvider(output, "en_us"));
    }

}
