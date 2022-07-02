package com.phoenix.phoenixtales.rise.block.blocks;

import com.phoenix.phoenixtales.rise.service.conduit.ConduitNeighborManager;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class ConduitTile extends TileEntity implements ITickableTileEntity {

    private ConduitNeighborManager manager;
    private ArrayList<BlockPos> neighbors = new ArrayList<>();

    public ConduitTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public void neighborChanged() {
        ArrayList<BlockPos> newN = new ArrayList<>();
        for (Direction d : Direction.values()) {
            TileEntity tile = world != null ? world.getTileEntity(pos.offset(d)) : null;
            if (tile == null) continue;
            if (tile instanceof ConduitTile) {
                ConduitTile neighbor = (ConduitTile) tile;
                newN.add(neighbor.getPos());
            }
        }
        this.neighbors = newN;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        if (nbt.contains("neighbors")) {
            this.neighbors = (ArrayList<BlockPos>) Arrays.stream(nbt.getLongArray("neighbors")).mapToObj(BlockPos::fromLong).collect(Collectors.toList());
        }
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putLongArray("neighbors", neighbors.stream().map(BlockPos::toLong).collect(Collectors.toList()));
        return super.write(compound);
    }

    @Override
    public void tick() {

    }
}
