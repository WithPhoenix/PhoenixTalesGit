package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace;

import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings("deprecation")
public class SmeltingFurnaceBottom extends SmeltingFurnace {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public SmeltingFurnaceBottom() {
        super();
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIT, Boolean.valueOf(false)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (SmeltingFurnace.isBuild(state, pos, worldIn)) {
            ItemStack item = player.getHeldItem(handIn);
            if (item.getItem() instanceof FlintAndSteelItem) {
                worldIn.setBlockState(pos, state.with(SmeltingFurnace.LIT, Boolean.valueOf(true)));
                item.damageItem(2, player, p -> p.sendBreakAnimation(handIn));
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
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

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.animateTick(stateIn, worldIn, pos, rand);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }
}
