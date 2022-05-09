package com.phoenix.phoenixtales.fall.block.alembic;

import com.phoenix.phoenixtales.fall.item.FallItems;
import com.phoenix.phoenixtales.fall.service.FallBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class Alembic extends Block {
    public static final IntegerProperty PROGRESS = FallBlockStateProperties.PROGRESS;
    private final Item[] partOrder = {FallItems.STILL, FallItems.TUBE, FallItems.WEAVE, FallItems.ROTTEN_HEART};

    public Alembic() {
        super(Properties.create(Material.ROCK, MaterialColor.CLAY).hardnessAndResistance(2.0f, 2.0f).sound(SoundType.STONE));
        this.setDefaultState(this.getStateContainer().getBaseState().with(PROGRESS, Integer.valueOf(0)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int i = state.get(PROGRESS);
        if (i < 4) {
            //the setup is not finished
            ItemStack stack = player.getHeldItem(handIn);
            //check if
            if (this.isNextPart(state, stack)) {
                BlockState blockState = this.addPart(state, worldIn, pos);
                if (!player.abilities.isCreativeMode) {
                    stack.shrink(1);
                }
            }
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        } else {
            //now access to the tile
        }
        return ActionResultType.SUCCESS;
    }

    private boolean isNextPart(BlockState state, ItemStack stack) {
        int i = state.get(PROGRESS);
        Item item = stack.getItem();
        return item == this.partOrder[i];
    }

    private BlockState addPart(BlockState state, IWorld world, BlockPos pos) {
        int i = state.get(PROGRESS) + 1;
        BlockState blockState = state.with(PROGRESS, Integer.valueOf(i));
        world.setBlockState(pos, blockState, 3);
        return blockState;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }
        NonNullList<ItemStack> parts = NonNullList.withSize(4, ItemStack.EMPTY);
        for (int i = 0; i < state.get(PROGRESS); i++) {
            parts.set(i, this.partOrder[i].getDefaultInstance());
        }
        InventoryHelper.dropItems(worldIn, pos, parts);
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(PROGRESS);
    }
}
