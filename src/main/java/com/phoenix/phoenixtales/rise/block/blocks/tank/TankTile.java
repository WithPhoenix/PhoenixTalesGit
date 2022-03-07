package com.phoenix.phoenixtales.rise.block.blocks.tank;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fluids.FluidStack;

public class TankTile extends TileEntity {

    private FluidStack fluid = FluidStack.EMPTY;

    public TankTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public TankTile() {
        this(RiseTileEntities.TANK_TILE);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.fluid = FluidStack.loadFluidStackFromNBT(nbt.getCompound("fluid"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("fluid", this.fluid.writeToNBT(new CompoundNBT()));
        return super.write(compound);
    }

//    public int getMB() {
//        return mb;
//    }
//
//    public void setMB(int fluid_mb) {
//        this.mb = fluid_mb;
//    }
//
//    public void removeMB(int mb) {
//        this.mb = this.mb - mb;
//    }
//
//    public void addMB(int mb) {
//        this.mb = this.mb + mb;
//    }
//
//    public String getFluidS() {
//        return fluidS;
//    }
//
//    public void setFluidS(String fluidS) {
//        this.fluidS = fluidS;
//    }


}
