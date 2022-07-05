package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.SimpleCableTile;
import com.phoenix.phoenixtales.rise.service.TechnologyType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class SimpleCable extends GenericCable {
    public SimpleCable() {
        super(Properties.create(Material.WOOL, MaterialColor.WOOL).hardnessAndResistance(0.8f).sound(SoundType.CLOTH), TechnologyType.SIMPLE);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SimpleCableTile();
    }
}
