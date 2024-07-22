package com.mystchonky.arsocultas.common.registrar;

import com.mystchonky.arsocultas.ArsOcultas;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.Map;

public class LangRegistrar {
    public static final Map<String, String> entries = new HashMap<>();

    public static void register() {
        IntegrationRegistrar.registeredSpells.forEach(spell -> {
            addTranslation(ArsOcultas.MODID + ".glyph_name." + spell.getRegistryName().getPath(), spell.getName());
            addTranslation(ArsOcultas.MODID + ".glyph_desc." + spell.getRegistryName().getPath(), spell.getBookDescription());
        });
    }

    private static LangEntry addTranslation(String key, String translation) {
        entries.put(key, translation);
        return new LangEntry(key, Component.translatable(key));
    }

    private static LangEntry addTranslation(String type, String key, String translation) {
        return addTranslation(type + "." + ArsOcultas.MODID + "." + key, translation);
    }

    public record LangEntry(String key, Component component) {
    }
}
