package com.phoenix.phoenixtales.rise.service.conduit;

import net.minecraft.nbt.CompoundNBT;

public interface ICableGraph {

    void update();

    CompoundNBT serializeNBT();

    void deserializeNBT(CompoundNBT nbt);
}
