package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.SmeltingFurnace;
import net.minecraft.block.BlockState;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;

import java.util.Random;

public class SmeltingTileUpper extends SmeltingFurnaceTile implements ITickableTileEntity {
    private int time;
    private final Random random = new Random();

    public SmeltingTileUpper() {
        super(RiseTileEntities.SMELTING_TILE_UPPER);
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
        this.items.set(0, Items.CHARCOAL.getDefaultInstance());
    }

    public void addIron(int n) {
        this.items.set(1, Items.IRON_INGOT.getDefaultInstance());
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            if (this.getBlockState().get(SmeltingFurnace.LIT)) {
                if (items.get(0).getCount() > 0 && items.get(1).getCount() > 2) {
                    if (this.time == 360) {
                        this.items.get(0).shrink(random.nextInt(2) + 2);
                        this.items.get(1).shrink(random.nextInt(2) + 1);
                        this.time = 0;
                    }
                    this.time++;
                }
            }
        }
    }
}
