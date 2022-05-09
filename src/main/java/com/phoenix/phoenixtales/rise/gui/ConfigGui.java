package com.phoenix.phoenixtales.rise.gui;

import com.phoenix.phoenixtales.rise.block.RiseContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ConfigGui extends Container implements INamedContainerProvider {


    protected ConfigGui(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    public ConfigGui(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(RiseContainers.CONFIG_GUI, windowId);
    }


    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("screen.phoenixtales.config");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
//        return new ConfigGui(p_createMenu_1_, p_createMenu_3_.getEntityWorld(), p_createMenu_3_.getPosition(), p_createMenu_2_, p_createMenu_3_);
        return this;
    }
}
