package com.phoenix.phoenixtales.rise.item.items;

import com.phoenix.phoenixtales.core.creativetab.ItemTab;
import com.phoenix.phoenixtales.rise.RiseItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class Phosphorus extends RiseItem {
    public Phosphorus() {
        super(new Item.Properties().group(ItemTab.ITEM_GROUP));
    }

    //TODO implement the different types of phosphor and change the burn time also you have to put it into a special item (storage vessel? vial? phial?)
//can i use the bundle and change it a bit
    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType) {
        return 3000;
    }
}
