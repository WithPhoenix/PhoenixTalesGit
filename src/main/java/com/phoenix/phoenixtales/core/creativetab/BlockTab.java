package com.phoenix.phoenixtales.core.creativetab;

import com.phoenix.phoenixtales.origins.item.OriginsItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class BlockTab extends ItemGroup {

    public static final BlockTab BLOCK_GROUP = new BlockTab(ItemGroup.GROUPS.length, "origins_block_group");

    public BlockTab(int index, String label) {
        super(index, label);
//        setBackgroundImageName("phoenixtech.png");
    }

    @Nonnull
    @Override
    public ItemStack createIcon() {
        return new ItemStack(OriginsItems.SMOULDERING_STONE);
    }

}
