package com.phoenix.phoenixtales.rise.block.blocks.pipe.tile;

import com.phoenix.phoenixtales.rise.service.TechnologyType;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class GenericPipeTile extends TileEntity implements ITickableTileEntity {
    private TechnologyType type;

    public GenericPipeTile(TileEntityType<?> tileEntityTypeIn, TechnologyType type) {
        super(tileEntityTypeIn);
        this.type = type;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return super.write(compound);
    }

    public TechnologyType getTechnologyType() {
        return type;
    }

    @Override
    public void tick() {

    }
}
