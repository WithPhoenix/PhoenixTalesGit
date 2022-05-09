package com.phoenix.phoenixtales.rise.block;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.alloyfactory.AlloyTile;
import com.phoenix.phoenixtales.rise.block.blocks.assembler.AssemblerTile;
import com.phoenix.phoenixtales.rise.block.blocks.cable.CableTile;
import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStoreTile;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressTile;
import com.phoenix.phoenixtales.rise.block.blocks.tank.TankTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class RiseTileEntities {
    public static List<TileEntityType<?>> tiles = new ArrayList<>();

    public static final TileEntityType<CableTile> CABLE_TILE = createTile("cable_tile", TileEntityType.Builder.create(CableTile::new, RiseBlocks.CABLE).build(null));
    public static final TileEntityType<PressTile> PRESS_TILE = createTile("press_tile", TileEntityType.Builder.create(PressTile::new, RiseBlocks.PRESS_FACTORY).build(null));
    public static final TileEntityType<AlloyTile> ALLOY_TILE = createTile("alloy_tile", TileEntityType.Builder.create(AlloyTile::new, RiseBlocks.ALLOY_FACTORY).build(null));
    public static final TileEntityType<AssemblerTile> ASSEMBLER_TILE = createTile("assembler_tile", TileEntityType.Builder.create(AssemblerTile::new, RiseBlocks.ASSEMBLER).build(null));
    public static final TileEntityType<EnergyStoreTile> ENERGY_STORE_TILE = createTile("energy_store_tile", TileEntityType.Builder.create(EnergyStoreTile::new, RiseBlocks.ENERGY_STORE).build(null));

    public static final TileEntityType<TankTile> TANK_TILE = createTile("tank_tile", TileEntityType.Builder.create(TankTile::new, RiseBlocks.TANK).build(null));

    private static <T extends TileEntity> TileEntityType<T> createTile(String id, TileEntityType<T> tile) {
        tile.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        tiles.add(tile);
        return tile;
    }
}
