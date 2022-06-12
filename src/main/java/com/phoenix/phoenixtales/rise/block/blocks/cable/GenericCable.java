package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitBlock;
import com.phoenix.phoenixtales.rise.block.blocks.EnergyBaseBlock;
import com.phoenix.phoenixtales.rise.service.TechnologyType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import org.jetbrains.annotations.Nullable;

public class GenericCable extends ConduitBlock {
    private final TechnologyType type;

    public GenericCable(TechnologyType type) {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(2.0f).sound(SoundType.METAL));
        this.type = type;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }


    @Override
    protected boolean canConnectTo(IWorldReader world, BlockPos pos, Direction facing) {
        TileEntity tile = world.getTileEntity(pos.offset(facing));
        return tile != null && tile.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).isPresent();
    }

    @Override
    protected boolean isConduit(IWorldReader world, BlockPos pos, Direction facing) {
        Block block = world.getBlockState(pos.offset(facing)).getBlock();
        return block instanceof GenericCable;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, world, pos, neighbor);
    }

    //    @Override
//    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
////        TileEntity tileEntity = worldIn.getTileEntity(pos);
////        if (tileEntity instanceof AbstractCableTile) {
////            AbstractCableTile tile = (AbstractCableTile) tileEntity;
////            List<CableNetwork> networks = new ArrayList<>();
////            for (Direction d : Direction.values()) {
////                TileEntity offset = worldIn.getTileEntity(pos.offset(d));
////                if (offset instanceof AbstractCableTile) {
////                    networks.add(((AbstractCableTile) offset).getNetwork());
////                }
////            }
////            if (networks.isEmpty()) {
////                tile.initNetwork(worldIn, 0);
////                placer.sendMessage(new StringTextComponent("Empty"), placer.getUniqueID());
////            } else if (networks.size() == 1) {
////                tile.initNetworkFromExisting(networks.get(0));
////                placer.sendMessage(new StringTextComponent("one"), placer.getUniqueID());
////            } else {
////                tile.initNetworkFromExisting(networks);
////                placer.sendMessage(new StringTextComponent("merged"), placer.getUniqueID());
////            }
////            tile.getNetwork().add(pos);
////        }
//        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
//    }

//    @Override
//    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
////        TileEntity tile = worldIn.getTileEntity(pos);
////        if (tile instanceof AbstractCableTile) {
////            if (newState.getBlock() == state.getBlock()) {
////                //the Tier could be changed
////            } else {
////                ((AbstractCableTile) tile).getNetwork().add(pos);
////            }
////        }
//        super.onReplaced(state, worldIn, pos, newState, isMoving);
//    }

//    @Override
//    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos pos2, boolean b) {
//        super.neighborChanged(state, world, pos, block, pos2, b);
//    }


}
