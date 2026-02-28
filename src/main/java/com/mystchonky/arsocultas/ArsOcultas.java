package com.mystchonky.arsocultas;

import com.mojang.logging.LogUtils;
import com.mystchonky.arsocultas.init.Registrar;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(ArsOcultas.MODID)
public class ArsOcultas {
    public static final String MODID = "ars_ocultas";

    public static final Logger LOGGER = LogUtils.getLogger();

    public ArsOcultas(IEventBus modbus, ModContainer container) {
        container.registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC, MODID + "/base-server.toml");

        Registrar.init(modbus);
    }

    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

}
