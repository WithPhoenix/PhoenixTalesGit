package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.ITier;
import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class OverloadedCableTile extends TileEntity implements ITickableTileEntity, ITier {

    @Override
    public TechnologyTier getTier() {
        return TechnologyTier.OVERLOADED;
    }

    public OverloadedCableTile() {
        super(RiseTileEntities.OVERLOADED_CABLE);
    }

    @Override
    public void tick() {

    }
}
