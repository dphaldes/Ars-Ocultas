package com.mystchonky.arsocultas;

import com.hollingsworth.arsnouveau.setup.proxy.ClientProxy;
import com.hollingsworth.arsnouveau.setup.proxy.IProxy;
import com.hollingsworth.arsnouveau.setup.proxy.ServerProxy;
import com.klikli_dev.occultism.client.gui.spirit.SpiritGui;
import com.klikli_dev.occultism.client.gui.spirit.SpiritTransporterGui;
import com.klikli_dev.occultism.common.container.spirit.SpiritContainer;
import com.mystchonky.arsocultas.common.config.BaseConfig;
import com.mystchonky.arsocultas.common.init.ArsOcultasContainers;
import com.mystchonky.arsocultas.common.init.ArsOcultasItems;
import com.mystchonky.arsocultas.common.init.ArsOcultasLang;
import com.mystchonky.arsocultas.common.init.Integrations;
import com.mystchonky.arsocultas.common.network.Networking;
import com.tterrag.registrate.Registrate;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
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
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new); //TODO: Change to our own

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
        ArsOcultasItems.register();
        ArsOcultasContainers.CONTAINERS.register(modbus);
        Integrations.init();
        ArsOcultasLang.register();

        modbus.addListener(this::commonSetup);
        modbus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        Integrations.postInit();
        Networking.registerMessages();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(()-> {
            MenuScreens.register(ArsOcultasContainers.SPIRIT_WRAPPER.get(), SpiritGui<SpiritContainer>::new);
            MenuScreens.register(ArsOcultasContainers.SPIRIT_TRANSPORT_WRAPPER.get(), SpiritTransporterGui::new);
        });
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

}
