package com.phoenix.phoenixtales.rise.item.items;

import com.phoenix.phoenixtales.core.creativetab.ItemTab;
import com.phoenix.phoenixtales.rise.item.RiseItem;
import net.minecraft.item.ItemStack;

public class SolderingIronItem extends RiseItem {
    public SolderingIronItem() {
        super(new Properties().group(ItemTab.ITEM_GROUP).maxDamage(300));
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        if (stack.attemptDamageItem(1, random, null)) {
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
