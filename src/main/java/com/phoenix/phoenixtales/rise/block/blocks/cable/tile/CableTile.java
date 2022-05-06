package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.ITier;
import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class CableTile extends TileEntity implements ITickableTileEntity, ITier {

    @Override
    public TechnologyTier getTier() {
        return TechnologyTier.NORMAL;
    }

    public CableTile() {
        super(RiseTileEntities.CABLE);
    }

    @Override
    public void tick() {

    }
}
