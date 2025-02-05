package com.mystchonky.arsocultas.data;

import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.data.client.BlockStateProvider;
import com.mystchonky.arsocultas.data.client.ItemModelProvider;
import com.mystchonky.arsocultas.data.recipe.EnchantingAppProvider;
import com.mystchonky.arsocultas.data.recipe.ImbuementProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ArsOcultas.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataProvider {
    public static String root = ArsOcultas.MODID;

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var helper = event.getExistingFileHelper();

        generator.addProvider(event.includeServer(), new ImbuementProvider(event.getLookupProvider(), generator));
        generator.addProvider(event.includeServer(), new EnchantingAppProvider(generator));

        generator.addProvider(event.includeClient(), new LanguageProvider(output, "en_us"));
        generator.addProvider(event.includeClient(), new BlockStateProvider(output, helper));
        generator.addProvider(event.includeClient(), new ItemModelProvider(output, helper));
    }

}
