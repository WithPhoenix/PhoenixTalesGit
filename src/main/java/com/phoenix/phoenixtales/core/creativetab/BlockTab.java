package com.phoenix.phoenixtales.core.creativetab;

import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BlockTab extends ItemGroup {

    public static final BlockTab BLOCK_GROUP = new BlockTab(ItemGroup.GROUPS.length, "tab_block");

    public BlockTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(OriginsBlocks.REALMSTONE);
    }

}
