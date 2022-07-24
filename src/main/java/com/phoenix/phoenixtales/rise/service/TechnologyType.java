package com.phoenix.phoenixtales.rise.service;

public enum TechnologyType {
    SIMPLE, NORMAL, IMPROVED, OVERLOADED;

    public int getCableValue() {
        switch (this) {
            case NORMAL:
                return 500;
            case IMPROVED:
                return 2000;
            case OVERLOADED:
                return 5000;
            case SIMPLE:
            default:
                return 100;
        }
    }
}
