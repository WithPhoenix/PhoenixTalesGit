package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitBlock;
import com.phoenix.phoenixtales.rise.block.blocks.EnergyBaseBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class AbstractCable extends ConduitBlock {
    public AbstractCable(Properties p_i48355_2_) {
        super(p_i48355_2_);
    }

    @Override
    protected boolean shouldConnect(IBlockReader reader, BlockPos pos) {
        boolean flag = false;
        BlockPos.Mutable blockpos$mutable = pos.toMutable();

        for (Direction direction : Direction.values()) {
            BlockState blockstate = reader.getBlockState(blockpos$mutable);
            blockpos$mutable.setAndMove(pos, direction);
            blockstate = reader.getBlockState(blockpos$mutable);
            if (blockstate.getBlock() instanceof AbstractCable || blockstate.getBlock() instanceof EnergyBaseBlock) {
                flag = true;
                break;
            }
        }
        return flag;
    }


}
