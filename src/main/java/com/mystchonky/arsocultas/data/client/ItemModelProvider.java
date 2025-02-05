package com.mystchonky.arsocultas.data.client;

import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.common.registrar.BlockRegistrar;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ArsOcultas.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(BlockRegistrar.ALTAR.get());
    }

}
