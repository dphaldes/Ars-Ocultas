package com.mystchonky.arsocultas.datagen;

import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.common.registrar.IntegrationRegistrar;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GlyphItemModelProvider extends ItemModelProvider {
    public GlyphItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, ArsOcultas.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        IntegrationRegistrar.registeredSpells.forEach(spell -> basicItem(spell.getRegistryName()));
    }
}
