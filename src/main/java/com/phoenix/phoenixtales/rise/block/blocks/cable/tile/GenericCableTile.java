package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.service.TechnologyType;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class GenericCableTile extends TileEntity implements ITickableTileEntity {

    private TechnologyType type;

    protected GenericCableTile(TileEntityType<?> tileEntityTypeIn, TechnologyType type) {
        super(tileEntityTypeIn);
        this.type = type;
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

//        this.storage = new RiseEnergyStorage(cap, cap, cap, 0);
//        this.storageOpt = LazyOptional.of(() -> this.storage);
//    }

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


//        if (!world.isRemote) {
//            for (Direction d : Direction.values()) {
//                int push = this.storage.extractEnergy(this.storage.getMaxExtract(), true);
//                if (push > 0) {
//                    this.world.getTileEntity(this.pos.offset(d)).getCapability(CapabilityEnergy.ENERGY, d).ifPresent(cap -> cap.receiveEnergy(push, false));
//                    this.storage.extractEnergy(push, false);
//                }
//            }
//        }


}
