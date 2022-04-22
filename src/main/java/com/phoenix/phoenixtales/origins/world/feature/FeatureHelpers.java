package com.phoenix.phoenixtales.origins.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

public class FeatureHelpers {

    public static BlockPos findGround(ISeedReader reader, BlockPos pos) {
        BlockPos re = pos;
        for (re = re.up(); reader.isAirBlock(re) && re.getY() > 1; re = re.down()) {
        }
        re = re.up();
        return re;
    }

    //returns the pos of the last block placed
    public static BlockPos placeByHeight(ISeedReader reader, BlockPos posIn, BlockState state, int height) {
        for (int i = 0; i < height; i++) {
            reader.setBlockState(posIn, state, 2);
            posIn = posIn.up();
        }
        return posIn.down();
    }

    public static BlockPos out(BlockPos pos, int i) {
        switch (i) {
            case 0:
                pos = pos.north().west();
                break;
            case 1:
                pos = pos.north();
                break;
            case 2:
                pos = pos.north().east();
                break;
            case 3:
                pos = pos.west();
                break;
            case 4:
                pos = pos.east();
                break;
            case 5:
                pos = pos.south().west();
                break;
            case 6:
                pos = pos.south();
                break;
            case 7:
                pos = pos.south().east();
                break;
        }
        return pos;
    }
}
