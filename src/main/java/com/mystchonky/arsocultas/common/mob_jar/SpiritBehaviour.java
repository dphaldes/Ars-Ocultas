package com.mystchonky.arsocultas.common.mob_jar;

import com.hollingsworth.arsnouveau.api.item.inv.FilterableItemHandler;
import com.hollingsworth.arsnouveau.api.item.inv.InventoryManager;
import com.hollingsworth.arsnouveau.api.item.inv.MultiInsertReference;
import com.hollingsworth.arsnouveau.api.mob_jar.JarBehavior;
import com.hollingsworth.arsnouveau.api.util.InvUtil;
import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.klikli_dev.occultism.api.OccultismAPI;
import com.klikli_dev.occultism.common.entity.job.CleanerJob;
import com.klikli_dev.occultism.common.entity.job.SpiritJob;
import com.klikli_dev.occultism.common.entity.spirit.SpiritEntity;
import com.klikli_dev.occultism.exceptions.ItemHandlerMissingException;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;

import java.util.List;

public class SpiritBehaviour<T extends SpiritEntity> extends JarBehavior<T> {

    @Override
    public void use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, MobJarTile tile) {
        if (world.isClientSide) return;
        ItemStack heldStack = player.getItemInHand(handIn);
        SpiritEntity spirit = entityFromJar(tile);
        if (player.isSecondaryUseActive() && heldStack.isEmpty()) {
            openScreen(player, spirit, tile);
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
        Level level = tile.getLevel();
        if (level.isClientSide) return;
        SpiritEntity spirit = entityFromJar(tile);
        if (!spirit.isInitialized()) spirit.init();

        if (level.getGameTime() % 40 == 0) {
            // Pick up items
            List<ItemEntity> itemEntities = level.getEntitiesOfClass(ItemEntity.class, new AABB(tile.getBlockPos()).inflate(3.D), ItemEntity::isAlive);
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

        //run spirit jobs
        spirit.getJob().ifPresent(spiritJob -> {
            // CleanerJob is similar to allay
            if (spiritJob instanceof CleanerJob) {
                List<FilterableItemHandler> inventories = InvUtil.adjacentInventories(level, tile.getBlockPos());
                if (inventories.isEmpty()) {
                    return;
                }
                InventoryManager manager = new InventoryManager(inventories);
                MultiInsertReference reference = manager.insertStackWithReference(spirit.getItemInHand(InteractionHand.MAIN_HAND));
                if (!reference.isEmpty()) {
                    ItemStack remainder = reference.getRemainder();
                    spirit.setItemInHand(InteractionHand.MAIN_HAND, remainder);
                    level.playSound(null, tile.getBlockPos(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.8F, 1.0F);
                }
            }

            // Update job
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

    @Override
    public void getTooltip(MobJarTile tile, List<Component> tooltips) {
        super.getTooltip(tile, tooltips);
    }

    public void openScreen(Player playerEntity, SpiritEntity spirit, MobJarTile tile) {
        MenuProvider menuProvider;

        SpiritJob currentJob = spirit.getJob().orElse(null);
        if (currentJob instanceof MenuProvider jobMenuProvider)
            menuProvider = new MenuProviderWrapper<>(jobMenuProvider, spirit);
        else
            menuProvider = new MenuProviderWrapper<>(spirit, spirit);

        NetworkHooks.openScreen((ServerPlayer) playerEntity, menuProvider, (buf) -> buf.writeBlockPos(tile.getBlockPos()));
    }
}
