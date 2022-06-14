package com.phoenix.phoenixtales.rise.block.blocks;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
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
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@SuppressWarnings("deprecation")
public abstract class ConduitBlock extends Block implements IWaterLoggable {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.5, 5.5, 5.5, 10.5, 10.5, 10.5);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty EAST = BooleanProperty.create("east");

    public static final Map<Direction, VoxelShape> FACING_TO_SHAPE_MAP = Util.make(Maps.newEnumMap(Direction.class), (p) -> {
        p.put(Direction.NORTH, Block.makeCuboidShape(6.5, 6.5, 0, 9.5, 9.5, 5.5));
        p.put(Direction.SOUTH,Block.makeCuboidShape(6.5, 6.5, 10.5, 9.5, 9.5, 16.0));
        p.put(Direction.WEST, Block.makeCuboidShape(0, 6.5, 6.5, 5.5, 9.5, 9.5));
        p.put(Direction.EAST, Block.makeCuboidShape(10.5, 6.5, 6.5, 16.0, 9.5, 9.5));
        p.put(Direction.DOWN, Block.makeCuboidShape(6.5, 0, 6.5, 9.5, 5.5, 9.5));
        p.put(Direction.UP, Block.makeCuboidShape(6.5, 10.5, 6.5, 9.5, 16.0, 9.5));
    });

    public static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (p) -> {
        p.put(Direction.NORTH, NORTH);
        p.put(Direction.SOUTH, SOUTH);
        p.put(Direction.WEST, WEST);
        p.put(Direction.EAST, EAST);
        p.put(Direction.DOWN, DOWN);
        p.put(Direction.UP, UP);
    });

    public ConduitBlock(Properties p_i48355_2_) {
        super(p_i48355_2_);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(false)).with(DOWN, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false)).with(NORTH, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false)).with(WEST, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return getState(context.getWorld(), context.getPos(), null);
    }

    private BlockState getState(World world, BlockPos pos, BlockState current) {
        FluidState fluidState = world.getFluidState(pos);
        return this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER)
                .with(NORTH, isConnected(world, pos, Direction.NORTH))
                .with(SOUTH, isConnected(world, pos, Direction.SOUTH))
                .with(WEST, isConnected(world, pos, Direction.WEST))
                .with(EAST, isConnected(world, pos, Direction.EAST))
                .with(DOWN, isConnected(world, pos, Direction.DOWN))
                .with(UP, isConnected(world, pos, Direction.UP));
    }

    public boolean isConnected(IWorldReader world, BlockPos pos, Direction facing) {
        return isConduit(world, pos, facing) || connectsTo(world, pos, facing);
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos pos2, boolean b) {
        super.neighborChanged(state, world, pos, block, pos2, b);
        BlockState stateNew = getState(world, pos, state);
        if (!state.getProperties().stream().allMatch(property -> state.get(property).equals(stateNew.get(property)))) {
            world.setBlockState(pos, stateNew);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(WATERLOGGED, NORTH, SOUTH, WEST, EAST, DOWN, UP);
    }

    protected abstract boolean isConduit(IWorldReader world, BlockPos pos, Direction facing);

    protected abstract boolean connectsTo(IWorldReader world, BlockPos pos, Direction facing);

    @Override
    public VoxelShape getShape(BlockState stateIn, IBlockReader reader, BlockPos posIn, ISelectionContext context) {
        VoxelShape shape = SHAPE;
        for (Direction direction : Direction.values()) {
            if (stateIn.get(FACING_TO_PROPERTY_MAP.get(direction))) {
                shape = VoxelShapes.combineAndSimplify(FACING_TO_SHAPE_MAP.get(direction), shape, IBooleanFunction.OR);
            }
        }
        return shape;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public boolean canContainFluid(IBlockReader p_204510_1_, BlockPos p_204510_2_, BlockState p_204510_3_, Fluid p_204510_4_) {
        return IWaterLoggable.super.canContainFluid(p_204510_1_, p_204510_2_, p_204510_3_, p_204510_4_);
    }

    @Override
    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        return IWaterLoggable.super.receiveFluid(worldIn, pos, state, fluidStateIn);
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }


    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.BLOCK;
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
