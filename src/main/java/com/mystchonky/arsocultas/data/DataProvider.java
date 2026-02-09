package com.mystchonky.arsocultas.data;

import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.data.client.BlockStateProvider;
import com.mystchonky.arsocultas.data.client.ItemModelProvider;
import com.mystchonky.arsocultas.data.recipe.EnchantingAppProvider;
import com.mystchonky.arsocultas.data.recipe.ImbuementProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = ArsOcultas.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataProvider {
    public static String root = ArsOcultas.MODID;

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var helper = event.getExistingFileHelper();
        var provider = event.getLookupProvider();

        //common
        event.createBlockAndItemTags(BlockTagsProvider::new, ItemTagsProvider::new);
        generator.addProvider(event.includeServer(), new ImbuementProvider(event.getLookupProvider(), generator));
        generator.addProvider(event.includeServer(), new EnchantingAppProvider(generator));
        generator.addProvider(event.includeServer(), new LootTableProvider(output, Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(BlockLootProvider::new, LootContextParamSets.BLOCK)), provider));

        // client
        generator.addProvider(event.includeClient(), new BlockStateProvider(output, helper));
        generator.addProvider(event.includeClient(), new LanguageProvider(output, "en_us"));
        generator.addProvider(event.includeClient(), new ItemModelProvider(output, helper));
//        generator.addProvider(event.includeClient(), new BookProvider(generator, provider));
    }

}
