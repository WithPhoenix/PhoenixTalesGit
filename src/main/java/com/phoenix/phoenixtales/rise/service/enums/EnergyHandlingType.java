package com.phoenix.phoenixtales.rise.service.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum EnergyHandlingType implements IStringSerializable {
    NONE(0), INPUT(1), OUTPUT(2);

    private final int index;

   EnergyHandlingType(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }

    public boolean isNone() {
        return this == NONE;
    }

    public boolean isReceive() {
        return this == INPUT;
    }

    public boolean isExtract() {
        return this == OUTPUT;
    }

    public static EnergyHandlingType byIndex(int i) {
        switch (i) {
            case 1:
                return INPUT;
            case 2:
                return OUTPUT;
            default:
                return NONE;
        }
    }

    @Override
    public String getString() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
