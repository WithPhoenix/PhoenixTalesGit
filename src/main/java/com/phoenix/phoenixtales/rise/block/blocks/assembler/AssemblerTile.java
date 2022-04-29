package com.phoenix.phoenixtales.rise.block.blocks.assembler;

import com.phoenix.phoenixtales.rise.service.RiseRecipeTypes;
import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
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
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AssemblerTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private int progress;
    private int totalTime;
    private int progressPercent;
    private int energy;
    private int maxEnergy; //this is a fixed value, with upgrades this will get higher
    private int energyPercent;

    public AssemblerTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.progress = 0;
        this.totalTime = 1;
        this.energy = 0;
        this.maxEnergy = 10000;
    }

    public AssemblerTile() {
        this(RiseTileEntities.ASSEMBLER_TILE);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.progress = nbt.getInt("processTime");
        this.totalTime = nbt.getInt("totalTime");
        this.energy = nbt.getInt("energy");
        this.maxEnergy = nbt.getInt("mE");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        compound.putInt("processTime", this.progress);
        compound.putInt("totalTime", this.totalTime);
        compound.putInt("energy", this.energy);
        compound.putInt("mE", this.maxEnergy);
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
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    int energyUsagePerTick;

    public void craft() {
        int energyUsagePerTick;
        Inventory inventory = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < 5; i++) {
            inventory.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }

        AssemblingRecipe recipe = world.getRecipeManager().getRecipe(RiseRecipeTypes.ASSEMBLING_RECIPE, inventory, world).orElse(null);

        if (recipe != null) {
            this.totalTime = recipe.getProcessingTime();
            if (ItemHandlerHelper.canItemStacksStack(recipe.getRecipeOutput(), itemHandler.getStackInSlot(5)) || itemHandler.getStackInSlot(5).equals(ItemStack.EMPTY)) {
                if (this.energy >= recipe.neededEnergy()) {
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
        } else {
            if (this.progress > 0) {
                this.progress = 0;
            }
        }

        if (this.energy > this.maxEnergy) {
            this.energy = this.maxEnergy;
        }

        this.progressPercent = (int) ((double) (progress) * 100d / (double) (totalTime));
        this.energyPercent = (int) ((double) (energy) * 100d / (double) (maxEnergy));
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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getEnergyPercent() {
        return this.energyPercent;
    }

    public void setEnergyPercent(int n) {
        this.energyPercent = n;
    }

    public void removeEnergy(int energy) {
        this.energy = this.energy - energy;
    }

    public void addEnergy(int energy) {
        this.energy = this.energy + energy;
    }

    public int getProgressPercent() {
        return this.progressPercent;
    }

    public void setProcessPercent(int percent) {
        this.progressPercent = percent;
    }
}