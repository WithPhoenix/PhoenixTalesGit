package com.phoenix.phoenixtales.rise.block.blocks;

import com.phoenix.phoenixtales.rise.service.SideConfiguration;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class EnergyBaseTile extends TileEntity {
        protected final SideConfiguration CONFIG;

    public EnergyBaseTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        CONFIG = new SideConfiguration(this);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("config", CONFIG.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        CONFIG.deserializeNBT(nbt.getCompound("config"));
        super.read(state, nbt);
    }
}
