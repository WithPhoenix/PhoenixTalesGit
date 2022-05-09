package com.phoenix.phoenixtales.core.creativetab;

import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TalesTabBlock extends ItemGroup {

    public static final TalesTabBlock TAB_BLOCK = new TalesTabBlock(ItemGroup.GROUPS.length, "tab_block");

    public TalesTabBlock(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(OriginsBlocks.REALMSTONE);
    }

}
