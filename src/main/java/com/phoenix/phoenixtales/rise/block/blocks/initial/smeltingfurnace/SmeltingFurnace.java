package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace;

import com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.tile.SmeltingFurnaceTile;
import com.phoenix.phoenixtales.rise.service.RiseBlockStateProps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public abstract class SmeltingFurnace extends Block {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final IntegerProperty BUILD = RiseBlockStateProps.BUILD_1_4;

    public SmeltingFurnace() {
        super(Properties.create(Material.ROCK, MaterialColor.ADOBE).setRequiresTool().notSolid().hardnessAndResistance(1.25F, 4.2F));
        this.setDefaultState(this.stateContainer.getBaseState().with(LIT, Boolean.valueOf(false)).with(BUILD, Integer.valueOf(1)));
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof SmeltingFurnaceTile) {
            InventoryHelper.dropItems(worldIn, pos, ((SmeltingFurnaceTile) tileentity).getItems());
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(LIT, Boolean.valueOf(false)).with(BUILD, Integer.valueOf(1));
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosionIn) {
        return false;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LIT, BUILD);
    }

    public static boolean isBuild(BlockState state, BlockPos pos, World world) {
        if (state.getBlock() instanceof SmeltingFurnaceBottom) {
            BlockState top = world.getBlockState(pos.up());
            return top.getBlock() instanceof SmeltingFurnaceTop;
        } else if (state.getBlock() instanceof SmeltingFurnaceTop) {
            BlockState bot = world.getBlockState(pos.down());
            return bot.getBlock() instanceof SmeltingFurnaceBottom;
        }
        return false;
    }
}
