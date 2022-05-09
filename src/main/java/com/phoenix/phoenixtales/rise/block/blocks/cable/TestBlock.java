package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.TestCableTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import org.jetbrains.annotations.Nullable;

public class TestBlock extends Block {
    public TestBlock() {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(2.0f, 2.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TestCableTile();
    }
}
