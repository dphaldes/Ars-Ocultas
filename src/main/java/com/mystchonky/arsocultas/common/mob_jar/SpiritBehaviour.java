package com.mystchonky.arsocultas.common.mob_jar;

import com.hollingsworth.arsnouveau.api.mob_jar.JarBehavior;
import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.klikli_dev.occultism.api.OccultismAPI;
import com.klikli_dev.occultism.common.entity.job.CleanerJob;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.klikli_dev.occultism.exceptions.ItemHandlerMissingException;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpiritBehaviour<T extends SpiritEntity> extends JarBehavior<T> {

    @Override
    public void use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, MobJarTile tile) {
        if (world.isClientSide)
            return;
        ItemStack heldStack = player.getItemInHand(handIn);
        SpiritEntity spirit = entityFromJar(tile);

        if (player.isSecondaryUseActive() && heldStack.isEmpty()) {
            spirit.openScreen(player);
            return;
        }

        if (canAcceptItemStack(spirit, heldStack)) {
            ItemStack duplicate = heldStack.copy();
            ItemStackHandler handler = spirit.itemStackHandler.orElseThrow(ItemHandlerMissingException::new);
            if (ItemHandlerHelper.insertItemStacked(handler, duplicate, true).getCount() < duplicate.getCount()) {
                ItemStack remaining = ItemHandlerHelper.insertItemStacked(handler, duplicate, false);
                heldStack.setCount(remaining.getCount());
            }
        }

    }

    @Override
    public void tick(MobJarTile tile) {
        if (tile.getLevel().isClientSide)
            return;
        SpiritEntity spirit = entityFromJar(tile);
        if (!spirit.isInitialized())
            spirit.init();

        // In 7x7x7 area, if spirit can accept any items, collect
        if (tile.getLevel().getRandom().nextInt(20) == 0) {
            List<ItemEntity> itemEntities = spirit.level.getEntitiesOfClass(ItemEntity.class, new AABB(tile.getBlockPos()).inflate(3), ItemEntity::isAlive);
            if (!itemEntities.isEmpty()) {
                ItemEntity itemEntity = itemEntities.stream().filter(item -> OccultismAPI.get().canPickupItem(spirit, item).orElse(false)).findFirst().orElse(null);
                if (itemEntity != null) {
                    ItemStack duplicate = itemEntity.getItem().copy();
                    ItemStackHandler handler = spirit.itemStackHandler.orElseThrow(ItemHandlerMissingException::new);
                    if (ItemHandlerHelper.insertItemStacked(handler, duplicate, true).getCount() < duplicate.getCount()) {
                        ItemStack remaining = ItemHandlerHelper.insertItemStacked(handler, duplicate, false);
                        itemEntity.getItem().setCount(remaining.getCount());
                    }
                }
            }
        }
        spirit.getJob().ifPresent(spiritJob -> {
            // Every 5 ticks
            if (tile.getLevel().getRandom().nextInt(5) == 0) {
                // Cleaner Spirit automatically pushes into inventory on top
                if (spiritJob instanceof CleanerJob) {
                    BlockEntity blockEntity = tile.getLevel().getBlockEntity(tile.getBlockPos().above());
                    IItemHandler handler = getItemCapFromTile(blockEntity);
                    if (handler == null) return;
                    ItemStack duplicate = spirit.getItemInHand(InteractionHand.MAIN_HAND).copy();

                    //simulate insertion
                    ItemStack toInsert = ItemHandlerHelper.insertItem(handler, duplicate, true);
                    //if anything was inserted go for real
                    if (toInsert.getCount() != duplicate.getCount()) {
                        ItemStack leftover = ItemHandlerHelper.insertItem(handler, duplicate, false);
                        //if we inserted everything
                        spirit.setItemInHand(InteractionHand.MAIN_HAND, leftover);
                    }

                }
            }

            // Update job (for crusher)
            spiritJob.update();
            tile.updateBlock();
        });
    }

    private boolean canAcceptItemStack(SpiritEntity spirit, ItemStack stack) {
        OccultismAPI api = OccultismAPI.get();
        if (api.getItemsToPickUp(spirit).isPresent()) {
            return api.getItemsToPickUp(spirit).get().stream().anyMatch(item -> item.test(stack));
        }
        return false;
    }

    private @Nullable IItemHandler getItemCapFromTile(BlockEntity blockEntity) {
        if (blockEntity != null && blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).isPresent()) {
            var lazy = blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve();
            if (lazy.isPresent())
                return lazy.get();
        }
        return null;
    }

}
