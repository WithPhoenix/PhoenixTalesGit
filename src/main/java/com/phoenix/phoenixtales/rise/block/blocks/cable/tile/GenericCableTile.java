package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitTile;
import com.phoenix.phoenixtales.rise.service.TechnologyType;
import com.phoenix.phoenixtales.rise.service.conduit.network.CableNetwork;
import com.phoenix.phoenixtales.rise.service.conduit.EnergyManager;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class GenericCableTile extends ConduitTile implements ITickableTileEntity {
    private TechnologyType type;
    private CableNetwork network;
    private EnergyManager manager;

    protected GenericCableTile(TileEntityType<?> tileEntityTypeIn, TechnologyType type) {
        super(tileEntityTypeIn);
        this.type = type;
    }

    public CableNetwork getNetwork() {
        return network;
    }

    public void init(CableNetwork network) {

        this.network = network;

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
