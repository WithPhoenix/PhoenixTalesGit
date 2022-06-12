package com.phoenix.phoenixtales.rise.block.blocks.assembler;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AssemblerTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handlerOpt = LazyOptional.of(() -> itemHandler);
    private final RiseEnergyStorage storage = new RiseEnergyStorage(20000, 50, 50, 0);
    private final LazyOptional<IEnergyStorage> storageOpt = LazyOptional.of(() -> storage);
    private int progress;
    private int totalTime;
    private int progressPercent;
    private int energyPercent;

    public AssemblerTile() {
        super(RiseTileEntities.ASSEMBLER_TILE);
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
        return new ItemStackHandler(6) {
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

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handlerOpt.cast();
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

    public void craft() {
        int energyUsagePerTick;
        Inventory inventory = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < 5; i++) {
            inventory.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }

        AssemblingRecipe recipe = world != null ? world.getRecipeManager().getRecipe(RiseRecipeTypes.ASSEMBLING_RECIPE, inventory, world).orElse(null) : null;

        if (recipe != null) {
            this.totalTime = recipe.getProcessingTime();
            if (ItemHandlerHelper.canItemStacksStack(recipe.getRecipeOutput(), itemHandler.getStackInSlot(5)) || itemHandler.getStackInSlot(5).equals(ItemStack.EMPTY)) {
                if (itemHandler.getStackInSlot(5).getCount() < 64) {
                    if (this.storage.getEnergyStored() >= recipe.neededEnergy()) {
                        //calculate how much energy needs to be used per tick
                        energyUsagePerTick = recipe.neededEnergy() / recipe.getProcessingTime();
                        removeEnergy(energyUsagePerTick);

                        ++this.progress;
                        if (this.progress >= recipe.getProcessingTime()) {
                            this.progress = 0;

                            ItemStack output = recipe.getRecipeOutput();

                            assemble(output, recipe.getCount());

                            markDirty();
                        }
                    } //progress will be saved if not enough energy is present
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

    private void assemble(ItemStack output, int count) {
        itemHandler.extractItem(0, 1, false);
        itemHandler.extractItem(1, 1, false);
        itemHandler.extractItem(2, 1, false);
        itemHandler.extractItem(3, 1, false);
        itemHandler.extractItem(4, 1, false);

        for (int i = 0; i < count; i++) {
            itemHandler.insertItem(5, output, false);
        }
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            craft();
        }
    }

    public ItemStack getItemOn(int slot) {
        return itemHandler.getStackInSlot(slot);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("screen.phoenixtales.assembler");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new AssemblerContainer(i, this.world, this.pos, playerInventory, playerEntity);
    }

    public void removeEnergy(int energy) {
        this.storage.extractEnergy(energy, false);
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
}