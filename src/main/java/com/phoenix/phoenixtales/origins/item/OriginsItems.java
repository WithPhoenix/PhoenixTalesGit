package com.phoenix.phoenixtales.origins.item;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.core.creativetab.TalesTabBlock;
import com.phoenix.phoenixtales.core.creativetab.TalesTabItem;
import com.phoenix.phoenixtales.origins.OriginsBlockItem;
import com.phoenix.phoenixtales.origins.OriginsItem;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import com.phoenix.phoenixtales.origins.item.items.DecoPortItem;
import com.phoenix.phoenixtales.rise.item.items.Sulfur;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class OriginsItems {

    public static List<Item> items = new ArrayList<>();

//    public static final Item CreativeTabItem = createItem("cti", new Item(new Item.Properties()));
//    public static final Item CreativeTabBlock = createItem("ctblock", new Item(new Item.Properties()));

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static final Item REALMSTONE = createItem("realmstone", new OriginsBlockItem(OriginsBlocks.REALMSTONE, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));

    public static final Item SEARING_STONE = createItem("searing_stone", new OriginsBlockItem(OriginsBlocks.SEARING_STONE, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item SEARING_COBBLESTONE = createItem("searing_cobblestone", new OriginsBlockItem(OriginsBlocks.SEARING_COBBLESTONE, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item SEARING_DIRT = createItem("searing_dirt", new OriginsBlockItem(OriginsBlocks.SEARING_DIRT, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item SEARING_GRASS_BLOCK = createItem("searing_grass_block", new OriginsBlockItem(OriginsBlocks.SEARING_GRASS_BLOCK, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));

    public static final Item ASHEN_STONE = createItem("ashen_stone", new OriginsBlockItem(OriginsBlocks.ASHEN_STONE, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item ASHEN_COBBLESTONE = createItem("ashen_cobblestone", new OriginsBlockItem(OriginsBlocks.ASHEN_COBBLESTONE, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item ASHEN_DIRT = createItem("ashen_dirt", new OriginsBlockItem(OriginsBlocks.ASHEN_DIRT, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item ASHEN_GRASS_BLOCK = createItem("ashen_grass_block", new OriginsBlockItem(OriginsBlocks.ASHEN_GRASS_BLOCK, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));

    public static final Item SEARING_GRASS = createItem("searing_grass", new OriginsBlockItem(OriginsBlocks.SEARING_GRASS, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item SEARING_FERN = createItem("searing_fern", new OriginsBlockItem(OriginsBlocks.SEARING_FERN, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item TALL_SEARING_GRASS = createItem("tall_searing_grass", new OriginsBlockItem(OriginsBlocks.TALL_SEARING_GRASS, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item LARGE_SEARING_FERN = createItem("large_searing_fern", new OriginsBlockItem(OriginsBlocks.LARGE_SEARING_FERN, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));

    public static final Item ASHEN_GRASS = createItem("ashen_grass", new OriginsBlockItem(OriginsBlocks.ASHEN_GRASS, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item ASHEN_FERN = createItem("ashen_fern", new OriginsBlockItem(OriginsBlocks.ASHEN_FERN, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item TALL_ASHEN_GRASS = createItem("tall_ashen_grass", new OriginsBlockItem(OriginsBlocks.TALL_ASHEN_GRASS, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item LARGE_ASHEN_FERN = createItem("large_ashen_fern", new OriginsBlockItem(OriginsBlocks.LARGE_ASHEN_FERN, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));

    public static final Item HUO_LOG = createItem("huo_log", new OriginsBlockItem(OriginsBlocks.HUO_LOG, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUO_PLANKS = createItem("huo_planks", new OriginsBlockItem(OriginsBlocks.HUO_PLANKS, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUO_STAIRS = createItem("huo_stairs", new OriginsBlockItem(OriginsBlocks.HUO_STAIRS, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUO_SLAB = createItem("huo_slab", new OriginsBlockItem(OriginsBlocks.HUO_SLAB, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUO_DOOR = createItem("huo_door", new OriginsBlockItem(OriginsBlocks.HUO_DOOR, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUO_TRAPDOOR = createItem("huo_trapdoor", new OriginsBlockItem(OriginsBlocks.HUO_TRAPDOOR, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUO_LEAVES = createItem("huo_leaves", new OriginsBlockItem(OriginsBlocks.HUO_LEAVES, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUU_SAPLING = createItem("huo_sapling", new OriginsBlockItem(OriginsBlocks.HUO_SAPLING, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));

    public static final Item HUI_LOG = createItem("hui_log", new OriginsBlockItem(OriginsBlocks.HUI_LOG, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUI_PLANKS = createItem("hui_planks", new OriginsBlockItem(OriginsBlocks.HUI_PLANKS, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUI_STAIRS = createItem("hui_stairs", new OriginsBlockItem(OriginsBlocks.HUI_STAIRS, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUI_SLAB = createItem("hui_slab", new OriginsBlockItem(OriginsBlocks.HUI_SLAB, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUI_DOOR = createItem("hui_door", new OriginsBlockItem(OriginsBlocks.HUI_DOOR, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUI_TRAPDOOR = createItem("hui_trapdoor", new OriginsBlockItem(OriginsBlocks.HUI_TRAPDOOR, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUI_LEAVES = createItem("hui_leaves", new OriginsBlockItem(OriginsBlocks.HUI_LEAVES, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item HUI_SAPLING = createItem("hui_sapling", new OriginsBlockItem(OriginsBlocks.HUI_SAPLING, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));

    public static final Item CHARRED_DEBRIS = createItem("charred_debris", new OriginsBlockItem(OriginsBlocks.CHARRED_DEBRIS, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));

    public static final Item ASH = createItem("ash", new OriginsBlockItem(OriginsBlocks.ASH, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));
    public static final Item CRYSTAL = createItem("crystal", new OriginsBlockItem(OriginsBlocks.CRYSTAL, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));

    public static final Item DECO_PORTAL = createItem("deco_portal", new DecoPortItem());

    public static final Item ORE_SULFUR_OVERWORLD = createItem("sulfur_ore", new OriginsBlockItem(OriginsBlocks.ORE_SULFUR_OVERWORLD, new Item.Properties().group(TalesTabBlock.TAB_BLOCK)));

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static final Item GLASS_SHARD = createItem("glass_shard", new OriginsItem(new Item.Properties().group(TalesTabItem.TAB_ITEM)));
    public static final Item SULFUR = createItem("sulfur", new Sulfur());

    private static Item createItem(String id, Item item) {
        item.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        items.add(item);
        return item;
    }
}
