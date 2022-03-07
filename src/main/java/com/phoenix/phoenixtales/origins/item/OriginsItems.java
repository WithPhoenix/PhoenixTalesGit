package com.phoenix.phoenixtales.origins.item;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.core.creativetab.BlockTab;
import com.phoenix.phoenixtales.core.creativetab.ItemTab;
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

    public static final Item CreativeTabItem = createItem("cti", new Item(new Item.Properties()));
    public static final Item CreativeTabBlock = createItem("ctblock", new Item(new Item.Properties()));

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final Item DECO_PORTAL = createItem("deco_portal", new DecoPortItem());
    public static final Item SMOULDERING_STONE = createItem("smouldering_stone", new OriginsBlockItem(OriginsBlocks.SMOULDERING_STONE, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item ASH = createItem("ash", new OriginsBlockItem(OriginsBlocks.ASH, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item CRYSTAL = createItem("crystal", new OriginsBlockItem(OriginsBlocks.CRYSTAL, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item HUO_LOG = createItem("huo_log", new OriginsBlockItem(OriginsBlocks.HUO_LOG, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item HUO_PLANKS = createItem("huo_planks", new OriginsBlockItem(OriginsBlocks.HUO_PLANKS, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item HUO_STAIRS = createItem("huo_stairs", new OriginsBlockItem(OriginsBlocks.HUO_STAIRS, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item HUO_SLAB = createItem("huo_slab", new OriginsBlockItem(OriginsBlocks.HUO_SLAB, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item HUO_DOOR = createItem("huo_door", new OriginsBlockItem(OriginsBlocks.HUO_DOOR, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item HUO_TRAPDOOR = createItem("huo_trapdoor", new OriginsBlockItem(OriginsBlocks.HUO_TRAPDOOR, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    public static final Item HUO_LEAVES = createItem("huo_leaves", new OriginsBlockItem(OriginsBlocks.HUO_LEAVES, new Item.Properties().group(BlockTab.BLOCK_GROUP)));
    //    public static final Item HUO_SAPLING = createItem("huo_sapling", new OriginsBlockItem(OriginsBlocks.HUO_SAPLING, new Item.Properties().group(BlockTab.ITEM_GROUP)));
    public static final Item ORE_SULFUR_OVERWORLD = createItem("sulfur_ore", new OriginsBlockItem(OriginsBlocks.ORE_SULFUR_OVERWORLD, new Item.Properties().group(BlockTab.BLOCK_GROUP)));

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static final Item GLASS_SHARD = createItem("glass_shard", new OriginsItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item SULFUR = createItem("sulfur", new Sulfur());

    private static Item createItem(String id, Item item) {
        item.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        items.add(item);
        return item;
    }
}
