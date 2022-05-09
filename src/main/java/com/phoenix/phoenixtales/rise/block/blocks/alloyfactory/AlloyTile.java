package com.phoenix.phoenixtales.rise.block.blocks.alloyfactory;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.item.RiseItems;
import com.phoenix.phoenixtales.rise.service.RiseEnergyStorage;
import com.phoenix.phoenixtales.rise.service.RiseRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
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
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Random;

public class AlloyTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handlerOpt = LazyOptional.of(() -> itemHandler);
    private final RiseEnergyStorage storage = new RiseEnergyStorage(200000, 2500, 2500, 0);
    private final LazyOptional<IEnergyStorage> storageOpt = LazyOptional.of(() -> storage);
    private int progress;
    private int totalTime;
    private int progressPercent;
    private int energyPercent;

    public AlloyTile() {
        super(RiseTileEntities.ALLOY_TILE);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        storage.deserializeNBT(nbt.getCompound("storage"));
        this.progress = nbt.getInt("progress");
        this.totalTime = nbt.getInt("totalTime");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        compound.put("storage", storage.serializeNBT());
        compound.putInt("progress", this.progress);
        compound.putInt("totalTime", this.totalTime);
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(4) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 2) {
                    return stack.getItem() == RiseItems.SLAG;
                } else {
                    return true;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                return 64;
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

    public ItemStack getItemOn(int slot) {
        return itemHandler.getStackInSlot(slot);
    }


    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handlerOpt.cast();
        } else if (cap == CapabilityEnergy.ENERGY) {
            return storageOpt.cast();
        }
        return super.getCapability(cap, side);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return this.getCapability(cap, null);
    }

    private void craft() {
        int energyUsagePerTick;
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }

        AlloyingRecipe r = world.getRecipeManager().getRecipe(RiseRecipeTypes.ALLOYING_RECIPE, inv, world).orElse(null);

        if (r != null) {
            this.totalTime = r.getProgressTime();
            if (ItemHandlerHelper.canItemStacksStack(r.getRecipeOutput(), itemHandler.getStackInSlot(3)) || itemHandler.getStackInSlot(3).equals(ItemStack.EMPTY)
                    && ItemHandlerHelper.canItemStacksStack(RiseItems.SLAG.getDefaultInstance(), itemHandler.getStackInSlot(2)) || itemHandler.getStackInSlot(2).equals(ItemStack.EMPTY)) {
                if (itemHandler.getStackInSlot(2).getCount() < 64 && itemHandler.getStackInSlot(3).getCount() < 64) {
                    if (this.storage.getEnergyStored() >= r.neededEnergy()) {

                        energyUsagePerTick = r.neededEnergy() / r.getProgressTime();
                        removeEnergy(energyUsagePerTick);

                        ++this.progress;

                        if (this.progress >= r.getProgressTime()) {
                            this.progress = 0;

                            ItemStack output = r.getRecipeOutput();

                            alloying(output, r.getCount());

                            markDirty();
                        }
                    }
                }
            }
        } else {
            if (this.progress > 0) {
                this.progress = 0;
            }
        }

        this.progressPercent = (int) ((double) (progress) * 100d / (double) (totalTime));
        this.energyPercent = (int) ((double) (this.storage.getEnergyStored()) * 100d / (double) (this.storage.getMaxEnergyStored()));
    }

    private void alloying(ItemStack output, int count) {
        Random r = new Random();
        itemHandler.extractItem(0, 1, false);
        itemHandler.extractItem(1, 1, false);

        for (int i = 0; i < count; i++) {
            itemHandler.insertItem(3, output, false);
            for (int j = 0; j < r.nextInt(2); j++) {
                itemHandler.insertItem(2, RiseItems.SLAG.getDefaultInstance(), false);
            }
        }
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            craft();
        }
    }

    public int getEnergyPercent() {
        return this.energyPercent;
    }

    public void setEnergyPercent(int n) {
        this.energyPercent = n;
    }

    public int getProgressPercent() {
        return this.progressPercent;
    }

    public void setProcessPercent(int percent) {
        this.progressPercent = percent;
    }

    public void removeEnergy(int energy) {
        this.storage.extractEnergy(energy, false);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("screen.phoenixtales.alloy");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new AlloyContainer(i, this.world, this.pos, playerInventory, playerEntity);
    }
}
