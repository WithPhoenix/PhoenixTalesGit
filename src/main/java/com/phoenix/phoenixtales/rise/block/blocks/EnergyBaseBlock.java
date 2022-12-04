package com.phoenix.phoenixtales.rise.block.blocks;

import com.phoenix.phoenixtales.rise.service.enums.EnergyHandlingType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
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
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity != null) {
            if (tileEntity instanceof EnergyBaseTile) {
                EnergyBaseTile tile = (EnergyBaseTile) tileEntity;
                if (stack.getTag() != null) {
                    CompoundNBT config = stack.getTag().contains("config") ? stack.getTag().getCompound("config") : null;
                    if (config != null) {
                        for (int i = 0; i < 6; i++) {
                            tile.CONFIG.set(Direction.byName(Direction.byIndex(i).name()), config.contains(String.valueOf(i)) ? EnergyHandlingType.byIndex(Integer.parseInt((config.getString(String.valueOf(i))))) : EnergyHandlingType.NONE);
                        }
                    }
                } else {
                    tile.CONFIG.reset();
                }
            }
        }
        Direction d = placer != null ? placer.getHorizontalFacing().getOpposite() : Direction.NORTH;
        worldIn.setBlockState(pos, state.with(FACING, d));
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    public ItemStack getBlockItem(EnergyBaseTile tile, Item item) {
        ItemStack stack = new ItemStack(item);
        CompoundNBT nbt = new CompoundNBT();
        CompoundNBT config = new CompoundNBT();
        for (Direction d : Direction.values()) {
            config.putInt(d.name(), tile.CONFIG.get(d).index());
        }
        nbt.put("config", config);
        stack.setTag(nbt);
        return stack;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
    }
}
