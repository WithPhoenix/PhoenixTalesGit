package com.phoenix.phoenixtales.rise.service.conduit;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.List;

public class NetworkCable implements INetwork, IEnergyStorage {
    private List<BlockPos> cables = new ArrayList<>();
    private int id;
    private World world;

    private int capacity;
    private int maxReceive;
    private int maxExtract;
    private int stored;

    public NetworkCable(int id, World world, int capacity, int stored) {
        this(id, world, capacity, stored, 0, 0);
    }

    public NetworkCable(int id, World world, int capacity, int stored, int maxReceive, int maxExtract) {

    }

    //todo smth with the technology tier
    @Override
    public void add(BlockPos pos) {

    }

    @Override
    public void remove(BlockPos pos) {

    }

    @Override
    public void update() {

    }

    @Override
    public void merge(List<INetwork> networks) {

    }

    @Override
    public CompoundNBT serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return this.stored;
    }

    @Override
    public int getMaxEnergyStored() {
        return this.capacity;
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }
}
