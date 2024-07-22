package com.mystchonky.arsocultas.common.mob_jar;

import com.klikli_dev.occultism.client.gui.spirit.SpiritTransporterGui;
import com.klikli_dev.occultism.common.container.spirit.SpiritTransporterContainer;
import com.mystchonky.arsocultas.common.network.MessageSpiritSetFilter;
import com.mystchonky.arsocultas.common.network.Network;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class SpiritScreenWrapper {
    public static SpiritTransporterGui wrapTransporterGui(SpiritTransporterContainer container,
                                                          Inventory playerInventory,
                                                          Component titleIn) {
        return new SpiritTransporterGui(container, playerInventory, titleIn) {
            @Override
            public void setIsBlacklist(boolean isBlacklist) {
                super.setIsBlacklist(isBlacklist);
                Network.sendToServer(new MessageSpiritSetFilter(isBlacklist, spirit.blockPosition()));
            }
        };
    }
}
