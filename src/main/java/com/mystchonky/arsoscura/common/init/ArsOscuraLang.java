package com.mystchonky.arsoscura.common.init;

import com.mystchonky.arsoscura.ArsOscura;
import com.tterrag.registrate.Registrate;

public class ArsOscuraLang {

    private static final Registrate REGISTRATE = ArsOscura.registrate();

    public static void register() {
        ArsNouveauIntegration.registeredSpells.forEach(spell -> {
            REGISTRATE.addRawLang(ArsOscura.MODID + ".glyph_name." + spell.getRegistryName().getPath(), spell.getName());
            REGISTRATE.addRawLang(ArsOscura.MODID + ".glyph_desc." + spell.getRegistryName().getPath(), spell.getBookDescription());
        });
    }
}
