package com.phoenix.phoenixtales.rise.service;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;

public class RiseBlockStateProps {
    public static final IntegerProperty NETWORK = IntegerProperty.create("network", 0, Integer.MAX_VALUE);
    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");
    public static final BooleanProperty HAS_STACK = BooleanProperty.create("stack");
}
