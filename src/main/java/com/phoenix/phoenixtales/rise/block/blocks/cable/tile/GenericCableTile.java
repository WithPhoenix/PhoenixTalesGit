package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitTile;
import com.phoenix.phoenixtales.rise.service.TechnologyType;
import com.phoenix.phoenixtales.rise.service.conduit.network.CableManager;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class GenericCableTile extends ConduitTile implements ITickableTileEntity {
    private final TechnologyType type;
    private List<Link> links;

    private CableManager network;

    protected GenericCableTile(TileEntityType<?> tileEntityTypeIn, TechnologyType type) {
        super(tileEntityTypeIn);
        this.type = type;
    }

    public CableManager getNetwork() {
        return network;
    }

    public List<Link> getLinks() {
        if (this.world == null) return new ArrayList<>();
        this.network.update();
        if (this.links == null) return new ArrayList<>();
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void init(CableManager network) {
        this.network = network;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return super.write(compound);
    }

    public TechnologyType getTechnologyType() {
        return type;
    }

    @Override
    public void tick() {

    }

    public class Link {
        private BlockPos pos;
        private Direction direction;

        public Link(BlockPos pos, Direction direction) {
            this.pos = pos;
            this.direction = direction;
        }

        public BlockPos getPos() {
            return pos;
        }

        public Direction getDirection() {
            return direction;
        }
    }
}
