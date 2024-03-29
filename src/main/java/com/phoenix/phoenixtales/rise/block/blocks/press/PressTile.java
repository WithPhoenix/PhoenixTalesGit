package com.phoenix.phoenixtales.rise.block.blocks.press;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.RiseEnergyStorage;
import com.phoenix.phoenixtales.rise.service.RiseRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PressTile extends TileEntity implements ITickableTileEntity, ISidedInventory, INamedContainerProvider {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final RiseEnergyStorage storage = new RiseEnergyStorage(20000, 50, 50, 0);
    private final LazyOptional<IEnergyStorage> storageOpt = LazyOptional.of(() -> storage);
    private static final int[] slots_up = new int[]{0};
    private static final int[] slots_down = new int[]{1};
    private int progress;
    private int totalTime;
    private int progressPercent;
    private int energyPercent;

    public PressTile() {
        super(RiseTileEntities.PRESS_TILE);
        this.progress = 0;
        this.totalTime = 1;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.storage.deserializeNBT(nbt.getCompound("storage"));
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

    public IEnergyStorage getEnergyStorage() {
        return this.storage;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        } else if (cap == CapabilityEnergy.ENERGY) {
            return this.storageOpt.cast();
        }

        return super.getCapability(cap, side);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return this.getCapability(cap, null);
    }


    public ItemStack getItemOn(int slot) {
        return itemHandler.getStackInSlot(slot);
    }

    private void craft() {
        int energyUsagePerTick;
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }

        PressingRecipe recipe = world.getRecipeManager().getRecipe(RiseRecipeTypes.PRESS_RECIPE, inv, world).orElse(null);

        if (recipe != null) {
            this.totalTime = recipe.getProcessingTime();
            if (ItemHandlerHelper.canItemStacksStack(recipe.getRecipeOutput(), itemHandler.getStackInSlot(1)) || itemHandler.getStackInSlot(1).equals(ItemStack.EMPTY)) {
                if (itemHandler.getStackInSlot(1).getCount() < 64) {
                    if (this.storage.getEnergyStored() >= recipe.neededEnergy()) {
                        //calculate how much energy needs to be used per tick
                        energyUsagePerTick = recipe.neededEnergy() / recipe.getProcessingTime();
                        removeEnergy(energyUsagePerTick);

                        ++this.progress;

                        if (this.progress >= recipe.getProcessingTime()) {
                            this.progress = 0;

                            ItemStack output = recipe.getRecipeOutput();

                            press(output, recipe.getCount());

                            markDirty();
                        }
                    }
                }//progress will be saved if not enough energy is present
            }
        } else {
            if (this.progress > 0) {
                this.progress = 0;
            }
        }

        //Bretzelfresser#1927
        this.progressPercent = (int) ((double) (progress) * 100d / (double) (totalTime));
        this.energyPercent = (int) ((double) (this.storage.getEnergyStored()) * 100d / (double) (this.storage.getMaxEnergyStored()));
    }

    private void press(ItemStack output, int count) {
        itemHandler.extractItem(0, 1, false);

        for (int i = 0; i < count; i++) {
            itemHandler.insertItem(1, output, false);
        }
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            craft();
        }
    }

    @Override
    public int getSizeInventory() {
        return 2;
    }


    @Override
    public boolean isEmpty() {
//        for(ItemStack itemstack : this.items) {
//            if (!itemstack.isEmpty()) {
//                return false;
//            }
//        }
//
//        return true;
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.itemHandler.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return itemHandler.extractItem(index, count, false);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = itemHandler.getStackInSlot(index);
        itemHandler.extractItem(index, stack.getCount(), false);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clear() {

    }


    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("screen.phoenixtales.press");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new PressContainer(p_createMenu_1_, this.world, this.pos, p_createMenu_2_, p_createMenu_3_);
    }


    public void removeEnergy(int energy) {
        this.storage.extractEnergy(energy, false);
    }

    public int getEnergyPercent() {
        return this.energyPercent;
    }

    public int getEnergy() {
        return this.storage.getEnergyStored();
    }

    public int getMax() {
        return this.storage.getMaxEnergyStored();
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


    /**
     * items can be inserted every side besides down, this is the extract side;
     */
    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return slots_down;
        }
        if (side == Direction.UP) {
            return slots_up;
        }
        return null;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @org.jetbrains.annotations.Nullable Direction direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1) {
            return true;
        }
        return direction == Direction.UP && index == 0;
    }
}