package com.mystchonky.arsocultas.common.mob_jar;

import com.klikli_dev.occultism.common.container.spirit.SpiritContainer;
import com.klikli_dev.occultism.common.container.spirit.SpiritTransporterContainer;
import com.klikli_dev.occultism.common.entity.job.SpiritJob;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.mystchonky.arsocultas.common.init.ArsOcultasContainers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

public class MenuProviderWrapper<T extends MenuProvider> implements MenuProvider {

    private final T menuProvider;
    private final SpiritEntity spirit;

    public MenuProviderWrapper(T menuProvider, SpiritEntity spirit) {
        this.menuProvider = menuProvider;
        this.spirit = spirit;
    }

    @Override
    public Component getDisplayName() {
        return menuProvider.getDisplayName();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        if (menuProvider instanceof SpiritJob) {
            return wrappedSpiritTransporter(pContainerId, pPlayerInventory, spirit);
        }
        return wrappedSpirit(pContainerId, pPlayerInventory, spirit);
    }


    public static SpiritContainer wrappedSpirit(int windowId, Inventory inv, SpiritEntity spirit) {
        return new SpiritContainer(windowId, inv, spirit) {
            @Override
            public MenuType<?> getType() {
                return ArsOcultasContainers.SPIRIT_WRAPPER.get();
            }
        };
    }

    public static SpiritTransporterContainer wrappedSpiritTransporter(int windowId, Inventory inv, SpiritEntity spirit) {
        return new SpiritTransporterContainer(windowId, inv, spirit) {
            @Override
            public MenuType<?> getType() {
                return ArsOcultasContainers.SPIRIT_TRANSPORT_WRAPPER.get();
            }
        };
    }
}
