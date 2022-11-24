package com.phoenix.phoenixtales.rise.block.blocks;

import com.phoenix.phoenixtales.rise.service.enums.EnergyHandlingType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class EnergyBaseBlock extends Block {

    public static DirectionProperty FACING = BlockStateProperties.FACING;

    public EnergyBaseBlock(Properties prop) {
        super(prop);
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (stack.getTag() != null) {
            CompoundNBT config = stack.getTag().contains("config") ? stack.getTag().getCompound("config") : null;
            if (config != null) {
                EnergyBaseTile tile = (EnergyBaseTile) worldIn.getTileEntity(pos);
                for (int i = 0; i < 6; i++) {
                    tile.CONFIG.set(Direction.byIndex(i), config.contains(String.valueOf(i)) ? EnergyHandlingType.byIndex(Integer.parseInt((config.getString(String.valueOf(i))))) : EnergyHandlingType.NONE);
                }
            }
        }
        Direction d = placer != null ? placer.getHorizontalFacing().getOpposite() : Direction.NORTH;
        worldIn.setBlockState(pos, state.with(FACING, d));
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }
        EnergyBaseTile tile = (EnergyBaseTile) worldIn.getTileEntity(pos);
        ItemStack stack = getBlockItem();
        CompoundNBT nbt = new CompoundNBT();
        CompoundNBT config = new CompoundNBT();
        config.putString("2", tile.CONFIG.get(Direction.NORTH).name());
        config.putString("5", tile.CONFIG.get(Direction.EAST).name());
        config.putString("3", tile.CONFIG.get(Direction.SOUTH).name());
        config.putString("4", tile.CONFIG.get(Direction.WEST).name());
        config.putString("0", tile.CONFIG.get(Direction.DOWN).name());
        config.putString("1", tile.CONFIG.get(Direction.UP).name());
        nbt.put("config", config);
        stack.setTag(nbt);
        InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
        worldIn.removeTileEntity(pos);
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    public abstract ItemStack getBlockItem();

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }
}
