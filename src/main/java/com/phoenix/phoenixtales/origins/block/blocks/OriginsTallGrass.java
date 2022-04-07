package com.phoenix.phoenixtales.origins.block.blocks;

import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class OriginsTallGrass extends OriginsBushBlock implements IGrowable, net.minecraftforge.common.IForgeShearable {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);


    public OriginsTallGrass(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    /**
     * Whether this IGrowable can grow
     */
    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {

        if (this == OriginsBlocks.SEARING_FERN || this == OriginsBlocks.ASHEN_FERN) {
            OriginsDoublePlant doubleplantblock = (OriginsDoublePlant) (this == OriginsBlocks.SEARING_FERN ? OriginsBlocks.LARGE_SEARING_FERN : OriginsBlocks.LARGE_ASHEN_FERN);
            if (doubleplantblock.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
                doubleplantblock.placeAt(worldIn, pos, 2);
            }
        } else if (this == OriginsBlocks.SEARING_GRASS || this == OriginsBlocks.ASHEN_GRASS) {
            OriginsDoublePlant doubleplantblock = (OriginsDoublePlant) (this == OriginsBlocks.SEARING_GRASS ? OriginsBlocks.TALL_SEARING_GRASS : OriginsBlocks.TALL_ASHEN_GRASS);
            if (doubleplantblock.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
                doubleplantblock.placeAt(worldIn, pos, 2);
            }
        }
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    @Override
    public AbstractBlock.OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.XYZ;
    }
}
