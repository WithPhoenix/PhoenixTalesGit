package com.phoenix.phoenixtales.rise.block.blocks;

import com.phoenix.phoenixtales.rise.service.RiseBlockStateProps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public abstract class ConduitBlock extends Block implements IWaterLoggable {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.5, 5.5, 5.5, 10.5, 10.5, 10.5);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty CONNECTED = RiseBlockStateProps.CONNECTED;
    private final BlockState optionalState;

    public ConduitBlock(Properties p_i48355_2_) {
        super(p_i48355_2_);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(false)).with(CONNECTED, Boolean.valueOf(false)));
        this.optionalState = this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(false)).with(CONNECTED, Boolean.valueOf(true));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IBlockReader iblockreader = context.getWorld();
        BlockPos blockpos = context.getPos();
        FluidState fluidstate = context.getWorld().getFluidState(blockpos);
        BlockState blockstate = this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(CONNECTED, Boolean.valueOf(shouldConnect(iblockreader, blockpos)));
        return blockstate;
    }

    protected boolean shouldConnect(IBlockReader reader, BlockPos pos) {
        return false;
    }

    //TODO update connected
    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos pos2, boolean b) {
        super.neighborChanged(state, world, pos, block, pos2, b);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(WATERLOGGED, CONNECTED);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos
            p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public boolean canContainFluid(IBlockReader p_204510_1_, BlockPos p_204510_2_, BlockState p_204510_3_, Fluid
            p_204510_4_) {
        return IWaterLoggable.super.canContainFluid(p_204510_1_, p_204510_2_, p_204510_3_, p_204510_4_);
    }

    @Override
    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        return IWaterLoggable.super.receiveFluid(worldIn, pos, state, fluidStateIn);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return shouldConnect(worldIn, currentPos) ? this.optionalState : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        switch (type) {
            case WATER:
                return worldIn.getFluidState(pos).isTagged(FluidTags.WATER);
            case AIR:
            case LAND:
            default:
                return false;
        }
    }
}
