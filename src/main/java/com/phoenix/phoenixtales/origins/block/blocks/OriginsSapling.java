package com.phoenix.phoenixtales.origins.block.blocks;

import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import com.phoenix.phoenixtales.origins.world.tree.TalesTree;
import net.minecraft.block.*;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;


@SuppressWarnings("deprecation")
public class OriginsSapling extends BushBlock implements IGrowable {
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    private final TalesTree tree;

    public OriginsSapling(TalesTree tree, AbstractBlock.Properties properties) {
        super(properties);
        this.tree = tree;
        this.setDefaultState(this.getStateContainer().getBaseState().with(STAGE, Integer.valueOf(0)));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.getLight(pos.up()) >= 5 && random.nextInt(7) == 0) {
            if (!worldIn.isAreaLoaded(pos, 1)) return;
            this.placeTree(worldIn, pos, state, random);
        }
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.matchesBlock(OriginsBlocks.ASHEN_DIRT) || state.matchesBlock(OriginsBlocks.ASHEN_GRASS_BLOCK)
                || state.matchesBlock(OriginsBlocks.SEARING_DIRT) || state.matchesBlock(OriginsBlocks.SEARING_GRASS_BLOCK)
                || super.isValidGround(state, worldIn, pos);
    }

    public void placeTree(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        if (state.get(STAGE) == 0) {
            world.setBlockState(pos, state.cycleValue(STAGE), 4);
        } else {
            this.tree.attemptGrow(world, world.getChunkProvider().getChunkGenerator(), pos, state, random);
        }
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return (double) worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        this.placeTree(worldIn, pos, state, rand);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }
}
