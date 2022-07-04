package com.phoenix.phoenixtales.rise.block.blocks.initial.solderingtable;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.item.RiseItems;
import com.phoenix.phoenixtales.rise.service.RiseEnergyStorage;
import com.phoenix.phoenixtales.rise.service.RiseRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SolderingTableTile extends TileEntity implements IClearable {
    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    private RiseEnergyStorage energy = new RiseEnergyStorage(10000, 20, 100, 0);
    private final LazyOptional<IEnergyStorage> storageLazyOptional = LazyOptional.of(() -> energy);
    private ItemStack tin_solder = new ItemStack(RiseItems.TIN_SOLDER, 0);
    private ItemStack soldering_iron = ItemStack.EMPTY;
    private int progress;
    private final Random RANDOM = new Random();

    public SolderingTableTile() {
        super(RiseTileEntities.SOLDERING_TILE);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.progress = nbt.contains("progress") ? nbt.getInt("progress") : 0;
        this.energy.deserializeNBT(nbt.getCompound("energy"));
        if (nbt.contains("tin")) {
            this.setTin(ItemStack.read(nbt.getCompound("tin")));
        }
        if (nbt.contains("iron")) {
            this.setIron(ItemStack.read(nbt.getCompound("iron")));
        }
        ItemStackHelper.loadAllItems(nbt, this.items);
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("progress", this.progress);
        compound.put("energy", energy.serializeNBT());
        this.writeStacks(compound);
        return compound;
    }

    private CompoundNBT writeStacks(CompoundNBT compound) {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.items);
        if (!this.getTin().isEmpty()) {
            compound.put("tin", this.getTin().write(new CompoundNBT()));
        }
        if (!this.getIron().isEmpty()) {
            compound.put("iron", this.getIron().write(new CompoundNBT()));
        }
        return compound;
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.writeStacks(new CompoundNBT());
    }

    public boolean hasTinAndIron() {
        return this.tin_solder.getCount() >= 3 && this.soldering_iron.getCount() > 0;
    }

    public boolean hasRecipe() {
        for (int i = 0; i < 4; i++) {
            if (this.items.get(i).isEmpty()) {
                return false;
            }
        }
        SolderingRecipe recipe = world != null ? world.getRecipeManager().getRecipe(RiseRecipeTypes.SOLDERING_RECIPE, new Inventory(this.itemsToArray()), world).orElse(null) : null;
        return recipe != null;
    }

    public int getTime() {
        SolderingRecipe recipe = world != null ? world.getRecipeManager().getRecipe(RiseRecipeTypes.SOLDERING_RECIPE, new Inventory(this.itemsToArray()), world).orElse(null) : null;
        return recipe != null ? recipe.getTime() : 0;
    }

    public void craft(World world, PlayerEntity player) {
        if (this.energy.extractEnergy(45,false) > 0) {
            SolderingRecipe recipe = world != null ? world.getRecipeManager().getRecipe(RiseRecipeTypes.SOLDERING_RECIPE, new Inventory(this.itemsToArray()), world).orElse(null) : null;
            if (recipe == null) return;
            this.tin_solder.shrink(RANDOM.nextInt(3) + 1);
            if (tin_solder.getCount() == 0) {
                world.setBlockState(pos, world.getBlockState(pos).with(SolderingTableBlock.TIN_SOLDER, Boolean.valueOf(false)));
            }
            for (int i = 0; i < 4; i++) {
                this.items.set(i, ItemStack.EMPTY);
            }
            if (RANDOM.nextFloat() > recipe.getChanceToFail()) {
                this.items.set(0, recipe.getRecipeOutput());
                world.playSound(player, pos, SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS, 1.0F, RANDOM.nextFloat() * 0.4F + 0.8F);
            }
            this.soldering_iron.damageItem(RANDOM.nextInt(3) + 3, player, t -> {
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1.0F, RANDOM.nextFloat() * 0.4F + 0.8F);
                world.setBlockState(pos, world.getBlockState(pos).with(SolderingTableBlock.SOLDERING_IRON, Boolean.valueOf(false)));
            });
        }
    }

    private ItemStack[] itemsToArray() {
        ItemStack[] stacks = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            stacks[i] = this.items.get(i);
        }
        return stacks;
    }

    public int progress() {
        return this.progress;
    }

    public void clearProgress() {
        this.progress = 0;
    }

    public void increaseProgress() {
        ++this.progress;
    }

    public void setTin(ItemStack stack) {
        this.tin_solder = stack;
        this.markDirty();
    }

    public ItemStack getTin() {
        return this.tin_solder;
    }

    public void setIron(ItemStack stack) {
        this.soldering_iron = stack;
        this.markDirty();
    }

    public ItemStack getIron() {
        return this.soldering_iron;
    }

    public NonNullList<ItemStack> removeAll() {
        NonNullList<ItemStack> list = NonNullList.withSize(6, ItemStack.EMPTY);
        for (int i = 3; i >= 0; i--) {
            if (!this.items.get(i).isEmpty()) {
                list.set(i, this.items.get(i));
                this.items.set(i, ItemStack.EMPTY);
            }
        }
        list.set(4, this.soldering_iron.copy());
        this.soldering_iron = ItemStack.EMPTY;
        world.setBlockState(pos, world.getBlockState(pos).with(SolderingTableBlock.SOLDERING_IRON, Boolean.valueOf(false)));
        list.set(5, this.tin_solder);
        this.tin_solder = new ItemStack(RiseItems.TIN_SOLDER, 0);
        world.setBlockState(pos, world.getBlockState(pos).with(SolderingTableBlock.TIN_SOLDER, Boolean.valueOf(false)));
        return list;
    }


    public ItemStack removeStack() {
        ItemStack stack = ItemStack.EMPTY;
        for (int i = 3; i >= 0; i--) {
            if (!this.items.get(i).isEmpty()) {
                stack = this.items.get(i);
                this.items.set(i, ItemStack.EMPTY);
                return stack;
            }
        }
        if (this.tin_solder.isEmpty()) {
            if (!this.soldering_iron.isEmpty()) {
                stack = this.soldering_iron;
                this.soldering_iron = ItemStack.EMPTY;
                world.setBlockState(pos, world.getBlockState(pos).with(SolderingTableBlock.SOLDERING_IRON, Boolean.valueOf(false)));
            }
        } else {
            stack = this.tin_solder;
            this.tin_solder = new ItemStack(RiseItems.TIN_SOLDER, 0);
            world.setBlockState(pos, world.getBlockState(pos).with(SolderingTableBlock.TIN_SOLDER, Boolean.valueOf(false)));
        }
        return stack.copy();
    }

    public boolean addStack(ItemStack stack) {
        for (int i = 0; i < 4; i++) {
            if (this.items.get(i).isEmpty()) {
                this.items.set(i, stack);
                return true;
            }
        }
        return false;
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void clear() {
        for (int i = 0; i < 4; i++) {
            this.items.set(i, ItemStack.EMPTY);
        }
        this.tin_solder = new ItemStack(RiseItems.TIN_SOLDER, 0);
        this.soldering_iron = ItemStack.EMPTY;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            if (this.getBlockState().get(SolderingTableBlock.FACING).getOpposite() == side) {
                return this.storageLazyOptional.cast();
            }
        }
        return super.getCapability(cap, side);
    }


}
