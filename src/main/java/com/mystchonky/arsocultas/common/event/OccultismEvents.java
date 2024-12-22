package com.mystchonky.arsocultas.common.event;

import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.klikli_dev.occultism.common.entity.job.CrusherJob;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.common.mob_jar.SpiritBehaviour;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = ArsOcultas.MODID)
public class OccultismEvents {

    @SubscribeEvent
    public static void crusherJob(CrusherJob.CrusherJobEvent event) {
        var entity = event.getEntity();
        var level = event.getEntity().level();
        if (entity instanceof SpiritEntity spirit) {
            if (level.getBlockEntity(spirit.blockPosition()) instanceof MobJarTile jar) {
                jar.dispatchBehavior(behavior -> {
                    if (behavior instanceof SpiritBehaviour<? extends SpiritEntity> spiritBehaviour) {
                        var remainder = spiritBehaviour.tryItemNearbyTransfer(jar, level, event.getResult());
                        event.setResult(remainder);
                    }
                });

            }
        }
    }
}
