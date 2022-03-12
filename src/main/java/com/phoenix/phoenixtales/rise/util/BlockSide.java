package com.phoenix.phoenixtales.rise.util;

public enum BlockSide {
    NORTH, EAST, SOUTH, WEST, TOP, BOTTOM;

    public static BlockSide intToBlockSide(int value) {
        switch (value) {
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            case 3:
                return WEST;
            case 4:
                return TOP;
            case 5:
                return BOTTOM;
            case 0:
            default:
                return NORTH;

        }
    }
}
