package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public abstract class SmeltingFurnace extends Block {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public SmeltingFurnace() {
        super(Properties.create(Material.ROCK, MaterialColor.ADOBE).setRequiresTool().hardnessAndResistance(1.25F, 4.2F));
        this.setDefaultState(this.stateContainer.getBaseState().with(LIT, Boolean.valueOf(false)));
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }
//        TileEntity tileentity = worldIn.getTileEntity(pos);
//        if (tileentity instanceof SmeltingFurnaceTile) {
//
//        }

        super.onReplaced(state, worldIn, pos, newState, isMoving);
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

    public static boolean isBuild(BlockState state, BlockPos pod, World world) {
       if(state.getBlock() instanceof SmeltingFurnaceBottom){
         
}else if (state.getBlock() instance SmeltingFurnaceMid ){

}else if(state.getBlock() instance SmeltingFurnaceTop) {
}
        return false;
    }
}
