package com.mystchonky.arsoscura.common.init;

import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.familiar.AbstractFamiliarHolder;
import com.hollingsworth.arsnouveau.api.mob_jar.JarBehaviorRegistry;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.klikli_dev.occultism.registry.OccultismEntities;
import com.mystchonky.arsoscura.common.mob_jar.SpiritBehaviour;

import java.util.ArrayList;
import java.util.List;

public class Integrations {

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>();
    public static List<AbstractFamiliarHolder> registeredFamiliars = new ArrayList<>();

    public static void init() {
        registerGlyphs();
    }

    public static void postInit() {
        registerSounds();
        registerJarBehaviours();
    }

    public static void registerGlyphs() {
    }


    public static void registerSounds() {
    }

    public static void registerSpellPart(AbstractSpellPart spellPart) {
        ArsNouveauAPI.getInstance().registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }

    public static void registerFamiliarHolders(AbstractFamiliarHolder familiarHolder) {
        ArsNouveauAPI.getInstance().registerFamiliar(familiarHolder);
        registeredFamiliars.add(familiarHolder);
    }

    public static void registerJarBehaviours() {
        JarBehaviorRegistry.register(OccultismEntities.FOLIOT.get(), new SpiritBehaviour<>());
        JarBehaviorRegistry.register(OccultismEntities.DJINNI.get(), new SpiritBehaviour<>());
        JarBehaviorRegistry.register(OccultismEntities.AFRIT.get(), new SpiritBehaviour<>());
        JarBehaviorRegistry.register(OccultismEntities.MARID.get(), new SpiritBehaviour<>());
    }

}
