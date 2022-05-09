package com.phoenix.phoenixtales.rise.service;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnergyHandlingType implements IStringSerializable {
    NONE, RECEIVE, EXTRACT;

    public boolean isNone() {
        return this == NONE;
    }

    public boolean isReceive() {
        return this == RECEIVE;
    }

    public boolean isExtract() {
        return this == EXTRACT;
    }

    @Override
    public String getString() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
