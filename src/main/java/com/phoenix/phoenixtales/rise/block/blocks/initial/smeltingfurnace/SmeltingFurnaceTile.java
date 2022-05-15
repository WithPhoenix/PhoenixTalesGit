package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class SmeltingFurnaceTile extends TileEntity implements ITickableTileEntity {

    public SmeltingFurnaceTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

    }

    @Override
    public void tick() {

    }
}
