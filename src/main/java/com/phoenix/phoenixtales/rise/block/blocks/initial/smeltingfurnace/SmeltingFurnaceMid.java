package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace;

import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SmeltingFurnaceMid extends SmeltingFurnace {
    public SmeltingFurnaceMid() {
        super();
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }
        if (worldIn.getBlockState(pos.up()).matchesBlock(RiseBlocks.SMELTING_FURNACE_TOP)) {
            worldIn.destroyBlock(pos.up(), true);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }
}
