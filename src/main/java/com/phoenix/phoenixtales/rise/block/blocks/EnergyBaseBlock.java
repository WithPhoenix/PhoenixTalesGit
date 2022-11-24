package com.phoenix.phoenixtales.rise.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

//only to find the blocks handling energy
public abstract class EnergyBaseBlock extends Block {

    public static DirectionProperty FACING = BlockStateProperties.FACING;

//    public static final Map<Integer, IntegerProperty> DIRECTION_TO_FACING_MAP = Util.make(Maps.newHashMap(), (p) -> {
//        p.put(0, FRONT);
//        p.put(1, BACK);
//        p.put(2, LEFT);
//        p.put(3, RIGHT);
//        p.put(4, UP_D);
//        p.put(5, DOWN_D);
//    });

    public EnergyBaseBlock(Properties prop) {
        super(prop);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        Direction d = placer != null ?  placer.getHorizontalFacing().getOpposite() : Direction.NORTH;
        worldIn.setBlockState(pos, state.with(FACING, d));
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }
}
