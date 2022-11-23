package com.phoenix.phoenixtales.rise.block.blocks.fuelgenerator;

import com.google.common.collect.Maps;
import com.phoenix.phoenixtales.rise.service.enums.EnergyHandlingType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraftforge.common.ToolType;

import java.util.Map;

public class FuelGenerator extends Block {
    public static final EnumProperty<EnergyHandlingType> NORTH = EnumProperty.create("north", EnergyHandlingType.class);
    public static final EnumProperty<EnergyHandlingType> SOUTH = EnumProperty.create("south", EnergyHandlingType.class);
    public static final EnumProperty<EnergyHandlingType> WEST = EnumProperty.create("west", EnergyHandlingType.class);
    public static final EnumProperty<EnergyHandlingType> EAST = EnumProperty.create("east", EnergyHandlingType.class);
    public static final EnumProperty<EnergyHandlingType> DOWN = EnumProperty.create("down", EnergyHandlingType.class);
    public static final EnumProperty<EnergyHandlingType> UP = EnumProperty.create("up", EnergyHandlingType.class);

    public static final Map<Direction, EnumProperty<EnergyHandlingType>> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (p) -> {
        p.put(Direction.NORTH, NORTH);
        p.put(Direction.SOUTH, SOUTH);
        p.put(Direction.WEST, WEST);
        p.put(Direction.EAST, EAST);
        p.put(Direction.DOWN, DOWN);
        p.put(Direction.UP, UP);
    });

    public FuelGenerator() {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(5.0f, 5.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool().sound(SoundType.METAL));
        this.setDefaultState(this.getStateContainer().getBaseState().with(NORTH, EnergyHandlingType.NONE).with(SOUTH, EnergyHandlingType.NONE).with(WEST, EnergyHandlingType.NONE).with(EAST, EnergyHandlingType.NONE).with(DOWN, EnergyHandlingType.NONE).with(UP, EnergyHandlingType.NONE));
    }


}
