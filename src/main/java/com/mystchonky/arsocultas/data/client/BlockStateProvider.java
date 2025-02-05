package com.mystchonky.arsocultas.data.client;


import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.common.registrar.BlockRegistrar;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateProvider extends net.neoforged.neoforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ArsOcultas.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        simpleBlock(BlockRegistrar.ALTAR.get(),
                models().cubeTop(BlockRegistrar.ALTAR.getRegisteredName(),
                        ArsOcultas.prefix("block/altar_side"),
                        ArsOcultas.prefix("block/altar_top")));
    }
}