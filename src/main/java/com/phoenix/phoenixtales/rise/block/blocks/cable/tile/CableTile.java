package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.tileentity.TileEntityType;

public class CableTile extends AbstractCableTile {
    public CableTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn, TechnologyTier.NORMAL);
    }

    public CableTile() {
        this(RiseTileEntities.CABLE);
    }
}
