package com.phoenix.phoenixtales.rise.block.blocks.heatgenerator;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.service.RiseEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HeatGeneratorTile extends TileEntity implements ITickableTileEntity {
    private final Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.UP, Direction.WEST, Direction.EAST};
    private final RiseEnergyStorage storage = new RiseEnergyStorage(500, 500, 500, 0);
    private final LazyOptional<IEnergyStorage> storageOpt = LazyOptional.of(() -> storage);

    public HeatGeneratorTile() {
        super(RiseTileEntities.HEAT_GENERATOR);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.storage.deserializeNBT(nbt.getCompound("storage"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("storage", storage.serializeNBT());
        return super.write(compound);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return this.getCapability(cap, null);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            if (side == Direction.DOWN) return super.getCapability(cap, side);
            return this.storageOpt.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
            if (this.world.getBlockState(pos.down()).getBlock() == Blocks.LAVA) {
                world.setBlockState(pos, this.getBlockState().with(HeatGeneratorBlock.LAVA, Boolean.valueOf(true)));
            }
            if (this.world.getBlockState(pos.down()).getBlock() != Blocks.LAVA) {
                world.setBlockState(pos, this.getBlockState().with(HeatGeneratorBlock.LAVA, Boolean.valueOf(false)));
            }
            if (this.getBlockState().get(HeatGeneratorBlock.LAVA)) {
                for (Direction d : this.directions) {
                    TileEntity neighbor = world != null ? world.getTileEntity(this.pos.offset(d)) : null;
                    if (neighbor != null) {
                        neighbor.getCapability(CapabilityEnergy.ENERGY, d.getOpposite()).ifPresent(cap -> {
                            int push = this.storage.extractEnergy(this.storage.getMaxExtract(), true);
                            if (push > 0) {
                                push = cap.receiveEnergy(push, false);
                                this.storage.extractEnergy(push, false);
                            }
                        });
                    }
                }
            }
        }
    }
}
