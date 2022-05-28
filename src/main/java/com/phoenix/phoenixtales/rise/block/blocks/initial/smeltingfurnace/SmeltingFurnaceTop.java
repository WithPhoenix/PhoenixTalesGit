package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace;

import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.tile.SmeltingTileUpper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;


@SuppressWarnings("deprecation")
public class SmeltingFurnaceTop extends SmeltingFurnace {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public SmeltingFurnaceTop() {
        super();
        this.setDefaultState(this.stateContainer.getBaseState().with(LIT, Boolean.valueOf(false)));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SmeltingTileUpper();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (SmeltingFurnace.isBuild(state, pos, worldIn)) {
            if (worldIn.getTileEntity(pos) instanceof SmeltingTileUpper) {
                SmeltingTileUpper tile = (SmeltingTileUpper) worldIn.getTileEntity(pos);
                if (tile == null) return ActionResultType.FAIL;
                ItemStack item = player.getHeldItem(handIn);
                if (item.getItem() == Items.CHARCOAL) {
                    if (tile.canInsert(true)) {
                        tile.addCoal(1);
                        if (!player.abilities.isCreativeMode) {
                            item.shrink(1);
                        }
                    }
                } else if (item.getItem() == Items.IRON_INGOT) {
                    if (tile.canInsert(false)) {
                        tile.addIron(1);
                        if (!player.abilities.isCreativeMode) {
                            item.shrink(1);
                        }
                    }
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }
        if (worldIn.getBlockState(pos.down()).matchesBlock(RiseBlocks.SMELTING_FURNACE_BOTTOM)) {
            worldIn.destroyBlock(pos.down(), false);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(LIT)) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY();
            double d2 = (double) pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D) {
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_SMOKER_SMOKE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            worldIn.addParticle(ParticleTypes.LARGE_SMOKE, d0, d1 + 1.1D, d2, 0.0D, 0.0D, 0.0D);
            worldIn.addParticle(ParticleTypes.FLAME, d0, d1 + 1.1D, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(LIT, Boolean.valueOf(false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
