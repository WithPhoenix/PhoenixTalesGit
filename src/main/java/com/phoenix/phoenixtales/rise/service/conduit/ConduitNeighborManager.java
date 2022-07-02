package com.phoenix.phoenixtales.rise.service.conduit;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitTile;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ConduitNeighborManager {
    private World world;
    private BlockPos pos;
    private List<BlockPos> blocks = new ArrayList<>();

    public ConduitNeighborManager(World world, BlockPos pos) {
        this.world = world;
        this.pos = pos;
    }

    public ConduitNeighborManager(World world) {

    }

    public void init(World world) {
        this.world = world;
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
        this.blocks = newN;
    }

    public CompoundNBT serializeNBT() {
        return null;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        if (nbt.contains("neighbors")) {
            this.blocks = Arrays.stream(nbt.getLongArray("neighbors")).mapToObj(BlockPos::fromLong).collect(Collectors.toList());
        }
    }
}
