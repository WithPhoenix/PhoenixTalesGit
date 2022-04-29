package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.RiseEnergyStorage;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

public class CableTile extends TileEntity implements ITickableTileEntity {
    private final RiseEnergyStorage storage = new RiseEnergyStorage(2500, 2500, 2500);
    private final LazyOptional<IEnergyStorage> storageOpt = LazyOptional.of(() -> storage);

    public CableTile(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public CableTile() {
        this(RiseTileEntities.CABLE_TILE);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return super.getCapability(cap);
    }

    @Override
    public void tick() {
    }
}
