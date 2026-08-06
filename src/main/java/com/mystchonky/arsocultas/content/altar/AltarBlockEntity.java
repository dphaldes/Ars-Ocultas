package com.mystchonky.arsocultas.content.altar;

import com.hollingsworth.arsnouveau.api.util.SourceUtil;
import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.klikli_dev.occultism.common.blockentity.GoldenSacrificialBowlBlockEntity;
import com.mystchonky.arsocultas.foundation.TickingBlockEntity;
import com.mystchonky.arsocultas.init.BlockEntityRegistrar;
import com.mystchonky.arsocultas.init.BlockRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class AltarBlockEntity extends BaseContainerBlockEntity implements TickingBlockEntity, Container {
    private static final int COST = 5000;
    private static final int CONTAINER_SIZE = 27;
    private static final int SLOT_SIZE = 1;
    private NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    private final ItemStackHandler itemHandler = new ItemStackHandler(items);

    public AltarBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.ALTAR.get(), pos, blockState);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.getGameTime() % 300 != 0)
            return;

        if (level.getBlockEntity(pos.above()) instanceof GoldenSacrificialBowlBlockEntity bowl) {
            var recipe = bowl.getCurrentRitualRecipe();
            if (recipe == null)
                return;

            if (!bowl.sacrificeFulfilled()) {
                for (BlockPos b : BlockPos.withinManhattan(getBlockPos().above(3), 10, 6, 10)) {
                    if (level.getBlockEntity(b) instanceof MobJarTile mobJarTile && mobJarTile.getEntity() instanceof LivingEntity entity) {
                        if (recipe.value().getRitual().isValidSacrifice(entity)) {
                            if (SourceUtil.hasSourceNearby(worldPosition, level, 10, COST)) {
                                SourceUtil.takeSourceMultipleWithParticles(worldPosition, level, 10, COST);
                                bowl.notifySacrifice(null);
                            }

                            return;
                        }
                    }
                }
            }
            if (!bowl.itemUseFulfilled()) {
                for (int slot = 0; slot < getContainerSize(); slot++) {
                    ItemStack stack = getItem(slot);
                    if (recipe.value().getItemToUse().test(stack)) {
                        if (SourceUtil.hasSourceNearby(worldPosition, level, 10, COST)) {
                            SourceUtil.takeSourceMultipleWithParticles(worldPosition, level, 10, COST);
                            bowl.notifyItemUse(null);
                            // TODO: FIND A WAY TO CONSUME ITEM OR REACT WITH A STATE CHANGE
                        }
                        return;
                    }
                }
            }

        }

    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable(BlockRegistrar.ALTAR.get().getDescriptionId());
    }

    @Override
    public int getMaxStackSize() {
        return SLOT_SIZE;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new AltarMenu(i, inventory, this);
    }

    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    public void dropInventory() {
        Containers.dropContents(this.level, this.worldPosition, items);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        ContainerHelper.loadAllItems(tag, this.items, registries);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }
}
