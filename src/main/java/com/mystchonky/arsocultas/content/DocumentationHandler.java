package com.mystchonky.arsocultas.content;

import com.hollingsworth.arsnouveau.api.documentation.DocCategory;
import com.hollingsworth.arsnouveau.api.documentation.ReloadDocumentationEvent;
import com.hollingsworth.arsnouveau.api.documentation.builder.DocEntryBuilder;
import com.hollingsworth.arsnouveau.api.registry.DocumentationRegistry;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.hollingsworth.arsnouveau.setup.registry.Documentation;
import com.klikli_dev.occultism.registry.OccultismItems;
import com.mystchonky.arsocultas.ArsOcultas;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT, modid = ArsOcultas.MODID)
public class DocumentationHandler {

    @SubscribeEvent
    public static void addEntries(ReloadDocumentationEvent.AddEntries event) {
        var modid = ArsOcultas.MODID;
        var category = new DocCategory(ArsOcultas.prefix(modid), OccultismItems.RITUAL_DUMMY_CUSTOM_SUMMON.toStack(), 1000);
        DocumentationRegistry.registerMainCategory(category);

        var gem = OccultismItems.SPIRIT_ATTUNED_GEM;
        Documentation.addPage(new DocEntryBuilder(modid, category, "gem")
                .withIcon(OccultismItems.SPIRIT_ATTUNED_GEM)
                .withSortNum(1)
                .withIntroPage()
                .withCraftingPages(ArsOcultas.prefix(gem.getId().getPath()), gem)
        );

        Documentation.addPage(new DocEntryBuilder(modid, category, "spirit_jar")
                .withIcon(OccultismItems.BOOK_OF_BINDING_FOLIOT)
                .withSortNum(2)
                .withIntroPage()
                .withLocalizedText()
                .withLocalizedText(OccultismItems.BRUSH)
        );

        Documentation.addPage(new DocEntryBuilder(modid, category, "transmutation")
                .withIcon(BlockRegistry.IMBUEMENT_BLOCK)
                .withSortNum(3)
                .withIntroPage()
                .withCraftingPages(ArsOcultas.prefix("gold_transmute"), Items.GOLD_INGOT)
                .withCraftingPages(ArsOcultas.prefix("silver_transmute"), OccultismItems.SILVER_INGOT)
        );
    }
}