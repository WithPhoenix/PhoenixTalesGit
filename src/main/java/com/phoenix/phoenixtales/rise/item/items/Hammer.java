package com.phoenix.phoenixtales.rise.item.items;

import com.phoenix.phoenixtales.core.creativetab.ItemTab;
import com.phoenix.phoenixtales.rise.item.RiseItem;
import net.minecraft.item.ItemStack;

public class Hammer extends RiseItem {
    public Hammer() {
        super(new Properties().group(ItemTab.ITEM_GROUP).maxDamage(300));
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        if (stack.attemptDamageItem(5, random, null)) {
            return ItemStack.EMPTY;
        } else {
            return stack;
        }
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
