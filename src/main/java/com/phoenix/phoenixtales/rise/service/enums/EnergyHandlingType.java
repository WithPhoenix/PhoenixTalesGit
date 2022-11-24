package com.phoenix.phoenixtales.rise.service.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnergyHandlingType implements IStringSerializable {
    NONE, INPUT, OUTPUT;

    public boolean isNone() {
        return this == NONE;
    }

    public boolean isReceive() {
        return this == INPUT;
    }

    public boolean isExtract() {
        return this == OUTPUT;
    }

    @Override
    public String getString() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
