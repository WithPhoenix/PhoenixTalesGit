package com.phoenix.phoenixtales.rise.block.blocks.cable;

import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.AdvancedCableTile;
import com.phoenix.phoenixtales.rise.service.TechnologyType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.Nullable;

public class AdvancedCable extends GenericCable {
    public AdvancedCable() {
        super(TechnologyType.ADVANCED);
    }

//    @Nullable
//    @Override
//    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
//        return new AdvancedCableTile();
//    }
}
