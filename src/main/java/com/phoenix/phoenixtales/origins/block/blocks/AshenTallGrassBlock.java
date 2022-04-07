package com.phoenix.phoenixtales.origins.block.blocks;

import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class AshenTallGrassBlock extends TallGrassBlock {
    public AshenTallGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        DoublePlantBlock doubleplantblock = (DoublePlantBlock) (this == OriginsBlocks.ASHEN_FERN ? OriginsBlocks.LARGE_ASHEN_FERN : OriginsBlocks.TALL_ASHEN_GRASS);
        if (doubleplantblock.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
            doubleplantblock.placeAt(worldIn, pos, 2);
        }
    }
}
