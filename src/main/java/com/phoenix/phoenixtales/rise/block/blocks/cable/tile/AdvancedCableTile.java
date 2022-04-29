package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.tileentity.TileEntityType;

public class AdvancedCableTile extends AbstractCableTile {
    public AdvancedCableTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn, TechnologyTier.ADVANCED);
    }

    public AdvancedCableTile() {
        this(RiseTileEntities.ADVANCED_CABLE);
    }
}
