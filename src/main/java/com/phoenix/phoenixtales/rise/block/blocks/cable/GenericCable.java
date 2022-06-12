package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitBlock;
import com.phoenix.phoenixtales.rise.block.blocks.EnergyBaseBlock;
import com.phoenix.phoenixtales.rise.service.TechnologyType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

@SuppressWarnings("deprecation")
public class GenericCable extends ConduitBlock {

    private final TechnologyType type;

    public GenericCable(TechnologyType type) {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(2.0f, 2.0f).sound(SoundType.METAL));
        this.type = type;
    }


    public TechnologyType getTechnologyType() {
        return this.type;
    }


    @Override
    protected boolean shouldConnect(IBlockReader reader, BlockPos pos) {
        boolean flag = false;
        BlockPos.Mutable blockpos$mutable = pos.toMutable();

        for (Direction direction : Direction.values()) {
            BlockState blockstate = reader.getBlockState(blockpos$mutable);
            blockpos$mutable.setAndMove(pos, direction);
            blockstate = reader.getBlockState(blockpos$mutable);
            if (blockstate.getBlock() instanceof GenericCable || blockstate.getBlock() instanceof EnergyBaseBlock) {
                flag = true;
                break;
            }
        }
        return flag;
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


//    @Override
//    public boolean hasTileEntity(BlockState state) {
//        return true;
//    }

}
