package com.phoenix.phoenixtales.rise.service;

public enum TechnologyTier {
    SIMPLE, NORMAL, ADVANCED, OVERLOADED;

    public static TechnologyTier fromInt(int i) {
        switch (i) {
            case 0:
                return SIMPLE;
            case 1:
                return NORMAL;
            case 2:
                return ADVANCED;
            case 3:
                return OVERLOADED;
            default:
                throw new IllegalArgumentException(i + " is not a valid argument");
        }
    }

    public static int toInt(TechnologyTier tier) {
        switch (tier) {
            case SIMPLE:
                return 0;
            case NORMAL:
                return 1;
            case ADVANCED:
                return 2;
            case OVERLOADED:
                return 3;
            default:
                throw new IllegalArgumentException();
        }
    }
}
