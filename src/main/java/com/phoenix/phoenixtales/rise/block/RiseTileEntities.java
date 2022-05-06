package com.phoenix.phoenixtales.rise.block;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.alloyfactory.AlloyTile;
import com.phoenix.phoenixtales.rise.block.blocks.assembler.AssemblerTile;
import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.AdvancedCableTile;
import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.CableTile;
import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.OverloadedCableTile;
import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.SimpleCableTile;
import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStoreTile;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressTile;
import com.phoenix.phoenixtales.rise.block.blocks.tank.TankTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = PhoenixTales.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RiseTileEntities {
    public static List<TileEntityType<?>> tiles = new ArrayList<>();

    @ObjectHolder(PhoenixTales.MOD_ID + ":simple_cable_tile")
    public static TileEntityType<SimpleCableTile> SIMPLE_CABLE;
    @ObjectHolder(PhoenixTales.MOD_ID + ":cable_tile")
    public static final TileEntityType<CableTile> CABLE = createTile("cable_tile", TileEntityType.Builder.create(CableTile::new, RiseBlocks.CABLE).build(null));
    @ObjectHolder(PhoenixTales.MOD_ID + ":advanced_cable_tile")
    public static final TileEntityType<AdvancedCableTile> ADVANCED_CABLE = createTile("advanced_cable_tile", TileEntityType.Builder.create(AdvancedCableTile::new, RiseBlocks.ADVANCED_CABLE).build(null));
    @ObjectHolder(PhoenixTales.MOD_ID + ":overloaded_cable_tile")
    public static final TileEntityType<OverloadedCableTile> OVERLOADED_CABLE = createTile("overloaded_cable_tile", TileEntityType.Builder.create(OverloadedCableTile::new, RiseBlocks.OVERLOADED_CABLE).build(null));
    @ObjectHolder(PhoenixTales.MOD_ID + ":press_tile")
    public static final TileEntityType<PressTile> PRESS_TILE = createTile("press_tile", TileEntityType.Builder.create(PressTile::new, RiseBlocks.PRESS_FACTORY).build(null));
    @ObjectHolder(PhoenixTales.MOD_ID + ":alloy_tile")
    public static final TileEntityType<AlloyTile> ALLOY_TILE = createTile("alloy_tile", TileEntityType.Builder.create(AlloyTile::new, RiseBlocks.ALLOY_FACTORY).build(null));
    @ObjectHolder(PhoenixTales.MOD_ID + ":assembler_tile")
    public static final TileEntityType<AssemblerTile> ASSEMBLER_TILE = createTile("assembler_tile", TileEntityType.Builder.create(AssemblerTile::new, RiseBlocks.ASSEMBLER).build(null));
    @ObjectHolder(PhoenixTales.MOD_ID + ":energy_store_tile")
    public static final TileEntityType<EnergyStoreTile> ENERGY_STORE_TILE = createTile("energy_store_tile", TileEntityType.Builder.create(EnergyStoreTile::new, RiseBlocks.ENERGY_STORE).build(null));
    @ObjectHolder(PhoenixTales.MOD_ID + ":tank_tile")
    public static final TileEntityType<TankTile> TANK_TILE = createTile("tank_tile", TileEntityType.Builder.create(TankTile::new, RiseBlocks.TANK).build(null));


    @SubscribeEvent
    public static void register(RegistryEvent.Register<TileEntityType<?>> event) {
        IForgeRegistry<TileEntityType<?>> r = event.getRegistry();
        r.register(TileEntityType.Builder.create(SimpleCableTile::new, RiseBlocks.SIMPLE_CABLE).build(null).setRegistryName("simple_cable_tile"));
        r.register(TileEntityType.Builder.create(CableTile::new, RiseBlocks.CABLE).build(null).setRegistryName("cable_tile"));
        r.register(TileEntityType.Builder.create(AdvancedCableTile::new, RiseBlocks.ADVANCED_CABLE).build(null).setRegistryName("advanced_cable_tile"));
        r.register(TileEntityType.Builder.create(OverloadedCableTile::new, RiseBlocks.OVERLOADED_CABLE).build(null).setRegistryName("overloaded_cable_tile"));
        r.register(TileEntityType.Builder.create(PressTile::new, RiseBlocks.PRESS_FACTORY).build(null).setRegistryName("press_tile"));
        r.register(TileEntityType.Builder.create(AlloyTile::new, RiseBlocks.ALLOY_FACTORY).build(null).setRegistryName("alloy_tile"));
        r.register(TileEntityType.Builder.create(AssemblerTile::new, RiseBlocks.ASSEMBLER).build(null).setRegistryName("assembler_tile"));
        r.register(TileEntityType.Builder.create(EnergyStoreTile::new, RiseBlocks.ENERGY_STORE).build(null).setRegistryName("energy_store_tile"));
        r.register(TileEntityType.Builder.create(TankTile::new, RiseBlocks.TANK).build(null).setRegistryName("tank_tile"));

    }

    @SuppressWarnings("deprecation")
    private static <T extends TileEntity> TileEntityType<T> createTile(String id, TileEntityType<T> tile) {
        ResourceLocation location = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (Registry.BLOCK_ENTITY_TYPE.keySet().contains(location)) {
            throw new IllegalStateException(location.toString() + " already exists!");
        }
        tile.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        tiles.add(tile);
        return tile;
    }
}
