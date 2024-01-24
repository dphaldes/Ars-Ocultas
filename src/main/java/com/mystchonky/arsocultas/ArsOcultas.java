package com.mystchonky.arsocultas;

import com.mystchonky.arsocultas.common.config.BaseConfig;
import com.mystchonky.arsocultas.common.network.Networking;
import com.mystchonky.arsocultas.common.registrar.ArsOcultasRegistrar;
import com.tterrag.registrate.Registrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ArsOcultas.MODID)
public class ArsOcultas {
    public static final String MODID = "ars_ocultas";

    private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MODID));
    public static final Logger LOGGER = LogManager.getLogger();

    public static Registrate registrate() {
        return REGISTRATE.get();
    }

    public ArsOcultas() {
        // Ensure the config subdirectory is present.
        try {
            Files.createDirectories(FMLPaths.CONFIGDIR.get().resolve(MODID));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Register config files
        var ctx = ModLoadingContext.get();
        ctx.registerConfig(ModConfig.Type.COMMON, BaseConfig.COMMON_SPEC, MODID + "/base-common.toml");
        ctx.registerConfig(ModConfig.Type.CLIENT, BaseConfig.CLIENT_SPEC, MODID + "/base-client.toml");

        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();

        ArsOcultasRegistrar.init(modbus);
        modbus.addListener(this::commonSetup);
        modbus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ArsOcultasRegistrar.commonSetup(event);
        Networking.registerMessages();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ArsOcultasRegistrar.clientSetup(event);
    }

}
