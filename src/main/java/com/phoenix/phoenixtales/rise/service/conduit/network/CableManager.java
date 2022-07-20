package com.phoenix.phoenixtales.rise.service.conduit.network;

import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.GenericCableTile;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.List;

public class CableManager implements IEnergyStorage {
    private int id;
    private World world;

    private int capacity;
    private int maxReceive;
    private int maxExtract;
    private int stored;

    public CableManager(int id, World world, int capacity) {
        this(id, world, capacity, 0);
    }

    public CableManager(int id, World world, int capacity, int stored) {
        this(id, world, capacity, stored, capacity, capacity);
    }

    public CableManager(int id, World world, int capacity, int stored, int maxReceive, int maxExtract) {
        this.id = id;
        this.world = world;
        this.stored = stored;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    //Todo es sollte möglich sein pro tick die connections abzufragen, wenn die suche gut ist
    //also wenn energy gepushed wird, dann aus dem einen alles raus, und neu verteilen
    //wenn energy received wird, dann gleich verteilen

    //todo die blockstates sagen schon aus ob was verbunden ist oder nicht, damit arbeiten
    public void update(BlockPos pos) {
        List<GenericCableTile.Link> queued = new ArrayList<>();

        while (queued.size() > 1) {

        }
        //if (world.getBlockState(pos).get(ConduitBlock.FACING_TO_PROPERTY_MAP.get(d))) {
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("rate", this.maxExtract);
        nbt.putInt("current", this.stored);
        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        this.stored = nbt.contains("current") ? nbt.getInt("current") : 0;
        int t = nbt.contains("rate") ? nbt.getInt("rate") : 0;
        this.maxExtract = t;
        this.maxReceive = t;
        this.capacity = t;
    }

    //this is called when a neighbor tile pushes energy
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    //this is used in @receiveEnergy to actually insert the energy
    public int actualReceive(int maxReceive) {
        if (this.canReceive()) {
            int receiving = Math.min(this.capacity - this.stored, Math.min(this.maxReceive, maxReceive));
            this.stored += receiving;
            return receiving;
        }
        return 0;
    }

    //called to
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (this.canExtract()) {
            int extracted = Math.min(stored, Math.min(this.maxExtract, maxExtract));
            if (!simulate) {
                this.stored -= extracted;
                this.evenEnergy();
            }
            return extracted;
        }
        return 0;
    }

    //called after removing energy to reallocate the whole energy
    public void evenEnergy() {

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
