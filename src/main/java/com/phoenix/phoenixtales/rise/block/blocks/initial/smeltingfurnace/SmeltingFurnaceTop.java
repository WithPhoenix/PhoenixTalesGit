package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;


@SuppressWarnings("deprecation")
public class SmeltingFurnaceTop extends SmeltingFurnace {
    public SmeltingFurnaceTop() {
        super();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (SmeltingFurnace.isBuild(state, pos, worldIn)) {
            ItemStack item = player.getHeldItem(handIn);
            //TODO insert items here, charcoal, iron, charcoal, iron ...
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

}
