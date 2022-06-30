package com.phoenix.phoenixtales.rise.block.blocks.htfactory;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.RiseEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class HTFactoryTile extends TileEntity implements ISidedInventory, ITickableTileEntity, INamedContainerProvider {
    private final int[] SLOTS_DOWN = new int[]{1, 2};
    private final int[] SLOTS = new int[]{0};
    private NonNullList<ItemStack> items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
    private final RiseEnergyStorage energy = new RiseEnergyStorage(20000, 100, 100, 0);
    private final LazyOptional<IEnergyStorage> storageOpt = LazyOptional.of(() -> energy);
    private int progress;
    private int totalTime;
    private int progressPercent;
    private int energyPercent;

    public HTFactoryTile() {
        super(RiseTileEntities.HT_TILE);
        this.progress = 0;
        this.totalTime = 1;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.items);
        this.energy.deserializeNBT(nbt.getCompound("energy"));
        this.progress = nbt.getInt("progress");
        this.totalTime = nbt.getInt("totalTime");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.put("energy", energy.serializeNBT());
        compound.putInt("progress", this.progress);
        compound.putInt("totalTime", this.totalTime);
        ItemStackHelper.saveAllItems(compound, this.items);
        return compound;
    }

    LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.DOWN, Direction.UP);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side != null) {
            if (side == Direction.DOWN) {
                return handlers[1].cast();
            } else {
                return handlers[0].cast();
            }
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return this.storageOpt.cast();
        }
        return super.getCapability(cap, side);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return this.getCapability(cap, null);
    }

    public NonNullList<ItemStack> getItems() {
        return items;
    }

    private void craft() {
        this.progressPercent = (int) ((double) (progress) * 100d / (double) (totalTime));
        this.energyPercent = (int) ((double) (this.energy.getEnergyStored()) * 100d / (double) (this.energy.getMaxEnergyStored()));
    }

    private void smelt(ItemStack output, int count) {

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

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("screen.phoenixtales.ht");
    }


    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
        return new HTFactoryContainer(id, this.world, this.pos, playerInv, player);
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            craft();
        }
    }

    @Override
    public int getSizeInventory() {
        return 3;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.items) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.items, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.items, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.items.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

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
        this.items.clear();
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_DOWN;
        } else {
            return SLOTS;
        }
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index == 0;
    }
}
