package com.phoenix.phoenixtales.rise.block.blocks.alloyfactory;

import com.phoenix.phoenixtales.rise.RiseRecipeTypes;
import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.item.RiseItems;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Random;

public class AlloyTile extends TileEntity implements ITickableTileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private int processTime;
    private int totalTime;
    private int energy;

    public AlloyTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public AlloyTile() {
        this(RiseTileEntities.ALLOY_TILE);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.processTime = nbt.getInt("processTime");
        this.totalTime = nbt.getInt("totalTime");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        compound.putInt("processTime", this.processTime);
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
//                    return stack.equals(RiseItems.SLAG.getDefaultInstance());
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
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    private void craft() {
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }

        AlloyingRecipe r = world.getRecipeManager().getRecipe(RiseRecipeTypes.ALLOYING_RECIPE, inv, world).orElse(null);

        if (r != null) {
            this.totalTime = r.getProcessTime();
            if (ItemHandlerHelper.canItemStacksStack(r.getRecipeOutput(), itemHandler.getStackInSlot(3)) || itemHandler.getStackInSlot(3).equals(ItemStack.EMPTY)
            && ItemHandlerHelper.canItemStacksStack(RiseItems.SLAG.getDefaultInstance(), itemHandler.getStackInSlot(2)) || itemHandler.getStackInSlot(2).equals(ItemStack.EMPTY)) {
                ++this.processTime;
                if (this.processTime >= r.getProcessTime()) {
                    this.processTime = 0;

                    ItemStack output = r.getRecipeOutput();

                    alloying(output, r.getCount());

                    markDirty();
                }
            }
        } else {
            if (this.processTime > 0) {
                this.totalTime = 0;
                this.processTime = 0;
            }
        }

    }


    private void alloying(ItemStack output, int count) {
        Random r = new Random();
        itemHandler.extractItem(0, 1, false);
        itemHandler.extractItem(1, 1, false);

        for (int i = 0; i < count; i++) {
            itemHandler.insertItem(3, output, false);
            for (int j = 0; j < r.nextInt(3); j++) {
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
}
