package com.phoenix.phoenixtales.rise.block;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.alloyfactory.AlloyTile;
import com.phoenix.phoenixtales.rise.block.blocks.assembler.AssemblerTile;
import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.TestCableTile;
import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStoreTile;
import com.phoenix.phoenixtales.rise.block.blocks.initial.engineersanvil.EngineersAnvilTile;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressTile;
import com.phoenix.phoenixtales.rise.block.blocks.tank.TankTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;


public class RiseTileEntities {
//    public static List<TileEntityType<?>> tiles = new ArrayList<>();


    //    @ObjectHolder(PhoenixTales.MOD_ID + ":simple_cable")
//    public static TileEntityType<SimpleCableTile> SIMPLE_CABLE;
//    @ObjectHolder(PhoenixTales.MOD_ID + ":cable_tile")
//    public static TileEntityType<CableTile> CABLE;
//    @ObjectHolder(PhoenixTales.MOD_ID + ":advanced_cable")
//    public static TileEntityType<AdvancedCableTile> ADVANCED_CABLE;
//    @ObjectHolder(PhoenixTales.MOD_ID + ":overloaded_cable")
//    public static TileEntityType<OverloadedCableTile> OVERLOADED_CABLE;
    @ObjectHolder(PhoenixTales.MOD_ID + ":press_tile")
    public static TileEntityType<PressTile> PRESS_TILE;
    @ObjectHolder(PhoenixTales.MOD_ID + ":alloy_tile")
    public static TileEntityType<AlloyTile> ALLOY_TILE;
    @ObjectHolder(PhoenixTales.MOD_ID + ":assembler_tile")
    public static TileEntityType<AssemblerTile> ASSEMBLER_TILE;
    @ObjectHolder(PhoenixTales.MOD_ID + ":energy_store_tile")
    public static TileEntityType<EnergyStoreTile> ENERGY_STORE_TILE;
    @ObjectHolder(PhoenixTales.MOD_ID+":engineers_anvil")
    public static TileEntityType<EngineersAnvilTile> ENGINEERS_ANVIL;
    @ObjectHolder(PhoenixTales.MOD_ID + ":tank_tile")
    public static TileEntityType<TankTile> TANK_TILE;

    @ObjectHolder(PhoenixTales.MOD_ID + ":test")
    public static TileEntityType<TestCableTile> TEST;

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registry {

        @SubscribeEvent
        public static void register(RegistryEvent.Register<TileEntityType<?>> event) {
            IForgeRegistry<TileEntityType<?>> r = event.getRegistry();
//            r.register(TileEntityType.Builder.create(SimpleCableTile::new, RiseBlocks.SIMPLE_CABLE).build(null).setRegistryName("simple_cable"));
//            r.register(TileEntityType.Builder.create(CableTile::new, RiseBlocks.CABLE).build(null).setRegistryName("cable_tile"));
//            r.register(TileEntityType.Builder.create(AdvancedCableTile::new, RiseBlocks.ADVANCED_CABLE).build(null).setRegistryName("advanced_cable"));
//            r.register(TileEntityType.Builder.create(OverloadedCableTile::new, RiseBlocks.OVERLOADED_CABLE).build(null).setRegistryName("overloaded_cable"));
            r.register(TileEntityType.Builder.create(PressTile::new, RiseBlocks.PRESS_FACTORY).build(null).setRegistryName("press_tile"));
            r.register(TileEntityType.Builder.create(AlloyTile::new, RiseBlocks.ALLOY_FACTORY).build(null).setRegistryName("alloy_tile"));
            r.register(TileEntityType.Builder.create(AssemblerTile::new, RiseBlocks.ASSEMBLER).build(null).setRegistryName("assembler_tile"));
            r.register(TileEntityType.Builder.create(EnergyStoreTile::new, RiseBlocks.ENERGY_STORE).build(null).setRegistryName("energy_store_tile"));
            r.register(TileEntityType.Builder.create(EngineersAnvilTile::new, RiseBlocks.ENGINEERS_ANVIL).build(null).setRegistryName("engineers_anvil"));
            r.register(TileEntityType.Builder.create(TankTile::new, RiseBlocks.TANK).build(null).setRegistryName("tank_tile"));

            r.register(TileEntityType.Builder.create(TestCableTile::new, RiseBlocks.TEST).build(null).setRegistryName("test"));
        }

//    @SuppressWarnings("deprecation")
//    private static <T extends TileEntity> TileEntityType<T> createTile(String id, TileEntityType<T> tile) {
//        ResourceLocation location = new ResourceLocation(PhoenixTales.MOD_ID, id);
//        if (Registry.BLOCK_ENTITY_TYPE.keySet().contains(location)) {
//            throw new IllegalStateException(location.toString() + " already exists!");
//        }
//        tile.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
//        tiles.add(tile);
//        return tile;
//    }
    }
}
