package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.OverloadedCableTile;
import com.phoenix.phoenixtales.rise.service.TechnologyType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class OverloadedCable extends GenericCable {
    public OverloadedCable() {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(1.5f).sound(SoundType.METAL), TechnologyType.OVERLOADED);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new OverloadedCableTile();
    }
}
