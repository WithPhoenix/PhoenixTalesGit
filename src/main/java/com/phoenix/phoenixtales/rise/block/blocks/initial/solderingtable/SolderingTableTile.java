package com.phoenix.phoenixtales.rise.block.blocks.initial.solderingtable;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.item.RiseItems;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;

//todo the table needs energy
public class SolderingTableTile extends TileEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    private ItemStack tin_solder = new ItemStack(RiseItems.TIN_SOLDER, 0);
    private ItemStack soldering_iron = new ItemStack(RiseItems.SOLDERING_IRON, 0);
    private int progress;

    public SolderingTableTile() {
        super(RiseTileEntities.SOLDERING_TILE);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.progress = nbt.contains("progress") ? nbt.getInt("progress") : 0;
        if (nbt.contains("tin")) {
            this.setTin(ItemStack.read(nbt.getCompound("tin")));
        }
        if (nbt.contains("iron")) {
            this.setIron(ItemStack.read(nbt.getCompound("iron")));
        }
        ItemStackHelper.loadAllItems(nbt, this.items);
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("progress", this.progress);
        this.writeStacks(compound);
        return compound;
    }

    private CompoundNBT writeStacks(CompoundNBT compound) {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.items);
        if (!this.getTin().isEmpty()) {
            compound.put("tin", this.getTin().write(new CompoundNBT()));
        }
        if (!this.getIron().isEmpty()) {
            compound.put("iron", this.getIron().write(new CompoundNBT()));
        }
        return compound;
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.writeStacks(new CompoundNBT());
    }


    //TODO check
    public boolean hasRecipe() {
        return false;
    }

    public int progress() {
        return this.progress;
    }

    public void clearProgress() {
        this.progress = 0;
    }

    public void increaseProgress() {
        ++this.progress;
    }

    public void setTin(ItemStack stack) {
        this.tin_solder = stack;
        this.markDirty();
    }

    public ItemStack getTin() {
        return this.tin_solder;
    }

    public void setIron(ItemStack stack) {
        this.soldering_iron = stack;
        this.markDirty();
    }

    public ItemStack getIron() {
        return this.soldering_iron;
    }


    public NonNullList<ItemStack> removeAll() {
        NonNullList<ItemStack> list = NonNullList.withSize(6, ItemStack.EMPTY);
        for (int i = 3; i >= 0; i--) {
            if (!this.items.get(i).isEmpty()) {
                list.set(i, this.items.get(i));
                this.items.set(i, ItemStack.EMPTY);
            }
        }
        list.set(4, this.soldering_iron);
        this.soldering_iron = new ItemStack(RiseItems.SOLDERING_IRON, 0);
        list.set(5, this.tin_solder);
        this.tin_solder = new ItemStack(RiseItems.TIN_SOLDER, 0);
        return list;
    }

    public ItemStack removeStack() {
        ItemStack stack = ItemStack.EMPTY;
        for (int i = 3; i >= 0; i--) {
            if (!this.items.get(i).isEmpty()) {
                stack = this.items.get(i);
                this.items.set(i, ItemStack.EMPTY);
                return stack;
            }
        }
        if (this.tin_solder.isEmpty()) {
            if (!this.soldering_iron.isEmpty()) {
                stack = this.soldering_iron;
                this.soldering_iron = new ItemStack(RiseItems.SOLDERING_IRON, 0);
            }
        } else {
            stack = this.tin_solder;
            this.tin_solder = new ItemStack(RiseItems.TIN_SOLDER, 0);
        }
        return stack.copy();
    }

    public boolean addStack(ItemStack stack) {
        for (int i = 0; i < 4; i++) {
            if (this.items.get(i).isEmpty()) {
                this.items.set(i, stack);
                return true;
            }
        }
        return false;
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }
}
