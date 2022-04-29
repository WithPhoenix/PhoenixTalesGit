package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.tileentity.TileEntityType;

public class PhoenixCableTile extends AbstractCableTile {
    public PhoenixCableTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn, TechnologyTier.PHOENIX);
    }

    public PhoenixCableTile() {
        this(RiseTileEntities.PHOENIX_CABLE);
    }
}
