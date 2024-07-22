package com.mystchonky.arsocultas.datagen;


import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.common.registrar.LangRegistrar;
import net.minecraft.data.PackOutput;

public class LanguageProvider extends net.neoforged.neoforge.common.data.LanguageProvider {
    public LanguageProvider(PackOutput output, String locale) {
        super(output, ArsOcultas.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        LangRegistrar.entries.forEach(this::add);
    }
}
