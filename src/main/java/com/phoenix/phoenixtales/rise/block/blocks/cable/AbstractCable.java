package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitBlock;
import com.phoenix.phoenixtales.rise.block.blocks.EnergyBaseBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

//TODO an Interface that opens if right clicked with a wrench showing information (transfer rate, tier)
public class AbstractCable extends ConduitBlock {
    public AbstractCable() {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(2.0f, 2.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL));
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

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
