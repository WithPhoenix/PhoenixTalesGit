package com.phoenix.phoenixtales.rise.service.conduit;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public interface INetwork {

    void add(BlockPos pos);

    void remove(BlockPos pos);

    void update();

    void merge(List<INetwork> networks);

    CompoundNBT serializeNBT();

    void deserializeNBT(CompoundNBT nbt);
}
