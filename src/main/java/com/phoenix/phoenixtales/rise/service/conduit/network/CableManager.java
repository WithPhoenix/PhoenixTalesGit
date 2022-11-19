package com.phoenix.phoenixtales.rise.service.conduit.network;

import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.GenericCableTile;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.HashSet;
import java.util.Set;

public class CableManager implements IEnergyStorage {
    private final int id;
    private final World world;

    public Set<BlockPos> cables = new HashSet<>();

    private long capacity;
    private int rate;
    private long stored;

    private int energyAtCable;

    public CableManager(int id, World world, long capacity, int rate) {
        this(id, world, capacity, 0, rate);
    }

    public CableManager(int id, World world, long capacity, long stored, int rate) {
        this.id = id;
        this.world = world;
        this.stored = stored;
        this.capacity = capacity;
        this.rate = rate;
        this.energyAtCable = 0;
    }

    public void init(BlockPos pos) {
        this.cables.add(pos);
        this.update(pos);
    }

    public void update(BlockPos pos) {
        for (Direction d : new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.DOWN, Direction.UP}) {
            TileEntity tile = world.getTileEntity(pos.offset(d));
            if (!(tile instanceof GenericCableTile)) continue;
            if (cables.contains(pos.offset(d))) continue;
            this.cables.add(pos.offset(d));
            ((GenericCableTile) tile).getManager().cables = this.cables;
            this.cables.add(pos.offset(d));
            update(pos.offset(d));
            ((GenericCableTile) tile).getManager().cables = this.cables;
            this.recalculateCapacityAndRate();
        }
    }

    private void recalculateCapacityAndRate() {
        this.capacity = this.cables.stream().map(pos -> world.getTileEntity(pos))
                .filter(te -> te instanceof GenericCableTile)
                .mapToLong(te -> ((GenericCableTile) te).getTechnologyType().transferRate())
                .sum();
        this.rate = (int) (this.capacity / this.cables.size());
        for (BlockPos pos : this.cables) {
            if (this.world.getTileEntity(pos) instanceof GenericCableTile) {
                GenericCableTile tile = (GenericCableTile) this.world.getTileEntity(pos);
                if (tile == null) continue;
                tile.getManager().setCapacity(this.capacity);
                tile.getManager().setRate(this.rate);
            }
        }
    }

    public void setCapacity(long val) {
        this.capacity = val;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    //this is called when a neighbor tile pushes energy
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (this.canReceive()) {
            int storedAsInt = this.stored > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) stored;
            int receiving = Math.min(Integer.MAX_VALUE - storedAsInt, Math.min(this.rate, maxReceive));
            if (!simulate) {
                this.stored += receiving;
            }
            return receiving;
        }
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (this.canExtract()) {
            int extracted = Math.min(this.rate, maxExtract);
            if (!simulate) {
                this.stored -= extracted;
//                this.evenEnergy();
            }
            return extracted;
        }
        return 0;
    }

    //TODO das problem ist, dass andere mods nur zugriff auf integer haben, d.h ich muss hier noch in jedes kabel unterscheiden, einfach dividieren, ich werde dann für mich eine eigene methode schreiben
    @Override
    public int getEnergyStored() {
        int atCable = this.energyAtCable;
        return this.stored > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) this.stored;
    }

    @Override
    public int getMaxEnergyStored() {
        return this.capacity > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) this.capacity;
    }

    @Override
    public boolean canExtract() {
        return this.capacity > 0;
    }

    @Override
    public boolean canReceive() {
        return this.capacity > 0;
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("rate", this.rate);
        nbt.putLong("current", this.stored);
        nbt.putLong("capacity", this.capacity);
        nbt.putLong("block", this.cables.iterator().next().toLong());
        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        this.stored = nbt.contains("current") ? nbt.getLong("current") : 0;
        this.capacity = nbt.contains("capacity") ? nbt.getLong("capacity") : 0;
        this.rate = nbt.contains("rate") ? nbt.getInt("rate") : 0;
    }

    //        this.update(cables.iterator().next());
//        Set<IEnergyStorage> storageStream = cables.stream().map(pos -> world.getTileEntity(pos))
//                .filter(Objects::nonNull)
//                .map(tile -> tile.getCapability(CapabilityEnergy.ENERGY))
//                .map(lazy -> lazy.orElse(new FallBackStorage()))
//                .filter(ie -> ie instanceof CableManager)
//                .collect(Collectors.toSet());
//        int receive = storageStream.size() == 0 ? maxReceive : maxReceive / storageStream.size();
//        int received = 0;
//        for (IEnergyStorage manager : storageStream) {
//            received += ((CableManager) manager).actualReceive(receive, simulate);
//        }
//        return received;

    //this is used in @receiveEnergy to actually insert the energy
//    public int actualReceive(int maxReceive, boolean simulate) {
//        world.addParticle(ParticleTypes.FLASH, 0, 0 + 1.1D, 0, 0.0D, 0.0D, 0.0D);
//        if (this.canReceive()) {
//            int storedAsInt = this.stored > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) stored;
//            int receiving = Math.min(Integer.MAX_VALUE - storedAsInt, Math.min(this.rate, maxReceive));
//            if (!simulate) {
//                this.stored += receiving;
//            }
//            return receiving;
//        }
//        return 0;
//    }

    //called after removing energy to reallocate the whole energy
//    public void evenEnergy() {
//        Stream<IEnergyStorage> storageStream = cables.stream().map(pos -> world.getTileEntity(pos))
//                .filter(Objects::nonNull)
//                .map(tile -> tile.getCapability(CapabilityEnergy.ENERGY))
//                .map(lazy -> lazy.orElse(new FallBackStorage()))
//                .filter(ie -> !(ie instanceof FallBackStorage));
//        long energy = storageStream
//                .mapToInt(IEnergyStorage::getEnergyStored)
//                .sum();
//        int energyPerIES = (int) (energy / storageStream
//                .collect(Collectors.toSet())
//                .size());
//        for (IEnergyStorage storage : storageStream.collect(Collectors.toSet())) {
//            storage.extractEnergy(storage.getMaxEnergyStored(), false);
//            storage.receiveEnergy(energyPerIES, false);
//        }
//    }

//    private static class FallBackStorage implements IEnergyStorage {
//        @Override
//        public int receiveEnergy(int maxReceive, boolean simulate) {
//            return 0;
//        }
//
//        @Override
//        public int extractEnergy(int maxExtract, boolean simulate) {
//            return 0;
//        }
//
//        @Override
//        public int getEnergyStored() {
//            return 0;
//        }
//
//        @Override
//        public int getMaxEnergyStored() {
//            return 0;
//        }
//
//        @Override
//        public boolean canExtract() {
//            return false;
//        }
//
//        @Override
//        public boolean canReceive() {
//            return false;
//        }
//    }
}
