package com.phoenix.phoenixtales.rise.service;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.IEnergyStorage;

public class RiseEnergyStorage implements IEnergyStorage {

    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;
    protected int stored;

    public RiseEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public RiseEnergyStorage(int capacity, int maxReceive, int maxExtract, int stored) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.stored = Math.min(capacity, stored);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (canReceive()) {
            int receiving = Math.min(this.capacity - this.stored, Math.min(this.maxReceive, maxReceive));
            if (!simulate) {
                this.stored += receiving;
            }
            return receiving;
        }
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (canExtract()) {
            int extracted = Math.min(stored, Math.min(this.maxExtract, maxExtract));
            if (!simulate) {
                this.stored -= extracted;
            }
            return extracted;
        }
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

    public int getMaxReceive() {
        return this.maxReceive;
    }

    public int getMaxExtract() {
        return this.maxExtract;
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT values = new CompoundNBT();
        values.putInt("cap", this.capacity);
        values.putInt("stored", this.stored);
        values.putInt("receive", this.maxReceive);
        values.putInt("extract", this.maxExtract);
        return values;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        this.capacity = nbt.contains("cap") ? nbt.getInt("cap") : 0;
        this.stored = nbt.contains("stored") ? nbt.getInt("stored") : 0;
        this.maxReceive = nbt.contains("receive") ? nbt.getInt("receive") : 0;
        this.maxExtract = nbt.contains("extract") ? nbt.getInt("extract") : 0;
    }
}
