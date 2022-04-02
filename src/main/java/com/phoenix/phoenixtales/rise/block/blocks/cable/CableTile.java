package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class CableTile extends TileEntity implements ITickableTileEntity {

    public CableTile(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public CableTile() {
        super(RiseTileEntities.CABLE_TILE);
    }

    @Override
    public void tick() {
    }
}
