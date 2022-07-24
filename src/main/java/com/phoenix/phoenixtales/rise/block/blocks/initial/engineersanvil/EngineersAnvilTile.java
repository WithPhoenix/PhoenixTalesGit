package com.phoenix.phoenixtales.rise.block.blocks.initial.engineersanvil;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.RiseRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

public class EngineersAnvilTile extends TileEntity implements IClearable {
    private ItemStack stack = ItemStack.EMPTY;

    public EngineersAnvilTile() {
        super(RiseTileEntities.ENGINEERS_ANVIL);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        if (nbt.contains("stack")) {
            this.setStack(ItemStack.read(nbt.getCompound("stack")));
        }
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        this.writeStack(compound);
        return compound;
    }

    private CompoundNBT writeStack(CompoundNBT compound) {
        super.write(compound);
        if (!this.getStack().isEmpty()) {
            compound.put("stack", this.getStack().write(new CompoundNBT()));
        }
        return compound;
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.writeStack(new CompoundNBT());
    }

    public ItemStack getStack() {
        return this.stack;
    }

    public ItemStack getStackCopied() {
        return this.stack.copy();
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
        this.markDirty();
    }

    public boolean craft() {
        ForgingRecipe recipe = world != null ? world.getRecipeManager().getRecipe(RiseRecipeTypes.FORGING_RECIPE, new Inventory(this.getStack()), world).orElse(null) : null;
        if (recipe != null) {
            this.setStack(recipe.getRecipeOutput());
            return true;
        }
        return false;
    }

    public boolean hasRecipe() {
        ForgingRecipe recipe = world != null ? world.getRecipeManager().getRecipe(RiseRecipeTypes.FORGING_RECIPE, new Inventory(this.getStack()), world).orElse(null) : null;
        return recipe != null;
    }

    public boolean hasItem() {
        return !this.stack.isEmpty();
    }

    @Override
    public void clear() {
        this.setStack(ItemStack.EMPTY);
    }

}
