package com.mystchonky.arsocultas.datagen;

import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.common.init.Integrations;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GlyphItemModelProvider extends ItemModelProvider {
    public GlyphItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ArsOcultas.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Integrations.registeredSpells.forEach(spell -> basicItem(spell.getRegistryName()));
    }
}
