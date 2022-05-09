package com.phoenix.phoenixtales.fall.item;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.core.creativetab.ItemTab;
import com.phoenix.phoenixtales.fall.FallItem;
import com.phoenix.phoenixtales.rise.item.items.RottenHeart;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class FallItems {

    //add Objects to list and register afterwards
    public static List<Item> items = new ArrayList<>();

    public static final Item ROTTEN_HEART = createItem("rotten_heart", new RottenHeart());

    public static final Item TUBE = createItem("tube", new FallItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item STILL = createItem("still", new FallItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));
    public static final Item WEAVE = createItem("weave", new FallItem(new Item.Properties().group(ItemTab.ITEM_GROUP)));

    public static Item createItem(String id, Item item) {
        item.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        items.add(item);
        return item;
    }
}
