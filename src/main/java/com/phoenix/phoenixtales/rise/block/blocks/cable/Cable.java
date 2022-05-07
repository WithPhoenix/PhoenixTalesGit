package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.CableTile;
import com.phoenix.phoenixtales.rise.service.TechnologyType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.Nullable;

public class Cable extends GenericCable {
    public Cable() {
        super(TechnologyType.NORMAL);
    }

//    @Nullable
//    @Override
//    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
//        return new CableTile();
//    }
}
