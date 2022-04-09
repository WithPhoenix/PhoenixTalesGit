package com.phoenix.phoenixtales.origins.world.feature.talesdim;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class HuiBushFeature extends Feature<NoFeatureConfig> {

    private final BlockState log = OriginsBlocks.HUI_LOG.getDefaultState();
    private final BlockState leave = OriginsBlocks.HUI_LEAVES.getDefaultState();

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

        switch (rand.nextInt(8)) {
            case 0:
                for (int i = 0; i < rand.nextInt(3); i++) {
                    reader.setBlockState(build.north(), log, 3);
                    placeAround(reader, build, 0.7f, rand, true);
                    build = build.up();
                }
                break;
            case 1:
                for (int i = 0; i < rand.nextInt(2); i++) {
                    reader.setBlockState(build.south(), log, 3);
                    placeAround(reader, build, 0.7f, rand, true);
                    build = build.up();
                }
                break;
            case 2:
                for (int i = 0; i < rand.nextInt(2); i++) {
                    reader.setBlockState(build.east(), log, 3);
                    placeAround(reader, build, 0.7f, rand, true);
                    build = build.up();
                }
                break;
            case 3:
                for (int i = 0; i < rand.nextInt(2); i++) {
                    reader.setBlockState(build.west(), log, 3);
                    placeAround(reader, build, 0.7f, rand, true);
                    build = build.up();
                }
                break;
            case 4:
                for (int i = 0; i < rand.nextInt(2); i++) {
                    reader.setBlockState(build.north().east(), log, 3);
                    placeAround(reader, build, 0.7f, rand, true);
                    build = build.up();
                }
                break;
            case 5:
                for (int i = 0; i < rand.nextInt(2); i++) {
                    reader.setBlockState(build.south().west(), log, 3);
                    placeAround(reader, build, 0.7f, rand, true);
                    build = build.up();
                }
                break;
            case 6:
                for (int i = 0; i < rand.nextInt(2); i++) {
                    reader.setBlockState(build.east().south(), log, 3);
                    placeAround(reader, build, 0.7f, rand, true);
                    build = build.up();
                }
                break;
            case 7:
                for (int i = 0; i < rand.nextInt(2); i++) {
                    reader.setBlockState(build.west().north(), log, 3);
                    placeAround(reader, build, 0.7f, rand, true);
                    build = build.up();
                }
                break;
        }
        return true;
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
