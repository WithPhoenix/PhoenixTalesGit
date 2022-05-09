package com.phoenix.phoenixtales.origins.block.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class DebrisBlock extends FallingBlock {
    public DebrisBlock() {
        super(AbstractBlock.Properties.create(Material.SAND, MaterialColor.STONE).hardnessAndResistance(0.6F).sound(SoundType.GROUND));
    }

    @Override
    public int getDustColor(BlockState state, IBlockReader reader, BlockPos pos) {
        int lvt_3_1_ = (47 << 8) + 42;
        return (lvt_3_1_ << 8) + 37;
    }
}
