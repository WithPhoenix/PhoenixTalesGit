package com.phoenix.phoenixtales.rise.service.conduit;

import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.AbstractCableTile;
import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CableNetwork implements IEnergyStorage {
    private List<BlockPos> cables = new ArrayList<>();
    private int id;
    private World world;

    private int capacity;
    private int stored;
    private int maxReceive;

    public CableNetwork(World world, int id) {
        this.world = world;
        this.id = id;
    }

    public void add(BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof AbstractCableTile) {
            this.cables.add(pos);
            this.increaseCapacity(((AbstractCableTile) tile).getTier());
        }
    }

    public List<BlockPos> getCables() {
        return this.cables;
    }

    public int getId() {
        return this.id;
    }

    public World getWorld() {
        return this.world;
    }

    public static CableNetwork merge(List<CableNetwork> networks, World world) {
        CableNetwork temp = new CableNetwork(networks.get(0).getWorld(), networks.get(0).getId());
        for (CableNetwork n : networks) {
            for (BlockPos pos : n.getCables()) {
                TileEntity tile = world.getTileEntity(pos);
                if (tile instanceof AbstractCableTile) {
                    ((AbstractCableTile) tile).changeNetwork(temp);
                }
            }
            temp.cables().addAll(n.getCables());
            temp.setCapacity(n.getMaxEnergyStored());
        }
        return temp;
    }

    private void increaseCapacity(TechnologyTier tier) {
        switch (tier) {
            case SIMPLE:
                this.capacity += 500;
                break;
            case NORMAL:
                this.capacity += 1000;
                break;
            case ADVANCED:
                this.capacity += 2500;
                break;
            case OVERLOADED:
                this.capacity += 5000;
                break;
        }
    }

    @Nullable
    private AbstractCableTile getTile(BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        return tile instanceof AbstractCableTile ? (AbstractCableTile) tile : null;
    }

    private void updateNetwork(BlockPos pos) {

    }

    public List<BlockPos> cables() {
        return this.cables;
    }

    public int setCapacity(int n) {
        return this.capacity += n;
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
        return false;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT values = new CompoundNBT();
        //network
        CompoundNBT cableNBT = new CompoundNBT();
        for (int i = 0; i < this.cables.size(); i++) {
            cableNBT.putLong("c" + i, this.cables.get(0).toLong());
        }
        cableNBT.putInt("size", this.cables.size());
        values.put("cables", cableNBT);
        //energy
        CompoundNBT energyNBT = new CompoundNBT();
        energyNBT.putInt("capacity", this.capacity);
        energyNBT.putInt("stored", this.stored);
        values.put("storage", energyNBT);
        //other stuff
        values.putInt("id", this.id);
        return values;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        this.id = nbt.contains("id") ? nbt.getInt("id") : 0;
        List<BlockPos> cablesn = new ArrayList<>();
        if (nbt.contains("cables")) {
            CompoundNBT cableNBT = nbt.getCompound("cables");
            int size = cableNBT.contains("size") ? cableNBT.getInt("size") : 0;
            for (int i = 0; i < size; i++) {
                if (cableNBT.contains("c" + i)) {
                    cablesn.add(BlockPos.fromLong(cableNBT.getLong("c" + i)));
                }
            }
        }
        this.cables = cablesn;
        if (nbt.contains("storage")) {
            CompoundNBT eNBT = nbt.getCompound("storage");
            this.capacity = eNBT.contains("capacity") ? eNBT.getInt("capacity") : 0;
            this.stored = eNBT.contains("stored") ? eNBT.getInt("stored") : 0;
        }
    }
}
