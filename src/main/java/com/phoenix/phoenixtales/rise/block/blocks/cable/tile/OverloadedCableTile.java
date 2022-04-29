package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.tileentity.TileEntityType;

public class OverloadedCableTile extends AbstractCableTile {
    public OverloadedCableTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn, TechnologyTier.PHOENIX);
    }

    public OverloadedCableTile() {
        this(RiseTileEntities.OVERLOADED_CABLE);
    }
}
