package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.tile;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;

import java.util.Random;

public abstract class SmeltingFurnaceTile extends TileEntity {
    protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
    protected final Random RANDOM = new Random();

    public SmeltingFurnaceTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        ItemStackHelper.loadAllItems(nbt, this.items);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.items);
        return compound;
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }
}
