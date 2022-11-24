package com.phoenix.phoenixtales.rise.service;

import com.phoenix.phoenixtales.rise.block.blocks.EnergyBaseBlock;
import com.phoenix.phoenixtales.rise.service.enums.EnergyHandlingType;
import com.phoenix.phoenixtales.rise.service.enums.RelativeDirection;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.EnumMap;

public class SideConfiguration implements INBTSerializable<CompoundNBT> {

    private final TileEntity tileIn;

    private final EnumMap<Direction, EnergyHandlingType> CONFIG = new EnumMap<>(Direction.class);

    public SideConfiguration(TileEntity tile) {
        this.tileIn = tile;
        this.reset();
    }


    public void set(Direction direction, EnergyHandlingType type) {
        CONFIG.put(direction, type);
    }

    public void set(RelativeDirection direction, EnergyHandlingType type) {
        CONFIG.put(relativeToLocal(direction), type);
    }

    public EnergyHandlingType get(Direction direction) {
        return CONFIG.get(direction);
    }

    public EnergyHandlingType get(RelativeDirection direction) {
        return CONFIG.get(relativeToLocal(direction));
    }

    public boolean canOutput(Direction direction) {
        return CONFIG.get(direction).isExtract();
    }

    public void reset() {
        for (Direction d : Direction.values()) {
            CONFIG.put(d, EnergyHandlingType.NONE);
        }
    }

    private Direction relativeToLocal(RelativeDirection direction) {
        Direction facing = tileIn.getBlockState().get(EnergyBaseBlock.FACING);
        Direction re;
        switch (direction) {
            case BACK:
                re = facing.getOpposite();
                break;
            case LEFT:
                re = this.getClockWise(facing);
                break;
            case RIGHT:
                re = this.getCounterClockWise(facing);
                break;
            case BOTTOM:
                re = Direction.DOWN;
                break;
            case TOP:
                re = Direction.UP;
                break;
            default:
                re = facing;
        }
        return re;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }

    private Direction getClockWise(Direction d) {
        Direction[] directions = new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
        for (int i = 0; i < directions.length; i++) {
            if (directions[i] == d) {
                return directions[i == directions.length - 1 ? 0 : (i + 1)];
            }
        }
        throw new IllegalArgumentException(d + " is not a valid argument");
    }

    private Direction getCounterClockWise(Direction d) {
        Direction[] directions = new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
        for (int i = 0; i < directions.length; i++) {
            if (directions[i] == d) {
                return directions[(i == 0 ? directions.length : i) - 1];
            }
        }
        throw new IllegalArgumentException(d + " is not a valid argument");
    }
}
