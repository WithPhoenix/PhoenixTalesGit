package com.phoenix.phoenixtales.rise.block.blocks.cable.tile;

import com.phoenix.phoenixtales.rise.service.TechnologyTier;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class AbstractCableTile extends TileEntity {
    //    private CableNetwork network;
//    private RiseEnergyStorage storage;
//    private LazyOptional<IEnergyStorage> storageOpt;

    public AbstractCableTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
//        int cap = 500;
//        switch (tier) {
//            case NORMAL:
//                cap = 1000;
//                break;
//            case ADVANCED:
//                cap = 2500;
//                break;
//            case OVERLOADED:
//                cap = 5000;
//                break;
//        }
    }

   public TechnologyTier getTier() {
        return null;
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
