package com.phoenix.phoenixtales.origins.block;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.block.blocks.decoportal.DecoPortalTile;
import com.phoenix.phoenixtales.origins.block.blocks.portal.OriginsPortalTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class OriginsTileEntities {

    public static List<TileEntityType<?>> tiles = new ArrayList<>();

    public static final TileEntityType<OriginsPortalTile> PORTAL_TILE = createTile("portal_tile", TileEntityType.Builder.create(OriginsPortalTile::new, OriginsBlocks.PORTAL).build(null));
    public static final TileEntityType<DecoPortalTile> DECO_PORTAL_TILE = createTile("deco_portal_tile", TileEntityType.Builder.create(DecoPortalTile::new, OriginsBlocks.DECO_PORTAL).build(null));


    private static <T extends TileEntity> TileEntityType<T> createTile(String id, TileEntityType<T> tile) {
        tile.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        tiles.add(tile);
        return tile;
    }
}
