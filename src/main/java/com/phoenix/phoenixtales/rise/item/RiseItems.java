package com.phoenix.phoenixtales.rise.item;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.core.creativetab.BlockTab;
import com.phoenix.phoenixtales.core.creativetab.ItemTab;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.item.items.ClayAndGravelItem;
import com.phoenix.phoenixtales.rise.item.items.Hammer;
import com.phoenix.phoenixtales.rise.item.items.MetalSaw;
import com.phoenix.phoenixtales.rise.item.items.Phosphorus;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

public class RiseItems {
    //add Objects to list and register afterwards
    public static List<Item> items = new ArrayList<>();

    // block items
    public static final Item ORE_VANADIUM = createItem("vanadium_ore", new RiseBlockItem(RiseBlocks.ORE_VANADIUM, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item ORE_NICKEL = createItem("nickel_ore", new RiseBlockItem(RiseBlocks.ORE_NICKEL, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item ORE_TIN = createItem("tin_ore", new RiseBlockItem(RiseBlocks.ORE_TIN, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item ORE_COPPER = createItem("copper_ore", new RiseBlockItem(RiseBlocks.ORE_COPPER, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item ORE_APATITE = createItem("apatite_ore", new RiseBlockItem(RiseBlocks.ORE_APATITE, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item ORE_KERNITE = createItem("kernite_ore", new RiseBlockItem(RiseBlocks.ORE_KERNITE, new Item.Properties().group(BlockTab.BLOCK_GROUP)));

    public static final Item BLOCK_STEEL = createItem("steel_block", new RiseBlockItem(RiseBlocks.BLOCK_STEEL, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item BLOCK_VANADIUM = createItem("vanadium_block", new RiseBlockItem(RiseBlocks.BLOCK_VANADIUM, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item BLOCK_NICKEL = createItem("nickel_block", new RiseBlockItem(RiseBlocks.BLOCK_NICKEL, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item BLOCK_INVAR = createItem("invar_block", new RiseBlockItem(RiseBlocks.BLOCK_INVAR, new Item.Properties().group(BlockTab.BLOCK_GROUP)));

    public static final Item ENGINEERS_ANVIL = createItem("engineers_anvil", new RiseBlockItem(RiseBlocks.ENGINEERS_ANVIL, new Item.Properties().group(BlockTab.BLOCK_GROUP)));

    public static final Item STEEL_CHASSIS = createItem("steel_chassis", new RiseBlockItem(RiseBlocks.STEEL_CHASSIS, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item VANADIUM_CHASSIS = createItem("vanadium_chassis", new RiseBlockItem(RiseBlocks.VANADIUM_CHASSIS, new Item.Properties().group(BlockTab.BLOCK_GROUP)));

    public static final Item CERAMIC_BLOCK = createItem("ceramic_block", new RiseBlockItem(RiseBlocks.CERAMIC_BLOCK, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item BURNT_CERAMIC_BLOCK = createItem("burnt_ceramic_block", new RiseBlockItem(RiseBlocks.BURNT_CERAMIC_BLOCK, new Item.Properties().group(BlockTab.BLOCK_GROUP)));

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // items
    public static final Item CLAY_AND_SAND = createItem("clay_and_sand", new ClayAndGravelItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));

    //elements and minerals
    public static final Item APATITE = createItem("apatite", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item KERNITE = createItem("kernite", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item PHOSPHORUS = createItem("red_phosphorus", new Phosphorus());
    public static final Item BORON = createItem("boron", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item SILICON = createItem("silicon", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item P_DOPED_SILICON = createItem("p_doped_silicon", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item N_DOPED_SILICON = createItem("n_doped_silicon", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));

    //steel based
    public static final Item INGOT_STEEL = createItem("steel_ingot", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item PLATE_STEEL = createItem("steel_plate", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item STEEL_ROD = createItem("steel_rod", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));

    //vanadium based
    public static final Item INGOT_VANADIUM = createItem("vanadium_ingot", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item NUGGET_VANADIUM = createItem("vanadium_nugget", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item VANADIUM_PLATE = createItem("vanadium_plate", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item VANADIUM_MACHINE_PART = createItem("vanadium_part", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    //nickel based
    public static final Item INGOT_NICKEL = createItem("nickel_ingot", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item NUGGET_NICKEL = createItem("nickel_nugget", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item NICKEL_PLATE = createItem("nickel_plate", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item NICKEL_MACHINE_PART = createItem("nickel_part", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    //invar based
    public static final Item INGOT_INVAR = createItem("invar_ingot", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item NUGGET_INVAR = createItem("invar_nugget", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item INVAR_PLATE = createItem("invar_plate", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item INVAR_ROD = createItem("invar_rod", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    //tin based
    public static final Item TIN_SOLDER = createItem("tin_solder", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));

    public static final Item METAL_SAW = createItem("metal_saw", new MetalSaw());
    public static final Item HAMMER = createItem("hammer", new Hammer());
    public static final Item CONFIGURATOR = createItem("configurator", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));

    //tech related
    public static final Item TRANSISTOR = createItem("transistor", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item ENHANCED_REDSTONE = createItem("enhanced_redstone", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    //this one is made with enhanced redstone at the soldering table
    public static final Item REDSTONE_CONTROLLER = createItem("redstone_controller", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    //these are made with silicon
    public static final Item SIMPLE_CONTROLLER = createItem("simple_controller", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item ADVANCED_CONTROLLER = createItem("advanced_controller", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item OVERLOADED_CONTROLLER = createItem("overloaded_controller", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));

    public static final Item MECHANICAL_ARM = createItem("mechanical_arm", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    //mechanical arm with two steel plates?
    public static final Item REINFORCED_MECHANICAL_ARM = createItem("steel_reinforced_mechanical_arm", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));

    public static final Item CERAMIC_BLEND = createItem("ceramic_blend", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item CERAMIC = createItem("ceramic", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item SLAG = createItem("slag", new RiseItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //    public static final Item SIMPLE_CABLE = createItem("simple_cable", new RiseBlockItem(RiseBlocks.SIMPLE_CABLE, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
//    public static final Item CABLE = createItem("cable", new RiseBlockItem(RiseBlocks.CABLE, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
//    public static final Item ADVANCED_CABLE = createItem("advanced_cable", new RiseBlockItem(RiseBlocks.ADVANCED_CABLE, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
//    public static final Item OVERLOADED_CABLE = createItem("overloaded_cable", new RiseBlockItem(RiseBlocks.OVERLOADED_CABLE, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item ENERGY_STORE = createItem("energy_store", new RiseBlockItem(RiseBlocks.ENERGY_STORE, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item PRESS_FACTORY = createItem("press_factory", new RiseBlockItem(RiseBlocks.PRESS_FACTORY, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item ALLOY_FACTORY = createItem("alloy_factory", new RiseBlockItem(RiseBlocks.ALLOY_FACTORY, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item ASSEMBLER = createItem("assembler", new RiseBlockItem(RiseBlocks.ASSEMBLER, new Item.Properties().group(BlockTab.BLOCK_GROUP)));

    public static final Item TANK = createItem("tank", new RiseBlockItem(RiseBlocks.TANK, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
//    public static final Item HUO_BOAT = createItem("huo_boat", new HuoBoatItem(new Item.Properties().group(ItemTab.ITEM_GROUP), "huo"));
//    public static final Item BAD_BEAT_MUSIC_DISC = createItem("bad_beat_music_disc", new MusicDiscItem(1, () -> TechSoundEvents.BAD_BEAT.get(), new Item.Properties().maxStackSize(1).group(ItemTab.ITEM_GROUP)));

    private static Item createItem(String id, Item item) {
        item.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        items.add(item);
        return item;
    }
}
