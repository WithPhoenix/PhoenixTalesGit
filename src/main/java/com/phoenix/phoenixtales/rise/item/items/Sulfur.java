package com.phoenix.phoenixtales.rise.item.items;

import com.phoenix.phoenixtales.core.creativetab.ItemTab;
import com.phoenix.phoenixtales.origins.OriginsItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;

import javax.annotation.Nullable;

public class Sulfur extends OriginsItem {
    public Sulfur() {
        super(new Item.Properties().group(ItemTab.ITEM_GROUP));
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType) {
        return 2800;
    }
}
