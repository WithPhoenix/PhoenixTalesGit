package com.phoenix.phoenixtales.rise.service;

public enum BlockSide {
    DOWN, UP, NORTH, SOUTH, WEST, EAST;

    public static BlockSide[] all = {BlockSide.DOWN, BlockSide.UP, BlockSide.NORTH, BlockSide.SOUTH, BlockSide.WEST, BlockSide.EAST};

    public static BlockSide intToBlockSide(int value) {
        switch (value) {
            case 1:
                return UP;
            case 2:
                return NORTH;
            case 3:
                return SOUTH;
            case 4:
                return WEST;
            case 5:
                return EAST;
            case 0:
            default:
                return DOWN;

        }
    }

    public static BlockSide fromName(String s) {
        switch (s) {
            case "EAST":
                return EAST;
            case "SOUTH":
                return SOUTH;
            case "WEST":
                return WEST;
            case "UP":
                return UP;
            case "NORTH":
                return NORTH;
            case "DOWN":
            default:
                return DOWN;
        }
    }

    public static int blockSideToInt(BlockSide side) {
        switch (side) {
            case UP:
                return 1;
            case NORTH:
                return 2;
            case SOUTH:
                return 3;
            case WEST:
                return 4;
            case EAST:
                return 5;
            case DOWN:
            default:
                return 0;
        }
    }
}
