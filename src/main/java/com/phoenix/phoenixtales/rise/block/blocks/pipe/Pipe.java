package com.phoenix.phoenixtales.rise.block.blocks.pipe;

import com.phoenix.phoenixtales.rise.block.blocks.pipe.tile.PipeTile;
import com.phoenix.phoenixtales.rise.service.TechnologyType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.Nullable;

public class Pipe extends GenericPipe {
    public Pipe() {
        super(TechnologyType.NORMAL);
    }

}
