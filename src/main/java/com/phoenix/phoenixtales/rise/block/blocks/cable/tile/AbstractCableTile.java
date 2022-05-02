package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.service.RiseEnergyStorage;
import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractCableTile extends TileEntity implements ITickableTileEntity {
    //    private CableNetwork network;
//    private RiseEnergyStorage storage;
//    private LazyOptional<IEnergyStorage> storageOpt;
    private TechnologyTier tier;

    public AbstractCableTile(TileEntityType<?> tileEntityTypeIn, TechnologyTier tier) {
        super(tileEntityTypeIn);
        this.tier = tier;
    }

    public void initCap() {
        int cap = 500;
        switch (tier) {
            case NORMAL:
                cap = 1000;
                break;
            case ADVANCED:
                cap = 2500;
                break;
            case OVERLOADED:
                cap = 5000;
                break;
     }
//        this.storage = new RiseEnergyStorage(cap, cap, cap, 0);
//        this.storageOpt = LazyOptional.of(() -> this.storage);
    }

//    public void initNetwork(World world, int id) {
//        this.network = new CableNetwork(world, id);
//    }
//
//    public void initNetworkFromExisting(CableNetwork network) {
//        this.network = network;
//    }
//
//    public void initNetworkFromExisting(List<CableNetwork> networks) {
//        this.network = CableNetwork.merge(networks, world);
//    }
//
//    public void changeNetwork(CableNetwork network) {
//        this.network = network;
//    }
//
//    public CableNetwork getNetwork() {
//        return this.network;
//    }

    public TechnologyTier getTier() {
        return this.tier;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
//        this.storage.deserializeNBT(nbt.getCompound("cap"));
//        this.network.deserializeNBT(nbt.getCompound("net"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
//        compound.put("cap", this.storage.serializeNBT());
//        compound.put("net", this.network.serializeNBT());
        return super.write(compound);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
//            return this.storageOpt.cast();
        }
        return super.getCapability(cap, side);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return this.getCapability(cap, null);
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
//            for (Direction d : Direction.values()) {
//                int push = this.storage.extractEnergy(this.storage.getMaxExtract(), true);
//                if (push > 0) {
//                    this.world.getTileEntity(this.pos.offset(d)).getCapability(CapabilityEnergy.ENERGY, d).ifPresent(cap -> cap.receiveEnergy(push, false));
//                    this.storage.extractEnergy(push, false);
//                }
//            }
        }
    }

}
