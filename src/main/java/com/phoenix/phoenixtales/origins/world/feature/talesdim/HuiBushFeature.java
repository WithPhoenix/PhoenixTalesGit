package com.phoenix.phoenixtales.origins.world.feature.talesdim;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import com.phoenix.phoenixtales.origins.block.blocks.OriginsLeavesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class HuiBushFeature extends Feature<NoFeatureConfig> {

    private final BlockState log = OriginsBlocks.HUI_LOG.getDefaultState();
    private final BlockState leave = OriginsBlocks.HUI_LEAVES.getDefaultState().with(OriginsLeavesBlock.DISTANCE, 2);

    public HuiBushFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockPos build = pos;
        for (build = build.up(); reader.isAirBlock(build) && build.getY() > 1; build = build.down()) {
        }
        build = build.up();

        for (int i = 0; i < rand.nextInt(3); i++) {
            reader.setBlockState(build, log, 3);
            placeAround(reader, build, 0.7f, rand, false);
            build = build.up();
        }

        BlockPos temp = build;
        switch (rand.nextInt(8)) {
            case 0:
                temp.north();
                this.placeOp(reader, temp, rand);
                break;
            case 1:
                temp = temp.south();
                this.placeOp(reader, temp, rand);
                break;
            case 2:
                temp = temp.east();
                this.placeOp(reader, temp, rand);
                break;
            case 3:
                temp = temp.west();
                this.placeOp(reader, temp, rand);
                break;
            case 4:
                temp = temp.north().east();
                this.placeOp(reader, temp, rand);
                break;
            case 5:
                temp = build.south().west();
                this.placeOp(reader, temp, rand);
                break;
            case 6:
                temp = temp.east().south();
                this.placeOp(reader, temp, rand);
                break;
            case 7:
                temp = temp.west().north();
                this.placeOp(reader, temp, rand);
                break;
        }
        return true;
    }

    private void placeOp(ISeedReader reader, BlockPos temp, Random random) {
        for (int i = 0; i < random.nextInt(3); i++) {
            reader.setBlockState(temp, log, 3);
            placeAround(reader, temp, 0.7f, random, true);
            temp = temp.up();
        }
    }

    private void placeAround(ISeedReader reader, BlockPos pos, float chance, Random random, boolean top) {
        if (top) {
            Direction[] directions = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.UP};
            for (Direction d : directions) {
                BlockPos pos1 = pos.offset(d);
                if (random.nextFloat() <= chance) {
                    if (reader.getBlockState(pos1) != log) {
                        reader.setBlockState(pos1, leave, 3);
                    }
                }
            }
        } else {
            Direction[] directions = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
            for (Direction d : directions) {
                BlockPos pos1 = pos.offset(d);
                if (random.nextFloat() <= chance) {
                    if (reader.getBlockState(pos1) != log) {
                        reader.setBlockState(pos1, leave, 3);
                    }
                }
            }
        }
    }
}
