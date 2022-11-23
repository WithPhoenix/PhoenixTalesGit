package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.CableTile;
import com.phoenix.phoenixtales.rise.service.enums.TechnologyType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class Cable extends GenericCable {
    public Cable() {
        super(Properties.create(Material.WOOL, MaterialColor.GRAY).hardnessAndResistance(1.0f).sound(SoundType.CLOTH), TechnologyType.NORMAL);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CableTile();
    }
}
