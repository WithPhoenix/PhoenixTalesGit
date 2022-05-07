package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.SimpleCableTile;
import com.phoenix.phoenixtales.rise.service.TechnologyType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.Nullable;

public class SimpleCable extends GenericCable {
    public SimpleCable() {
        super(TechnologyType.SIMPLE);
    }

//    @Nullable
//    @Override
//    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
//        return new SimpleCableTile();
//    }
}
