package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TestCableTile extends TileEntity implements ITickableTileEntity {
    public TestCableTile() {
        super(RiseTileEntities.TEST);
    }

    @Override
    public void tick() {

    }
}
