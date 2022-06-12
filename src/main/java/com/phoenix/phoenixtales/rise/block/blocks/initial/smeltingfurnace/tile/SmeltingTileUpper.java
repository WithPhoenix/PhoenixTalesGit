package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.SmeltingFurnaceBottom;
import com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.SmeltingFurnaceTop;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;

public class SmeltingTileUpper extends SmeltingFurnaceTile implements ITickableTileEntity {
    private int time;
    private int ctime;

    public SmeltingTileUpper() {
        super(RiseTileEntities.SMELTING_TILE_UPPER);
        this.items.set(0, new ItemStack(Items.CHARCOAL, 0));
        this.items.set(1, new ItemStack(Items.IRON_ORE, 0));
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.time = nbt.getInt("time");
        this.ctime = nbt.getInt("ctime");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putInt("time", this.time);
        compound.putInt("ctime", this.ctime);
        return compound;
    }

    public boolean canInsert(boolean coal) {
        if (coal) {
            return this.items.get(0).getCount() < 64;
        } else {
            return this.items.get(1).getCount() < 32;
        }
    }

    public boolean hasCoal() {
        return !this.items.get(0).isEmpty();
    }

    public void addCoal(int n) {
        this.items.get(0).grow(n);
    }

    public void addIron(int n) {
        this.items.get(1).grow(n);
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            //coal
            if (this.getBlockState().get(SmeltingFurnaceTop.LIT)) {
                if (ctime == 200) {
                    this.items.get(0).shrink(1);
                    this.ctime = 0;
                }
                if (this.hasCoal()) {
                    ++this.ctime;
                } else {
                    if (this.getBlockState().get(SmeltingFurnaceTop.LIT)) {
                        world.setBlockState(pos.down(), world.getBlockState(pos.down()).with(SmeltingFurnaceBottom.LIT, Boolean.valueOf(false)));
                        world.setBlockState(pos, world.getBlockState(pos).with(SmeltingFurnaceTop.LIT, Boolean.valueOf(false)));
                    }
                    this.ctime = 0;
                }
            }

            //now iron
            if (this.getBlockState().get(SmeltingFurnaceTop.LIT)) {
                if (!this.items.get(1).isEmpty()) {
                    if (this.time == 300) {
                        if (world.getTileEntity(pos.down()) instanceof SmeltingTileLower) {
                            this.items.get(1).shrink(1);
                            this.time = 0;
                            SmeltingTileLower tile = (SmeltingTileLower) world.getTileEntity(pos.down());
                            if (tile != null) {
                                tile.receiveProgressDone();
                            }
                        }
                    }
                    this.time++;
                }
            } else {
                this.time = 0;
            }
        }
    }
}
