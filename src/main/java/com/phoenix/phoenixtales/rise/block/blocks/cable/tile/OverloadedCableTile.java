package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.enums.TechnologyType;

public class OverloadedCableTile extends GenericCableTile {
    public OverloadedCableTile() {
        super(RiseTileEntities.OVERLOADED_CABLE, TechnologyType.OVERLOADED);
    }
}

