package com.mystchonky.arsocultas.common.registrar;

import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.klikli_dev.occultism.common.container.spirit.SpiritContainer;
import com.klikli_dev.occultism.common.container.spirit.SpiritTransporterContainer;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.common.mob_jar.SpiritMenuWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MenuTypeRegistrar {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, ArsOcultas.MODID);

    public static final Supplier<MenuType<SpiritContainer>> SPIRIT_WRAPPER = MENU_TYPES.register("spirit_wrapper",
            () -> IMenuTypeExtension
                            .create((windowId, inv, data) -> {
                                BlockPos pos = data.readBlockPos();
                                MobJarTile tile = (MobJarTile) Minecraft.getInstance().level.getBlockEntity(pos);
                                return SpiritMenuWrapper.wrappedSpirit(windowId, inv, (SpiritEntity) tile.getEntity());
                            }));


    public static final Supplier<MenuType<SpiritTransporterContainer>> SPIRIT_TRANSPORT_WRAPPER =
            MENU_TYPES.register("spirit_transport_wrapper",
                    () -> IMenuTypeExtension
                            .create((windowId, inv, data) -> {
                                BlockPos pos = data.readBlockPos();
                                MobJarTile tile = (MobJarTile) Minecraft.getInstance().level.getBlockEntity(pos);
                                return SpiritMenuWrapper.wrappedSpiritTransporter(windowId, inv, (SpiritEntity) tile.getEntity());
                            }));


    public static void register(IEventBus modbus) {
        MENU_TYPES.register(modbus);
    }
}
