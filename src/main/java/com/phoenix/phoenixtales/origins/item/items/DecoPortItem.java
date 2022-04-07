package com.phoenix.phoenixtales.origins.item.items;

import com.phoenix.phoenixtales.core.ClientSetup;
import com.phoenix.phoenixtales.core.creativetab.TalesTabBlock;
import com.phoenix.phoenixtales.origins.OriginsBlockItem;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.item.Item;

public class DecoPortItem extends OriginsBlockItem {
    public DecoPortItem() {
        super(OriginsBlocks.DECO_PORTAL, new Item.Properties().group(TalesTabBlock.TAB_BLOCK).setISTER(() -> ClientSetup::getDecoPortRenderer));
    }
}
