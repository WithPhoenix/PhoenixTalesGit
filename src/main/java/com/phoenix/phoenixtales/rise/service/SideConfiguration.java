package com.phoenix.phoenixtales.rise.service;

import com.phoenix.phoenixtales.rise.service.enums.EnergyHandlingType;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;

public class SideConfiguration {

    private World worldIn;
    private BlockState stateIn;

    private EnergyHandlingType DOWN;
    private EnergyHandlingType UP;
    private EnergyHandlingType NORTH;
    private EnergyHandlingType SOUTH;
    private EnergyHandlingType WEST;
    private EnergyHandlingType EAST;

    public SideConfiguration(World world, BlockState state) {
        this.worldIn = world;
        this.stateIn = state;

    }
}
