package com.phoenix.phoenixtales.rise.service.conduit.network;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.phoenix.phoenixtales.rise.block.blocks.ConduitBlock;
import com.phoenix.phoenixtales.rise.service.conduit.ICableNetwork;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.List;

public class CableNetwork implements ICableNetwork, IEnergyStorage {
    private int id;
    private World world;
    //would a pos we save make sense to init the search
    private List<BlockPos> blocks = new ArrayList<>();
    private List<Node> nodes = new ArrayList<>();
    private MutableGraph<BlockPos> graph = GraphBuilder.undirected().build();

    private int capacity;
    private int maxReceive;
    private int maxExtract;
    private int stored;

    public CableNetwork(int id, World world, int capacity) {
        this(id, world, capacity, 0);
    }

    public CableNetwork(int id, World world, int capacity, int stored) {
        this(id, world, capacity, stored, capacity, capacity);
    }

    public CableNetwork(int id, World world, int capacity, int stored, int maxReceive, int maxExtract) {
        this.id = id;
        this.world = world;
        this.stored = stored;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    //todo die blockstates sagen schon aus ob was verbunden ist oder nicht, damit arbeiten
    @Override
    public void update() {
        for (BlockPos pos : this.blocks) {
            for (Direction d : Direction.values()) {
                int count = 0;
                if (world.getBlockState(pos).get(ConduitBlock.FACING_TO_PROPERTY_MAP.get(d))) {
                    ++count;
                }
            }
        }
    }

    @Override
    public ICableNetwork merge(ICableNetwork... networks) {

        return networks[0];
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong("root", this.blocks.get(0).toLong());
        nbt.putInt("rate", this.maxExtract);
        nbt.putInt("current", this.stored);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (nbt.contains("root")) {
            this.blocks.set(0, BlockPos.fromLong(nbt.getLong("root")));
        }
        this.stored = nbt.contains("current") ? nbt.getInt("current") : 0;
        int t = nbt.contains("rate") ? nbt.getInt("rate") : 0;
        this.maxExtract = t;
        this.maxReceive = t;
        this.capacity = t;
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
