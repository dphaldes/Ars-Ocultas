package com.mystchonky.arsocultas.datagen;

import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.mystchonky.arsocultas.common.init.Integrations;
import net.minecraft.data.DataGenerator;

public class PatchouliProvider extends com.hollingsworth.arsnouveau.common.datagen.PatchouliProvider {

    public PatchouliProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void addEntries() {
        for (AbstractSpellPart spell : Integrations.registeredSpells) {
            addGlyphPage(spell);
        }
    }

    /**
     * Gets a name for this provider, to use in logging.
     */
    @Override
    public String getName() {
        return "Example Patchouli Datagen";
    }
}
