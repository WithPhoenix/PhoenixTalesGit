package com.phoenix.phoenixtales.rise.block.blocks.itemtransferer;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class ItemTransferer extends ConduitBlock {
    public ItemTransferer(Properties p_i48355_2_) {
        super( Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(2.0f, 2.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL));
    }

    @Override
    protected boolean connectsTo(IWorldReader world, BlockPos pos, Direction facing) {
        return false;
    }

    @Override
    protected boolean isConduit(IWorldReader world, BlockPos pos, Direction facing) {
        return false;
    }
}
