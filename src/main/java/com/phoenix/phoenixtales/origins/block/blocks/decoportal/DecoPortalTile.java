package com.phoenix.phoenixtales.origins.block.blocks.decoportal;

import com.phoenix.phoenixtales.origins.block.OriginsTileEntities;
import com.phoenix.phoenixtales.origins.block.blocks.portal.OriginsPortalTile;
import net.minecraft.tileentity.TileEntityType;

public class DecoPortalTile extends OriginsPortalTile {

    public DecoPortalTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public DecoPortalTile() {
        this(OriginsTileEntities.DECO_PORTAL_TILE);
    }
}
