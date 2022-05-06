package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.ITier;
import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class SimpleCableTile extends TileEntity implements ITickableTileEntity, ITier {

    @Override
    public TechnologyTier getTier() {
        return TechnologyTier.SIMPLE;
    }

    public SimpleCableTile() {
        super(RiseTileEntities.SIMPLE_CABLE);
    }

    @Override
    public void tick() {

    }
}
