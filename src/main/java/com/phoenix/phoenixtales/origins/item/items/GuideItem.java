package com.phoenix.phoenixtales.origins.item.items;

import com.phoenix.phoenixtales.core.creativetab.ItemTab;
import com.phoenix.phoenixtales.core.guide.GuideScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GuideItem extends Item {
    public GuideItem() {
        super(new Item.Properties().group(ItemTab.ITEM_GROUP));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        GuideScreen screen = new GuideScreen();
        screen.openScreen();
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
