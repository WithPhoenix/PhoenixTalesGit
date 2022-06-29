package com.phoenix.phoenixtales.rise.block.blocks.htfactory;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressContainer;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class HTTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    public HTTile() {
        super(RiseTileEntities.HT_TILE);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return super.write(compound);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return super.getCapability(cap, side);
    }

    private void craft() {

    }

    private void smelt(ItemStack output, int count) {

    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("screen.phoenixtales.ht");
    }


    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new PressContainer(p_createMenu_1_, this.world, this.pos, p_createMenu_2_, p_createMenu_3_);
    }

    @Override
    public void tick() {
        if (!world.isRemote()) {
            craft();
        }
    }
}
