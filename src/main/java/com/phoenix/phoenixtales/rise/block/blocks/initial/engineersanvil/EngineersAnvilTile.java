package com.phoenix.phoenixtales.rise.block.blocks.initial.engineersanvil;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

public class EngineersAnvilTile extends TileEntity implements IClearable {
    private ItemStack stack = ItemStack.EMPTY;

    public EngineersAnvilTile() {
        super(RiseTileEntities.ENGINEERS_ANVIL);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        if (nbt.contains("stack")) {
            this.setStack(ItemStack.read(nbt.getCompound("stack")));
        }
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (!this.getStack().isEmpty()) {
            compound.put("stack", this.getStack().write(new CompoundNBT()));
        }
        return super.write(compound);
    }

    public ItemStack getStack() {
        return this.stack;
    }

    public ItemStack getStackCopied() {
        return this.stack.copy();
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
        this.markDirty();
    }

    public void craft() {
        //drop Item, after crafting
    }

    public void dropAllItems() {
        if (this.world != null) {
            if (!this.world.isRemote) {
                InventoryHelper.spawnItemStack(world, (double) this.pos.getX(), (double) pos.getY(), (double) pos.getZ(), this.getStack());
            }
        }
    }

    @Override
    public void clear() {
        this.setStack(ItemStack.EMPTY);
    }

}
