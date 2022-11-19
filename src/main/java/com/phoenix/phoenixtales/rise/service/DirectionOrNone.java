package com.phoenix.phoenixtales.rise.service;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

public enum DirectionOrNone implements IStringSerializable {
    UP, DOWN, NORTH, EAST, SOUTH, WEST, NONE;

    public Direction toDirection() {
        return null;
    }

    @Override
    public String getString() {
        return this.name();
    }
}
