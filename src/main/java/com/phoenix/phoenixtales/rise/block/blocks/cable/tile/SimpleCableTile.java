package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.tileentity.TileEntityType;

public class SimpleCableTile extends AbstractCableTile {
    public SimpleCableTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn, TechnologyTier.SIMPLE);
    }

    public SimpleCableTile() {
        this(RiseTileEntities.SIMPLE_CABLE);
    }
}
