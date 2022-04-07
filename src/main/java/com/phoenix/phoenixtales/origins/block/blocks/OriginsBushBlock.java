package com.phoenix.phoenixtales.origins.block.blocks;

import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class OriginsBushBlock extends BushBlock {
    public OriginsBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.matchesBlock(OriginsBlocks.SEARING_GRASS_BLOCK) || state.matchesBlock(OriginsBlocks.SEARING_DIRT) || state.matchesBlock(OriginsBlocks.ASHEN_GRASS_BLOCK) || state.matchesBlock(OriginsBlocks.ASHEN_DIRT) || state.matchesBlock(Blocks.GRAVEL);
    }


}
