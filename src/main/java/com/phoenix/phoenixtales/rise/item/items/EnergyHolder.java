package com.phoenix.phoenixtales.rise.item.items;

import com.phoenix.phoenixtales.rise.RiseItem;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnergyHolder extends RiseItem {
    public EnergyHolder(Properties properties) {
        super(properties);
    }

    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt) {
        return super.updateItemStackNBT(nbt);
    }


    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return false;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return super.getRGBDurabilityForDisplay(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }
}
