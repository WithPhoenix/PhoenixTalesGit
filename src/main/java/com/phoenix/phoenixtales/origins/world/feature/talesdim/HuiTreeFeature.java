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

public class HuiTreeFeature extends Feature<NoFeatureConfig> {

    private final BlockState log = OriginsBlocks.HUI_LOG.getDefaultState();
    private final BlockState leave = OriginsBlocks.HUI_LEAVES.getDefaultState();

    private final Random ran = new Random();
    private final Direction[] options = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

    public HuiTreeFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        /*
                   ###  I
                ########I
                    I   I
                    I I
                    I


         */
        BlockPos trunk = pos;
        trunk = this.findGround(reader, trunk);
        this.placeTrunk(reader, trunk);

        float chance = ran.nextFloat();
        if (chance <= 0.75f) {
            placeTreeTop(reader, trunk, 2);
        } else {
            placeTreeTop(reader, trunk, 3);
        }

        return true;
    }

    private void placeTreeTop(ISeedReader reader, BlockPos posIn, int a) {
        BlockPos[] splits = new BlockPos[a];
        BlockPos pos1;
        for (int i = 0; i < a; i++) {
            pos1 = posIn;
            pos1 = around(pos1, splits);
            splits[i] = pos1;
        }
        for (int i = 0; i < a; i++) {
            pos1 = posIn;
            int start = ran.nextInt(3) - 1;
            pos1 = pos1.add(0, start, 0);
            pos1 = around(pos1, splits);
            //call this method in itself or maybe copy over the contents
            //and then on the end place the leaves
        }
    }

    private BlockPos around(BlockPos pos, BlockPos... existing) {
        //find random around
        int i = ran.nextInt(8);
        if (i < 3) {
            pos = pos.north(1).west(1);
            pos = pos.east(i);
        } else if (i < 5) {

        } else {
            pos = pos.south(1);
        }
        for (BlockPos t : existing) {
            if (!valid(pos, t)) {
                pos = around(pos, t);
            }
        }
        return pos;
    }


    private void placeTrunk(ISeedReader reader, BlockPos posIn) {
        int height = ran.nextInt(5) + 5;
        for (int i = 0; i < height; i++) {
            reader.setBlockState(posIn, this.log, 3);
            posIn = posIn.up();
        }
    }

    private boolean valid(BlockPos pos, BlockPos t) {
        return
                //x check
                pos.getX() != t.getX() || pos.getX() != t.getX() + 1 || pos.getX() != t.getX() - 1
                        //z check
                        && pos.getZ() != t.getZ() || pos.getZ() != t.getZ() + 1 || pos.getZ() != t.getZ() - 1;
    }

    private BlockPos findGround(ISeedReader reader, BlockPos pos) {
        BlockPos re = pos;
        for (re = re.up(); reader.isAirBlock(re) && re.getY() > 1; re = re.down()) {
        }
        re = re.up();
        return re;
    }
}
