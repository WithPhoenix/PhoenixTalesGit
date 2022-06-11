package com.phoenix.phoenixtales.rise.service;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;

public class RiseBlockStateProps {
    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");
    public static final BooleanProperty TIN_SOLDER = BooleanProperty.create("tin_solder");
    public static final BooleanProperty SOLDERING_IRON = BooleanProperty.create("soldering_iron");
    public static final BooleanProperty HAS_LAVA = BooleanProperty.create("lava");
}
