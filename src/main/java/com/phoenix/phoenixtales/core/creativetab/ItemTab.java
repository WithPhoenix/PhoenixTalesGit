package com.phoenix.phoenixtales.core.creativetab;

import com.phoenix.phoenixtales.origins.item.OriginsItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemTab extends ItemGroup {

    public static final ItemTab ITEM_GROUP = new ItemTab(ItemGroup.GROUPS.length, "origins_item_group");

    public ItemTab(int index, String label) {
        super(index, label);
//        setBackgroundImageName("phoenixtech.png");
    }

    @Nonnull
    @Override
    public ItemStack createIcon() {
        return new ItemStack(OriginsItems.SULFUR);
    }

}