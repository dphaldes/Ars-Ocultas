package com.mystchonky.arsocultas.data;


import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.init.BlockRegistrar;
import com.mystchonky.arsocultas.init.LangRegistrar;
import net.minecraft.data.PackOutput;

public class LanguageProvider extends net.neoforged.neoforge.common.data.LanguageProvider {
    public LanguageProvider(PackOutput output, String locale) {
        super(output, ArsOcultas.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        LangRegistrar.entries.forEach(this::add);
        add(BlockRegistrar.ALTAR.get(), "Sacrificial Altar");


        // Documentation

        var modid = ArsOcultas.MODID;
        add(modid + ".section" + "." + modid, "Ars Ocultas");
        add(modid + ".page" + ".gem", "Spirit Attuned Gem");
        add(modid + ".page1" + ".gem", "Alternate recipe when diamonds are scarce.");
        add(modid + ".page" + ".spirit_jar", "Spirit Jars");
        add(modid + ".page1" + ".spirit_jar", "Occultism Spirits can work inside Containment Jars. They maintain their original behaviour and do not decay making them perfect from automations! Spirit Jar will automatically pickup any item in 7x7 centered around it that matches spirit's filter (for crushing spirits, it will automatically pickup any ore that it can process).");
        add(modid + ".page2" + ".spirit_jar", "Items can also be pushed into the containment jar using a hopper or starbuncles (any pipe should also work!). Players can also right-click any item directly on the jar. Spirits in Jars exhibit their default behaviour, this means that a Crusher Spirit will automatically process and fling the output in one of directions");
        add(modid + ".page3" + ".spirit_jar", "Janitor Spirits inside jars have a special property. They automatically try to pickup items nearby and push to inventory next to it. This makes automation super easy!");
        add(modid + ".page" + ".transmutation", "Transmutation");
        add(modid + ".page1" + ".transmutation", "You can convert surplus amounts of silver into gold and vice-vera!");
    }
}
