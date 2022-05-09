package com.phoenix.phoenixtales.rise.item.items;

import com.phoenix.phoenixtales.core.creativetab.TalesTabItem;
import com.phoenix.phoenixtales.rise.RiseItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MetalSaw extends RiseItem {
    public MetalSaw() {
        super(new Properties().group(TalesTabItem.TAB_ITEM).maxDamage(150));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (Screen.hasControlDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.phoenixtales.saw_ctrl"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.phoenixtales.saw"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if (container.attemptDamageItem(1, random, null)) {
            return ItemStack.EMPTY;
        } else {
            return container;
        }
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}

