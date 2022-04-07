package com.phoenix.phoenixtales.core.creativetab;

import com.phoenix.phoenixtales.origins.item.OriginsItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TalesTabItem extends ItemGroup {

    public static final TalesTabItem TAB_ITEM = new TalesTabItem(ItemGroup.GROUPS.length, "tab_item");

    public TalesTabItem(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(OriginsItems.SULFUR);
    }
}