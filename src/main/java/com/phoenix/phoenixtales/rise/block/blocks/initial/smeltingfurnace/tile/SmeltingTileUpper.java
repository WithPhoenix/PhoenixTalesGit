package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.SmeltingFurnace;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;

public class SmeltingTileUpper extends SmeltingFurnaceTile implements ITickableTileEntity {
    private int time;

    public SmeltingTileUpper() {
        super(RiseTileEntities.SMELTING_TILE_UPPER);
        this.items.set(0, new ItemStack(Items.CHARCOAL, 0));
        this.items.set(1, new ItemStack(Items.IRON_INGOT, 0));
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.time = nbt.getInt("time");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("time", this.time);
        return compound;
    }

    public void addCoal(int n) {
        this.items.get(0).grow(n);
    }

    public void addIron(int n) {
        if (this.items.get(1).getItem() != Items.IRON_INGOT) {
            this.items.set(1, new ItemStack(Items.IRON_INGOT, n));
            return;
        }
        this.items.get(1).grow(n);
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            if (this.getBlockState().get(SmeltingFurnace.LIT)) {
                if (items.get(0).getCount() > 2 && items.get(1).getCount() > 0) {
                    if (this.time == 360) {
                        this.items.get(0).shrink(RANDOM.nextInt(2) + 2);
                        this.items.get(1).shrink(RANDOM.nextInt(2) + 1);
                        this.time = 0;
                        if (world.getTileEntity(pos.down()) instanceof SmeltingTileLower) {
                            SmeltingTileLower tile = (SmeltingTileLower) world.getTileEntity(pos.down());
                            if (tile != null) {
                                tile.receiveProgressDone();
                            }
                        }
                    }
                    this.time++;
                }
            }
        }
    }
}
