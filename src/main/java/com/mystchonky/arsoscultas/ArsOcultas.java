package com.mystchonky.arsoscultas;

import com.hollingsworth.arsnouveau.setup.ClientProxy;
import com.hollingsworth.arsnouveau.setup.IProxy;
import com.hollingsworth.arsnouveau.setup.ServerProxy;
import com.mystchonky.arsoscultas.common.config.BaseConfig;
import com.mystchonky.arsoscultas.common.init.ArsOcultasItems;
import com.mystchonky.arsoscultas.common.init.ArsOcultasLang;
import com.mystchonky.arsoscultas.common.init.Integrations;
import com.mystchonky.arsoscultas.common.network.Networking;
import com.tterrag.registrate.Registrate;
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

//    public static CreativeModeTab ArsOscuraTab = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), MODID) {
//        @Override
//        public ItemStack makeIcon() {
//            return ItemsRegistry.WAND.get().getDefaultInstance();
//        }
//    };

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

        ArsOcultasItems.register();
        Integrations.init();
        ArsOcultasLang.register();

        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(this::setup);
        modbus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void setup(final FMLCommonSetupEvent event) {
        Integrations.postInit();
        Networking.registerMessages();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

}
