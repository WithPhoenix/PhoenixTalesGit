package com.phoenix.phoenixtales.rise.block.blocks.energystore;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.util.BlockSide;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class EnergyStoreTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    //TODO config screen to set transferRatePerTick and outputsides/inputsides

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private int energy;
    private int maxEnergy; //this is a fixed value, with upgrades this will get higher
    private int energyPercent;
    private int transferRatePerTick;
    private int maxTransferRate;
    //does the side receive or export energy or non
    //0 = receive; 1 = export; 2 = non
    private Map<BlockSide, Integer> sideStatus;

    public EnergyStoreTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.energy = 0;
        this.maxEnergy = 1000000;
        this.transferRatePerTick = 0;
        this.maxTransferRate = 1000;
        this.sideStatus = new HashMap<>();
        this.initializeSides();
    }

    public EnergyStoreTile() {
        this(RiseTileEntities.ENERGY_STORE_TILE);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.energy = nbt.getInt("energy");
        this.maxEnergy = nbt.getInt("mE");
        this.maxTransferRate = nbt.getInt("mTRate");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        compound.putInt("energy", this.energy);
        compound.putInt("mE", this.maxEnergy);
        compound.putInt("mTRate", this.maxTransferRate);
        return super.write(compound);
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


    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            handleWorld();
            handleInv();

            this.energyPercent = (int) ((double) (energy) * 100d / (double) (maxEnergy));
        }
    }

    //all things in the world
    //receive or extract energy
    private void handleWorld() {

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

    public void setSideStatus(BlockSide side, Integer flag) {
        this.sideStatus.replace(side, flag);
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getTransferRatePerTick() {
        return transferRatePerTick;
    }

    public void setTransferRatePerTick(int transferRatePerTick) {
        this.transferRatePerTick = transferRatePerTick;
    }

    public int getMaxTransferRate() {
        return this.maxTransferRate;
    }

    public void setMaxTransferRate(int maxTransferRate) {
        this.maxTransferRate = maxTransferRate;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaxEnergy() {
        return this.maxEnergy;
    }

    public int getEnergyPercent() {
        return this.energyPercent;
    }

    public void setEnergyPercent(int energyPercent) {
        this.energyPercent = energyPercent;
    }

    public void addEnergy(int amount) {
        this.energy = this.energy + amount;
    }

    public void removeEnergy(int amount) {
        this.energy = this.energy - amount;
    }

    private void initializeSides() {
        for (int i = 0; i < 6; i++) {
            this.sideStatus.put(BlockSide.intToBlockSide(i), 2);
        }
    }
}
