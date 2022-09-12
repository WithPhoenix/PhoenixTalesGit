package com.phoenix.phoenixtales.rise.service.conduit.network;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitBlock;
import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.GenericCableTile;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CableManager implements IEnergyStorage {
    private int id;
    private World world;

    public Set<BlockPos> cables = new HashSet<>();

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

    public void init(BlockPos pos) {
        this.cables.add(pos);
    }

    //Todo es sollte möglich sein pro tick die connections abzufragen, wenn die suche gut ist
    //also wenn energy gepushed wird, dann aus dem einen alles raus, und neu verteilen
    //wenn energy received wird, dann gleich verteilen

    //todo die blockstates sagen schon aus ob was verbunden ist oder nicht, damit arbeiten
    //die blöcke sollten bei so nem durchlauf benachrichtigt werden
    public void update(BlockPos pos) {
        world.addParticle(ParticleTypes.BARRIER, 0D, 0D, 0D, 0.0D, 0.0D, 0.0D);
        for (Direction d : Direction.values()) {
            if (!(world.getTileEntity(pos) instanceof GenericCableTile)) return;
            if (world.getBlockState(pos).get(ConduitBlock.FACING_TO_PROPERTY_MAP.get(d))) {
                if (cables.contains(pos.offset(d))) return;
                update(pos.offset(d));
                world.getPlayers().forEach(player -> player.sendMessage(new StringTextComponent("check"), player.getUniqueID()));
            }
            this.cables.add(pos);
            return;
        }
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("rate", this.capacity);
        nbt.putInt("current", this.stored);
        nbt.putLong("block", this.cables.iterator().next().toLong());
        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        this.stored = nbt.contains("current") ? nbt.getInt("current") : 0;
        int t = nbt.contains("rate") ? nbt.getInt("rate") : 0;
        this.maxExtract = t;
        this.maxReceive = t;
        this.capacity = t;
        if (nbt.contains("block")) {

        }
    }

    //this is called when a neighbor tile pushes energy
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        world.addParticle(ParticleTypes.HEART, 0, 0 + 1.1D, 0, 0.0D, 0.0D, 0.0D);
        this.update(cables.iterator().next());
        Stream<IEnergyStorage> storageStream = cables.stream().map(pos -> world.getTileEntity(pos))
                .filter(Objects::nonNull)
                .map(tile -> tile.getCapability(CapabilityEnergy.ENERGY))
                .map(lazy -> lazy.orElse(new FallBackStorage()))
                .filter(ie -> ie instanceof CableManager);
        int receive = maxReceive / storageStream.collect(Collectors.toSet()).size();
        int received = 0;
        for (IEnergyStorage manager : storageStream.collect(Collectors.toSet())) {
            if (manager instanceof CableManager) received += ((CableManager) manager).actualReceive(receive);
        }
        return received;
    }

    //this is used in @receiveEnergy to actually insert the energy
    public int actualReceive(int maxReceive) {
        world.addParticle(ParticleTypes.FLASH, 0, 0 + 1.1D, 0, 0.0D, 0.0D, 0.0D);
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
        Stream<IEnergyStorage> storageStream = cables.stream().map(pos -> world.getTileEntity(pos))
                .filter(Objects::nonNull)
                .map(tile -> tile.getCapability(CapabilityEnergy.ENERGY))
                .map(lazy -> lazy.orElse(new FallBackStorage()))
                .filter(ie -> !(ie instanceof FallBackStorage));
        int energy = storageStream
                .mapToInt(IEnergyStorage::getEnergyStored)
                .sum();
        int energyPerIES = energy / storageStream
                .collect(Collectors.toSet())
                .size();
        for (IEnergyStorage storage : storageStream.collect(Collectors.toSet())) {
            storage.extractEnergy(storage.getMaxEnergyStored(), false);
            storage.receiveEnergy(energyPerIES, false);
        }
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
        return this.capacity > 0;
    }

    @Override
    public boolean canReceive() {
        return this.capacity > 0;
    }


    private static class FallBackStorage implements IEnergyStorage {
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
            return 0;
        }

        @Override
        public int getMaxEnergyStored() {
            return 0;
        }

        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return false;
        }
    }
}
