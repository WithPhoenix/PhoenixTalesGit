package com.phoenix.phoenixtales.rise.service.enums;

public enum TechnologyType {
    SIMPLE, NORMAL, IMPROVED, OVERLOADED;

    public int transferRate() {
        switch (this) {
            case NORMAL:
                return 6240;
            case IMPROVED:
                return 40200;
            case OVERLOADED:
                return 250000;
            case SIMPLE:
            default:
                return 1040;
        }
    }
}
