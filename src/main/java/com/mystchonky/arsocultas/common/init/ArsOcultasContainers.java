package com.mystchonky.arsocultas.common.init;

import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.klikli_dev.occultism.common.container.spirit.SpiritContainer;
import com.klikli_dev.occultism.common.container.spirit.SpiritTransporterContainer;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.common.mob_jar.MenuProviderWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ArsOcultasContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(
            ForgeRegistries.MENU_TYPES, ArsOcultas.MODID);

    public static final RegistryObject<MenuType<SpiritContainer>> SPIRIT_WRAPPER =
            CONTAINERS.register("spirit_wrapper",
                    () -> IForgeMenuType
                            .create((windowId, inv, data) -> {
                                BlockPos pos = data.readBlockPos();
                                MobJarTile tile = (MobJarTile) Minecraft.getInstance().level.getBlockEntity(pos);
                                return MenuProviderWrapper.wrappedSpirit(windowId, inv, (SpiritEntity) tile.getEntity());
                            }));


    public static final RegistryObject<MenuType<SpiritTransporterContainer>> SPIRIT_TRANSPORT_WRAPPER =
            CONTAINERS.register("spirit_transport_wrapper",
                    () -> IForgeMenuType
                            .create((windowId, inv, data) -> {
                                BlockPos pos = data.readBlockPos();
                                MobJarTile tile = (MobJarTile) Minecraft.getInstance().level.getBlockEntity(pos);
                                return MenuProviderWrapper.wrappedSpiritTransporter(windowId, inv, (SpiritEntity) tile.getEntity());
                            }));
}
