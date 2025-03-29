package com.mystchonky.arsocultas.common.event;

import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.hollingsworth.arsnouveau.common.datagen.EntityTagProvider;
import com.hollingsworth.arsnouveau.common.lib.EntityTags;
import com.klikli_dev.occultism.common.entity.job.CrusherJob;
import com.klikli_dev.occultism.common.entity.job.SmelterJob;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.klikli_dev.occultism.common.item.tool.SoulGemItem;
import com.klikli_dev.occultism.registry.OccultismItems;
import com.klikli_dev.occultism.registry.OccultismTags;
import com.klikli_dev.occultism.util.EntityUtil;
import com.mystchonky.arsocultas.ArsOcultas;
import com.mystchonky.arsocultas.common.mob_jar.SpiritBehaviour;
import com.mystchonky.arsocultas.config.BaseConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = ArsOcultas.MODID)
public class MinecraftEvents {

    @SubscribeEvent
    public static void crusherJob(PlayerInteractEvent.RightClickBlock event) {
        var player = event.getEntity();
        var stack = event.getItemStack();
        if (!(stack.getItem() instanceof SoulGemItem)) {
            return;
        }

        var level = event.getLevel();
        var hit = event.getHitVec();
        var be = level.getBlockEntity(hit.getBlockPos());
        if (!(be instanceof MobJarTile jar)) {
            return;
        }

        var gemData = stack.get(DataComponents.ENTITY_DATA);
        var jarEntity = jar.getEntity();

        if (gemData == null && jarEntity != null) {
            if (!BaseConfig.SERVER.CONTAINMENT_JARS_SOUL_GEM_PICKUP.get()) {
                // We cancel regardless to prevent an entity dupe.
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
                return;
            }

            var type = jarEntity.getType();

            if (type.is(EntityTags.JAR_RELEASE_BLACKLIST) || (!type.is(EntityTags.JAR_WHITELIST) && type.is(EntityTags.JAR_BLACKLIST))) {
                player.sendSystemMessage(
                        Component.translatable(stack.getDescriptionId() + ".message.entity_type_denied"));
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
                return;
            }

            if (type.is(OccultismTags.Entities.SOUL_GEM_DENY_LIST) && stack.getItem().equals(OccultismItems.SOUL_GEM_ITEM.get())) {
                player.sendSystemMessage(
                        Component.translatable(stack.getDescriptionId() + ".message.entity_type_denied"));
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
                return;
            }

            if (type.is(OccultismTags.Entities.TRINITY_GEM_DENY_LIST) && stack.getItem().equals(OccultismItems.TRINITY_GEM_ITEM.get())) {
                player.sendSystemMessage(
                        Component.translatable(stack.getDescriptionId() + ".message.entity_type_denied"));
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
                return;
            }

            var entityData = new CompoundTag();
            var id = jarEntity.getEncodeId();
            if (id != null) {
                entityData.putString("id", id);
            }

            entityData = jarEntity.saveWithoutId(entityData);
            stack.set(DataComponents.ENTITY_DATA, CustomData.of(entityData));
            jar.removeEntity();

            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
        } else if (gemData != null && jarEntity == null) {
            if (!BaseConfig.SERVER.CONTAINMENT_JARS_SOUL_GEM_PLACE.get()) {
                return;
            }

            CompoundTag entityData = stack.get(DataComponents.ENTITY_DATA).getUnsafe();
            stack.remove(DataComponents.ENTITY_DATA);

            EntityType<?> type = EntityUtil.entityTypeFromNbt(entityData);

            if (!type.is(EntityTags.JAR_WHITELIST) && type.is(EntityTags.JAR_BLACKLIST)) {
                player.sendSystemMessage(
                        Component.translatable(stack.getDescriptionId() + ".message.entity_type_denied"));
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
            }

            if (type.is(OccultismTags.Entities.SOUL_GEM_DENY_LIST) && stack.getItem().equals(OccultismItems.SOUL_GEM_ITEM.get())) {
                player.sendSystemMessage(
                        Component.translatable(stack.getDescriptionId() + ".message.entity_type_denied"));
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
                return;
            }

            if (type.is(OccultismTags.Entities.TRINITY_GEM_DENY_LIST) && stack.getItem().equals(OccultismItems.TRINITY_GEM_ITEM.get())) {
                player.sendSystemMessage(
                        Component.translatable(stack.getDescriptionId() + ".message.entity_type_denied"));
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
                return;
            }

            Entity entity = type.create(level);
            if (entity == null) {
                event.setCancellationResult(InteractionResult.FAIL);
                event.setCanceled(true);
                return;
            }

            entity.load(entityData);
            jar.setEntityData(entity);

            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
        }
    }
}
