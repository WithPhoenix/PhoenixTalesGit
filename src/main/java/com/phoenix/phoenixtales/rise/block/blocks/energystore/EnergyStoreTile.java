package com.phoenix.phoenixtales.rise.block.blocks.energystore;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.EnergyHandlingType;
import com.phoenix.phoenixtales.rise.service.RiseEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class EnergyStoreTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    //TODO config screen to set transferRatePerTick and outputsides/inputsides

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handlerOpt = LazyOptional.of(() -> itemHandler);
    private final RiseEnergyStorage storage = new RiseEnergyStorage(1000000, 15000, 1500, 0);
    private final LazyOptional<IEnergyStorage> storageOpt = LazyOptional.of(() -> storage);
    private int energyPercent;

    public EnergyStoreTile() {
        super(RiseTileEntities.ENERGY_STORE_TILE);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("items"));
        storage.deserializeNBT(nbt.getCompound("store"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("items", itemHandler.serializeNBT());
        compound.put("store", storage.serializeNBT());
        return super.write(compound);
    }

    public int getEnergyPercent() {
        return this.energyPercent;
    }

    public int getStored() {
        return this.storage.getEnergyStored();
    }

    public int getCapacity() {
        return this.storage.getMaxEnergyStored();
    }

    public int getR() {
        return this.storage.getMaxReceive();
    }

    public int getE() {
        return this.storage.getMaxExtract();
    }

    //logic to item handler
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handlerOpt.cast();
        } else if (cap == CapabilityEnergy.ENERGY) {
            if (side == null) return super.getCapability(cap, null);
            if (this.getBlockState().get(EnergyStore.FACING_TO_PROPERTY_MAP.get(side)) == EnergyHandlingType.RECEIVE) {
                return storageOpt.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    public <T> LazyOptional<T> getCapForCable(Direction side) {
        if (this.getBlockState().get(EnergyStore.FACING_TO_PROPERTY_MAP.get(side)) == EnergyHandlingType.RECEIVE
                || this.getBlockState().get(EnergyStore.FACING_TO_PROPERTY_MAP.get(side)) == EnergyHandlingType.EXTRACT) {
            return storageOpt.cast();
        }
        return LazyOptional.empty();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return this.getCapability(cap, null);
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            handleWorld();
            handleInv();

            this.energyPercent = (int) ((double) (this.storage.getEnergyStored()) * 100d / (double) (this.storage.getMaxEnergyStored()));
        }
    }

    //all things in the world
    //extract energy
    private void handleWorld() {
        for (Direction d : Direction.values()) {
            if (this.getBlockState().get(EnergyStore.FACING_TO_PROPERTY_MAP.get(d)) == EnergyHandlingType.EXTRACT) {
                TileEntity neighbor = world != null ? world.getTileEntity(this.pos.offset(d)) : null;
                if (neighbor != null) {
                    neighbor.getCapability(CapabilityEnergy.ENERGY, d.getOpposite()).ifPresent(cap -> {
                        int push = this.storage.extractEnergy(this.storage.getMaxExtract(), true);
                        if (push > 0) {
                            push = cap.receiveEnergy(push, false);
                            this.storage.extractEnergy(push, false);
                        }
                    });
                }
            }
        }
    }


    //all events in the inventory
    //receiving energy from items or charging items
    private void handleInv() {

    }


    public ItemStack getItemOn(int slot) {
        return itemHandler.getStackInSlot(slot);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("screen.phoenixtales.energy_store");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new EnergyStoreContainer(p_createMenu_1_, this.world, this.pos, p_createMenu_2_, p_createMenu_3_);
    }


    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    public void setEnergyPercent(int percent) {
        this.energyPercent = percent;
    }

    public void setStored(int v) {
    }


    //    public IIntArray getSideData() {
//        //update values
//        this.sideData.set(0, sideStatus.getOrDefault(BlockSide.DOWN, 2));
//        this.sideData.set(1, sideStatus.getOrDefault(BlockSide.UP, 2));
//        this.sideData.set(2, sideStatus.getOrDefault(BlockSide.NORTH, 2));
//        this.sideData.set(3, sideStatus.getOrDefault(BlockSide.SOUTH, 2));
//        this.sideData.set(4, sideStatus.getOrDefault(BlockSide.WEST, 2));
//        this.sideData.set(5, sideStatus.getOrDefault(BlockSide.EAST, 2));
//        return this.sideData;
//    }

//    public void nextStatus(BlockSide side) {
//        if (sideStatus.get(side) == 0) {
//            sideStatus.replace(side, 1);
//        } else if (sideStatus.get(side) == 1) {
//            sideStatus.replace(side, 2);
//        } else {
//            sideStatus.replace(side, 0);
//        }
//    }
//
//    public void replaceSideStatus(BlockSide side, Integer flag) {
//        this.sideStatus.replace(side, flag);
//    }
//
//    public int getSideStatus(BlockSide side) {
//        return sideStatus.get(side);
//    }
//
//    private void initializeSides() {
//        for (BlockSide side : BlockSide.all) {
//            this.sideStatus.put(side, 2);
//        }
//    }
//
//    private void saveSides(CompoundNBT compound) {
//        for (BlockSide side : BlockSide.all) {
//            compound.putInt(side.name(), sideStatus.getOrDefault(side, 2));
//        }
//    }

//    private Map<BlockSide, Integer> readSides(CompoundNBT nbt) {
//        Map<BlockSide, Integer> temp = new HashMap<>();
//        for (BlockSide side : BlockSide.all) {
//            temp.put(side, nbt.getInt(side.name()));
//        }
//        return temp;
//    }

    //    public void mapToNBT(CompoundNBT compound) {
//        int i = 0;
//        for (BlockSide side : sideStatus.keySet()) {
//            CompoundNBT sideData = new CompoundNBT();
//            sideData.putString("side", side.name());
//            sideData.putInt("status", sideStatus.get(side));
//            compound.put("sideData" + i, sideData);
//            ++i;
//        }
//    }
//
//    public Map<BlockSide, Integer> mapFromNBT(CompoundNBT nbt) {
//        Map<BlockSide, Integer> sideStatusTemp = new HashMap<>();
//        for (int i = 0; nbt.contains("sideData" + i); i++) {
//            CompoundNBT sideData = nbt.getCompound("sideData" + i);
//            BlockSide side = BlockSide.fromName(sideData.getString("side"));
//            int status = 2;
//            if (sideData.contains("status")) {
//                status = sideData.getInt("status");
//            }
//            sideStatusTemp.put(side, status);
//        }
//        return sideStatusTemp;
//    }
//
}
