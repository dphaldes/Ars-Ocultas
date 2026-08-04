package com.mystchonky.arsocultas.content.altar;

import com.mystchonky.arsocultas.init.BlockRegistrar;
import com.mystchonky.arsocultas.init.MenuTypeRegistrar;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AltarMenu extends AbstractContainerMenu {
    public final AltarBlockEntity blockEntity;
    public final Level level;

    public AltarMenu(int containerId, Inventory inv, BlockEntity blockEntity) {
        super(MenuTypeRegistrar.ALTAR_MENU.get(), containerId);
        this.blockEntity = ((AltarBlockEntity) blockEntity);
        this.level = inv.player.level();

        addBlockentityInventory(this.blockEntity);
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        System.out.println(index);
        int CONTAINER_SLOTS = blockEntity.getContainerSize();
        Slot slot = this.slots.get(index);
        ItemStack stack = ItemStack.EMPTY;
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack = slot.getItem();
            stack = itemStack.copy();
            if (index < CONTAINER_SLOTS) {
                if (!this.moveItemStackTo(itemStack, CONTAINER_SLOTS, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStack, 0, CONTAINER_SLOTS, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return stack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, BlockRegistrar.ALTAR.get());
    }

    private void addBlockentityInventory(Container inv) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(inv, l + i * 9, 8 + l * 18, 18 + i * 18));
            }
        }
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
