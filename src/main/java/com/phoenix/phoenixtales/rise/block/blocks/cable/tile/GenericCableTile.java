package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitBlock;
import com.phoenix.phoenixtales.rise.block.blocks.ConduitTile;
import com.phoenix.phoenixtales.rise.service.TechnologyType;
import com.phoenix.phoenixtales.rise.service.conduit.network.CableManager;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GenericCableTile extends ConduitTile implements ITickableTileEntity {
    private final TechnologyType type;
    private List<Link> links;

    private CableManager manager;
    private LazyOptional<IEnergyStorage> lazyOptManager;
    private boolean data;

    protected GenericCableTile(TileEntityType<?> tileEntityTypeIn, TechnologyType type) {
        super(tileEntityTypeIn);
        this.type = type;
    }

    public CableManager getNetwork() {
        return manager;
    }

    public List<Link> getLinks() {
        if (this.world == null) return new ArrayList<>();
        this.update(pos);
        if (this.links == null) return new ArrayList<>();
        return links;
    }

    public void update(@Nullable BlockPos pos) {
        if (pos == null) {
            pos = this.pos;
        }
        world.addParticle(ParticleTypes.BARRIER, 1D, 0D, 1D, 0.5D, 0.3D, 0.0D);
        for (Direction d : Direction.values()) {
            if (!(world.getTileEntity(pos) instanceof GenericCableTile)) return;
            if (world.getBlockState(pos).get(ConduitBlock.FACING_TO_PROPERTY_MAP.get(d))) {
                if (manager.cables.contains(pos.offset(d))) return;
                this.update(pos.offset(d));
            }
            this.manager.cables.add(pos);
            this.data = true;
            return;
        }
    }

    //can i put this method in CableManager?
    public void setLinks(List<Link> links) {
        this.links = links;
    }


    private boolean hasMnrg() {
        return this.manager != null;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        manager.deserializeNBT(nbt.getCompound("mngr"));
        data = nbt.contains("test") && nbt.getBoolean("test");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if (hasMnrg()) {
            compound.put("mngr", manager.serializeNBT());
        }
        compound.putBoolean("test", this.data);
        return super.write(compound);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            if (this.getBlockState().get(ConduitBlock.FACING_TO_PROPERTY_MAP.get(side))) {
                return lazyOptManager.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    public TechnologyType getTechnologyType() {
        return type;
    }

    //when extractiong or smth else loop over all chached cables and update
    //and maybe when the blockstates change then update the connections
    @Override
    public void tick() {
        if (hasMnrg()) {
            this.data = false;
            this.manager = new CableManager(0, this.world, type.getCableValue());
            this.manager.init(pos);
            this.update(pos);
            this.lazyOptManager = LazyOptional.of(() -> manager);
        }
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
