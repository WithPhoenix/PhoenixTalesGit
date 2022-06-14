package com.phoenix.phoenixtales.rise.service.conduit;

import net.minecraft.nbt.CompoundNBT;

public interface ICableNetwork {

    void update();

    ICableNetwork merge(ICableNetwork... networks);

    CompoundNBT serializeNBT();

    void deserializeNBT(CompoundNBT nbt);
}
