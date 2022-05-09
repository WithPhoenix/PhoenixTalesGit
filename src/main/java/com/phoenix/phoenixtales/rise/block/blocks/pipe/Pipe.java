package com.phoenix.phoenixtales.rise.block.blocks.pipe;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class Pipe extends ConduitBlock {
    public Pipe(Properties p_i48355_2_) {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(2.0f, 2.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL));
    }
}
