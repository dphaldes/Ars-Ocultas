package com.mystchonky.arsocultas.content;

import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.hollingsworth.arsnouveau.common.items.MobJarItem;
import com.klikli_dev.occultism.Occultism;
import com.klikli_dev.occultism.common.entity.job.CrusherJob;
import com.klikli_dev.occultism.common.entity.job.CrystallizerJob;
import com.klikli_dev.occultism.common.entity.job.SmelterJob;
import com.klikli_dev.occultism.common.entity.job.TraderJob;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.content.spirit_jar.SpiritBehaviour;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.apache.commons.lang3.StringUtils;

@EventBusSubscriber(modid = ArsOcultas.MODID)
public class EventHandler {

    private static final String TRANSLATION_KEY_BASE = "gui." + Occultism.MODID + ".spirit";

    // TODO: add common method when main Occultism mod will have a common class for all of those events
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

    @SubscribeEvent
    public static void smelterJob(SmelterJob.SmelterJobEvent event) {
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

    @SubscribeEvent
    public static void crystallizerJob(CrystallizerJob.CrystallizerJobEvent event) {
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

    @SubscribeEvent
    public static void traderJob(TraderJob.TraderJobEvent event) {
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

    @SubscribeEvent
    public static void itemTooltips(ItemTooltipEvent event) {
        var stack = event.getItemStack();
        var tooltips = event.getToolTip();
        if (MobJarItem.fromItem(stack, event.getContext().level()) instanceof SpiritEntity spirit) {
            var job = spirit.getJobID();
            if (!StringUtils.isBlank(job)) {
                job = job.replace(":", ".");
                String jobText = I18n.get(TRANSLATION_KEY_BASE + ".job", I18n.get("job." + job));
                tooltips.add(Component.translatable(jobText).withStyle(ChatFormatting.GOLD));
            }
        }
    }

}
