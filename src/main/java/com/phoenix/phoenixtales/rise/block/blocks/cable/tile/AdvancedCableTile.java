package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.ITier;
import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class AdvancedCableTile extends TileEntity implements ITickableTileEntity, ITier {

    @Override
    public TechnologyTier getTier() {
        return TechnologyTier.ADVANCED;
    }

    public AdvancedCableTile() {
        super(RiseTileEntities.ADVANCED_CABLE);
    }

    @Override
    public void tick() {

    }
}
