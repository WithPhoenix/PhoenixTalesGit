package com.phoenix.phoenixtales.rise.service.conduit;

import com.phoenix.phoenixtales.rise.block.blocks.cable.tile.GenericCableTile;
import com.phoenix.phoenixtales.rise.service.enums.TechnologyType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AchievedCableNetwork {
    private List<BlockPos> cables = new ArrayList<>();
    private int id;
    private World world;

    private int capacity;
    private int stored;

    public AchievedCableNetwork(World world, int id) {
        this.world = world;
        this.id = id;
    }

    public void add(BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof GenericCableTile) {
            this.cables.add(pos);
            this.increaseCapacity(((GenericCableTile) tile).getTechnologyType());
        }
    }


    public static AchievedCableNetwork merge(List<AchievedCableNetwork> networks, World world) {
        AchievedCableNetwork temp = new AchievedCableNetwork(networks.get(0).getWorld(), networks.get(0).getId());
        for (AchievedCableNetwork n : networks) {
            for (BlockPos pos : n.cables()) {
                TileEntity tile = world.getTileEntity(pos);
                if (tile instanceof GenericCableTile) {
//                    ((AbstractCableTile) tile).changeNetwork(temp);
                }
            }
            temp.cables().addAll(n.cables());
            temp.setCapacity(n.getCapacity());
        }
        return temp;
    }

    private void increaseCapacity(TechnologyType tier) {
        switch (tier) {
            case SIMPLE:
                this.capacity += 500;
                break;
            case NORMAL:
                this.capacity += 1000;
                break;
            case IMPROVED:
                this.capacity += 2500;
                break;
            case OVERLOADED:
                this.capacity += 5000;
                break;
        }
    }

    @Nullable
    private GenericCableTile getTile(BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        return tile instanceof GenericCableTile ? (GenericCableTile) tile : null;
    }

    private void updateNetwork(BlockPos pos) {

    }

    public int getId() {
        return this.id;
    }

    public World getWorld() {
        return this.world;
    }

    public List<BlockPos> cables() {
        return this.cables;
    }

    public int setCapacity(int n) {
        return this.capacity += n;
    }

    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    public int getStored() {
        return this.stored;
    }

    public int getCapacity() {
        return this.capacity;
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
