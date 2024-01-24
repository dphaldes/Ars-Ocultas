package com.mystchonky.arsocultas.common.registrar;

import com.mystchonky.arsocultas.ArsOcultas;
import com.tterrag.registrate.Registrate;

public class LangRegistrar {

    private static final Registrate REGISTRATE = ArsOcultas.registrate();

    public static void register() {
        IntegrationRegistrar.registeredSpells.forEach(spell -> {
            REGISTRATE.addRawLang(ArsOcultas.MODID + ".glyph_name." + spell.getRegistryName().getPath(), spell.getName());
            REGISTRATE.addRawLang(ArsOcultas.MODID + ".glyph_desc." + spell.getRegistryName().getPath(), spell.getBookDescription());
        });
    }
}
