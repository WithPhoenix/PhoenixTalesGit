package com.phoenix.phoenixtales.origins.block;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.block.blocks.decoportal.DecoPortalTile;
import com.phoenix.phoenixtales.origins.block.blocks.portal.OriginsPortalTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class OriginsTileEntities {


    @ObjectHolder(PhoenixTales.MOD_ID + ":portal_tile")
    public static TileEntityType<OriginsPortalTile> PORTAL_TILE;
    @ObjectHolder(PhoenixTales.MOD_ID + ":deco_portal_tile")
    public static TileEntityType<DecoPortalTile> DECO_PORTAL_TILE;


    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registry {
        @SubscribeEvent
        public static void register(RegistryEvent.Register<TileEntityType<?>> event) {
            IForgeRegistry<TileEntityType<?>> r = event.getRegistry();
            r.register(TileEntityType.Builder.create(OriginsPortalTile::new, OriginsBlocks.PORTAL).build(null).setRegistryName("portal_tile"));
            r.register(TileEntityType.Builder.create(DecoPortalTile::new, OriginsBlocks.DECO_PORTAL).build(null).setRegistryName("deco_portal_tile"));
        }
    }

//    private static <T extends TileEntity> TileEntityType<T> createTile(String id, TileEntityType<T> tile) {
//        tile.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
//        tiles.add(tile);
//        return tile;
//    }
}
