package com.mystchonky.arsocultas.content.altar;

import com.mystchonky.arsocultas.init.BlockRegistrar;
import com.mystchonky.arsocultas.init.MenuTypeRegistrar;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class AltarMenu extends AbstractContainerMenu {
    public final AltarBlockEntity blockEntity;

    public AltarMenu(int containerId, Inventory playerInventory, AltarBlockEntity blockEntity) {
        super(MenuTypeRegistrar.ALTAR.get(), containerId);
        this.blockEntity = blockEntity;

        addInventory(playerInventory);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        int CONTAINER_SLOTS = blockEntity.getContainerSize();
        Slot slot = this.slots.get(index);
        ItemStack stack = ItemStack.EMPTY;
        if (slot.hasItem()) {
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
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()),
                player, BlockRegistrar.ALTAR.get());
    }

    private void addInventory(Inventory playerInventory) {
        // altar
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                addSlot(new Slot(blockEntity, l + i * 9, 8 + l * 18, 18 + i * 18));
            }
        }

        // player inventory
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }

        //
        for (int i = 0; i < 9; ++i) {
            addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

}

