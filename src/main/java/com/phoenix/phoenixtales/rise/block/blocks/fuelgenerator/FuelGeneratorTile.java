package com.phoenix.phoenixtales.rise.block.blocks.fuelgenerator;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class FuelGeneratorTile extends TileEntity implements ITickableTileEntity {
    public FuelGeneratorTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void tick() {

    }
}
