package com.phoenix.phoenixtales.origins.world.feature.talesdim;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import com.phoenix.phoenixtales.origins.block.blocks.OriginsLeavesBlock;
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
    private final BlockState leave = OriginsBlocks.HUI_LEAVES.getDefaultState().with(OriginsLeavesBlock.DISTANCE, 10);

    private final Direction[] options = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
    private BlockPos l0;
    private BlockPos l1;
    private BlockPos l2;
    private int picked0;
    private int picked1;

    public HuiTreeFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockPos trunk = pos;
        trunk = this.findGround(reader, trunk);
        trunk = this.placeTrunk(reader, trunk, rand);

        //spit into two
        l0 = this.findAroundById(rand, trunk, 0);
        l1 = this.findAroundById(rand, trunk, 1);
        //the first never splits
        this.placeBranch(reader, rand, l0, 0);
        //the second always splits
        this.placeBranch(reader, rand, l1, 1);

        //maybe place a third
        float chance = rand.nextFloat();
        if (chance <= 0.25f) {
            l2 = this.findAroundById(rand, trunk, 2);
            //the third never splits
            this.placeBranch(reader, rand, l2, 2);
        }
        return true;
    }

    //TODO do i need to make my own leavesBlock to increase the distance
    private void placeBranch(ISeedReader reader, Random random, BlockPos posIn, int id) {
        if (id == 0) {
            //TODO this one needs to go far more out; also make the leaves a bit smaller extra type?
            int start = random.nextInt(3) - 1;
            posIn = posIn.add(0, start, 0);
            reader.setBlockState(posIn, log, 3);
            posIn = posIn.up();
            //go diagonal  70% or straight 30%
            int out = random.nextInt(8);
            int height = random.nextInt(2) + 1;
            if (random.nextFloat() <= 0.65) {
                posIn = this.out(posIn, out);
            }
            posIn = this.placeByHeight(reader, posIn, height);
            //go out and place leaves
            posIn = this.out(posIn, out);
            height = random.nextInt(3) + 1;
            posIn = this.placeByHeight(reader, posIn, height);
            //now place small leaves
            this.placeLeaves(reader, random, posIn, 0);
        } else if (id == 1) {
            int start = random.nextInt(3) - 1;
            posIn = posIn.add(0, start, 0);
            reader.setBlockState(posIn, log, 3);
            posIn = posIn.up();
            //go diagonal  50%
            int height = random.nextInt(2) + 1;
            if (random.nextFloat() <= 0.5) {
                posIn = this.out(posIn, random.nextInt(8));
                posIn = this.placeByHeight(reader, posIn, height);
            }
            height = random.nextInt(4) + 8;
            posIn = posIn.down();
            posIn = this.placeByHeight(reader, posIn, height);
            //place giant leaves here
            this.placeLeaves(reader, random, posIn, 2);
            //split into two
            //branch should be 3 under the half height of the one
            //TODO this one only has really small leaves at the end
            int flag = random.nextInt(8);
            BlockPos pos1 = this.out(posIn.down((height / 2) + 1), flag);
            reader.setBlockState(pos1, log, 3);
            pos1 = pos1.up();
            pos1 = this.out(pos1, flag);
            height = random.nextInt(2) + 2;
            pos1 = this.placeByHeight(reader, pos1, height);
            //place medium leaves here
            this.placeLeaves(reader, random, pos1, 1);

        } else {
            //should I add this branch with a set chance to the first? then i need to save a pos in branch 0
            if (random.nextFloat() <= 0.5f) {
                //like branch 0 but far more out
                int start = random.nextInt(3) - 1;
                posIn = posIn.add(0, start, 0);
                reader.setBlockState(posIn, log, 3);
                int out = random.nextInt(8);
                int loop = random.nextInt(4) + 3;
                for (int i = 0; i < loop; i++) {
                    if (random.nextFloat() <= 0.6f) {
                        //one up; height
                        posIn = posIn.up();
                    }
                    posIn = this.out(posIn, out);
                    reader.setBlockState(posIn, log, 3);
                }
                //place leaves
                posIn = posIn.up();
                this.placeLeaves(reader, random, posIn, 0);
            } else {
                //add to branch 0
            }
        }
    }

    //returns the pos of the last block placed
    private BlockPos placeByHeight(ISeedReader reader, BlockPos posIn, int height) {
        for (int i = 0; i < height; i++) {
            reader.setBlockState(posIn, log, 2);
            posIn = posIn.up();
        }
        return posIn.down();
    }

    private BlockPos out(BlockPos pos, int i) {
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

    private BlockPos findAroundById(Random random, BlockPos pos, int id) {
        int i = this.findLocationById(id, random);
        this.out(pos, i);
        return pos;
    }

    private int findLocationById(int id, Random random) {
        int i = random.nextInt(8);
        if (id == 0) {
            this.picked0 = i;
            return i;
        } else if (id == 1) {
            if (i != this.picked0) {
                this.picked1 = i;
                return i;
            } else {
                return this.findLocationById(id, random);
            }
        } else {
            if (i != this.picked0 && i != this.picked1) {
                return i;
            } else {
                return this.findLocationById(id, random);
            }
        }
    }

    private BlockPos placeTrunk(ISeedReader reader, BlockPos posIn, Random random) {
        int height = random.nextInt(5) + 5;
        for (int i = 0; i < height; i++) {
            reader.setBlockState(posIn, this.log, 3);
            posIn = posIn.up();
        }
        return posIn.down();
    }

    private BlockPos findGround(ISeedReader reader, BlockPos pos) {
        BlockPos re = pos;
        for (re = re.up(); reader.isAirBlock(re) && re.getY() > 1; re = re.down()) {
        }
        re = re.up();
        return re;
    }

    /**
     * @param type 0 = small leaves, 1 = medium leaves, 1 = big leaves
     */
    private void placeLeaves(ISeedReader reader, Random random, BlockPos posIn, int type) {
        if (type == 0) {
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.north(3).west(), leave, 3);
            }
            reader.setBlockState(posIn.north(3), leave, 3);
            reader.setBlockState(posIn.north(3).east(), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.north(2).west(2), leave, 3);
            }
            reader.setBlockState(posIn.north(2).west(), leave, 3);
            reader.setBlockState(posIn.north(2), leave, 3);
            reader.setBlockState(posIn.north(2).east(), leave, 3);
            reader.setBlockState(posIn.north(2).east(2), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.north().west(3), leave, 3);
            }
            reader.setBlockState(posIn.north().west(2), leave, 3);
            reader.setBlockState(posIn.north().west(), leave, 3);
            reader.setBlockState(posIn.north(), leave, 3);
            reader.setBlockState(posIn.north().east(), leave, 3);
            reader.setBlockState(posIn.north().east(2), leave, 3);
            reader.setBlockState(posIn.north().east(3), leave, 3);
            reader.setBlockState(posIn.west(3), leave, 3);
            reader.setBlockState(posIn.west(2), leave, 3);
            reader.setBlockState(posIn.west(), leave, 3);
            reader.setBlockState(posIn.east(), leave, 3);
            reader.setBlockState(posIn.east(2), leave, 3);
            reader.setBlockState(posIn.east(3), leave, 3);
            reader.setBlockState(posIn.south().west(3), leave, 3);
            reader.setBlockState(posIn.south().west(2), leave, 3);
            reader.setBlockState(posIn.south().west(), leave, 3);
            reader.setBlockState(posIn.south(), leave, 3);
            reader.setBlockState(posIn.south().east(), leave, 3);
            reader.setBlockState(posIn.south().east(2), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south().east(3), leave, 3);
            }
            reader.setBlockState(posIn.south(2).west(2), leave, 3);
            reader.setBlockState(posIn.south(2).west(), leave, 3);
            reader.setBlockState(posIn.south(2), leave, 3);
            reader.setBlockState(posIn.south(2).east(), leave, 3);
            reader.setBlockState(posIn.south(2).east(2), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south(3).west(), leave, 3);
            }
            reader.setBlockState(posIn.south(3), leave, 3);
            reader.setBlockState(posIn.south(3).east(), leave, 3);
            //second layer
            posIn = posIn.up();
            reader.setBlockState(posIn.north(2), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.north(2).east(), leave, 3);
            }
            reader.setBlockState(posIn.north().west(), leave, 3);
            reader.setBlockState(posIn.north(), leave, 3);
            reader.setBlockState(posIn.north().east(), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.north().east(2), leave, 3);
            }
            reader.setBlockState(posIn.west(2), leave, 3);
            reader.setBlockState(posIn.west(), leave, 3);
            reader.setBlockState(posIn, leave, 3);
            reader.setBlockState(posIn.east(), leave, 3);
            reader.setBlockState(posIn.east(2), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south().west(2), leave, 3);
            }
            reader.setBlockState(posIn.south().west(), leave, 3);
            reader.setBlockState(posIn.south(), leave, 3);
            reader.setBlockState(posIn.south().east(), leave, 3);
            reader.setBlockState(posIn.south(2), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south(2).west(), leave, 3);
            }
        } else if (type == 1) {

        } else {
            reader.setBlockState(posIn.north(5).west(), leave, 3);
            reader.setBlockState(posIn.north(5), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.north(5).east(), leave, 3);
            }
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.north(4).west(3), leave, 3);
            }
            reader.setBlockState(posIn.north(4).west(2), leave, 3);
            reader.setBlockState(posIn.north(4).west(), leave, 3);
            reader.setBlockState(posIn.north(4), leave, 3);
            reader.setBlockState(posIn.north(4).east(), leave, 3);
            reader.setBlockState(posIn.north(4).east(2), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.north(4).east(3), leave, 3);
            }
            reader.setBlockState(posIn.north(3).west(3), leave, 3);
            reader.setBlockState(posIn.north(3).west(2), leave, 3);
            reader.setBlockState(posIn.north(3).west(), leave, 3);
            reader.setBlockState(posIn.north(3), leave, 3);
            reader.setBlockState(posIn.north(3).east(), leave, 3);
            reader.setBlockState(posIn.north(3).east(2), leave, 3);
            reader.setBlockState(posIn.north(3).east(3), leave, 3);
            reader.setBlockState(posIn.north(2).west(4), leave, 3);
            reader.setBlockState(posIn.north(2).west(3), leave, 3);
            reader.setBlockState(posIn.north(2).west(2), leave, 3);
            reader.setBlockState(posIn.north(2).west(), leave, 3);
            reader.setBlockState(posIn.north(2), leave, 3);
            reader.setBlockState(posIn.north(2).east(), leave, 3);
            reader.setBlockState(posIn.north(2).east(2), leave, 3);
            reader.setBlockState(posIn.north(2).east(3), leave, 3);
            reader.setBlockState(posIn.north(2).east(4), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.north().west(5), leave, 3);
                reader.setBlockState(posIn.west(5), leave, 3);
            }
            reader.setBlockState(posIn.north().west(4), leave, 3);
            reader.setBlockState(posIn.north().west(3), leave, 3);
            reader.setBlockState(posIn.north().west(2), leave, 3);
            reader.setBlockState(posIn.north().west(), leave, 3);
            reader.setBlockState(posIn.north(), leave, 3);
            reader.setBlockState(posIn.north().east(), leave, 3);
            reader.setBlockState(posIn.north().east(2), leave, 3);
            reader.setBlockState(posIn.north().east(3), leave, 3);
            reader.setBlockState(posIn.north().east(4), leave, 3);
            reader.setBlockState(posIn.west(4), leave, 3);
            reader.setBlockState(posIn.west(3), leave, 3);
            reader.setBlockState(posIn.west(2), leave, 3);
            reader.setBlockState(posIn.west(), leave, 3);
            reader.setBlockState(posIn.east(), leave, 3);
            reader.setBlockState(posIn.east(2), leave, 3);
            reader.setBlockState(posIn.east(3), leave, 3);
            reader.setBlockState(posIn.east(4), leave, 3);
            reader.setBlockState(posIn.east(5), leave, 3);
            reader.setBlockState(posIn.south().west(4), leave, 3);
            reader.setBlockState(posIn.south().west(3), leave, 3);
            reader.setBlockState(posIn.south().west(2), leave, 3);
            reader.setBlockState(posIn.south().west(1), leave, 3);
            reader.setBlockState(posIn.south(), leave, 3);
            reader.setBlockState(posIn.south().east(), leave, 3);
            reader.setBlockState(posIn.south().east(2), leave, 3);
            reader.setBlockState(posIn.south().east(3), leave, 3);
            reader.setBlockState(posIn.south().east(4), leave, 3);
            reader.setBlockState(posIn.south().east(5), leave, 3);
            reader.setBlockState(posIn.south(2).west(4), leave, 3);
            reader.setBlockState(posIn.south(2).west(3), leave, 3);
            reader.setBlockState(posIn.south(2).west(2), leave, 3);
            reader.setBlockState(posIn.south(2).west(1), leave, 3);
            reader.setBlockState(posIn.south(2), leave, 3);
            reader.setBlockState(posIn.south(2).east(), leave, 3);
            reader.setBlockState(posIn.south(2).east(2), leave, 3);
            reader.setBlockState(posIn.south(2).east(3), leave, 3);
            reader.setBlockState(posIn.south(2).east(4), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south(2).east(5), leave, 3);
            }
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south(3).west(4), leave, 3);
            }
            reader.setBlockState(posIn.south(3).west(3), leave, 3);
            reader.setBlockState(posIn.south(3).west(2), leave, 3);
            reader.setBlockState(posIn.south(3).west(1), leave, 3);
            reader.setBlockState(posIn.south(3), leave, 3);
            reader.setBlockState(posIn.south(3).east(), leave, 3);
            reader.setBlockState(posIn.south(3).east(2), leave, 3);
            reader.setBlockState(posIn.south(3).east(3), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south(3).east(4), leave, 3);
            }
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south(4).west(3), leave, 3);
            }
            reader.setBlockState(posIn.south(4).west(2), leave, 3);
            reader.setBlockState(posIn.south(4).west(1), leave, 3);
            reader.setBlockState(posIn.south(4), leave, 3);
            reader.setBlockState(posIn.south(4).east(), leave, 3);
            reader.setBlockState(posIn.south(4).east(2), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south(4).east(3), leave, 3);
            }
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south(5).west(2), leave, 3);
            }
            reader.setBlockState(posIn.south(5).west(), leave, 3);
            reader.setBlockState(posIn.south(5), leave, 3);
            reader.setBlockState(posIn.south(5).east(), leave, 3);
            posIn = posIn.up();
            reader.setBlockState(posIn.north(4), leave, 3);
            reader.setBlockState(posIn.north(4).east(), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.north(3).west(2), leave, 3);
            }
            reader.setBlockState(posIn.north(3).west(), leave, 3);
            reader.setBlockState(posIn.north(3), leave, 3);
            reader.setBlockState(posIn.north(3).east(), leave, 3);
            reader.setBlockState(posIn.north(3).east(2), leave, 3);
            reader.setBlockState(posIn.north(2).west(3), leave, 3);
            reader.setBlockState(posIn.north(2).west(2), leave, 3);
            reader.setBlockState(posIn.north(2).west(), leave, 3);
            reader.setBlockState(posIn.north(2), leave, 3);
            reader.setBlockState(posIn.north(2).east(), leave, 3);
            reader.setBlockState(posIn.north(2).east(2), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.north(2).east(3), leave, 3);
            }
            reader.setBlockState(posIn.north().west(4), leave, 3);
            reader.setBlockState(posIn.north().west(3), leave, 3);
            reader.setBlockState(posIn.north().west(2), leave, 3);
            reader.setBlockState(posIn.north().west(), leave, 3);
            reader.setBlockState(posIn.north(), leave, 3);
            reader.setBlockState(posIn.north().east(), leave, 3);
            reader.setBlockState(posIn.north().east(2), leave, 3);
            reader.setBlockState(posIn.north().east(3), leave, 3);
            reader.setBlockState(posIn.west(4), leave, 3);
            reader.setBlockState(posIn.west(3), leave, 3);
            reader.setBlockState(posIn.west(2), leave, 3);
            reader.setBlockState(posIn.west(), leave, 3);
            reader.setBlockState(posIn, leave, 3);
            reader.setBlockState(posIn.east(), leave, 3);
            reader.setBlockState(posIn.east(2), leave, 3);
            reader.setBlockState(posIn.east(3), leave, 3);
            reader.setBlockState(posIn.east(4), leave, 3);
            reader.setBlockState(posIn.south().west(3), leave, 3);
            reader.setBlockState(posIn.south().west(2), leave, 3);
            reader.setBlockState(posIn.south().west(), leave, 3);
            reader.setBlockState(posIn.south(), leave, 3);
            reader.setBlockState(posIn.south().east(), leave, 3);
            reader.setBlockState(posIn.south().east(2), leave, 3);
            reader.setBlockState(posIn.south().east(3), leave, 3);
            reader.setBlockState(posIn.south().east(4), leave, 3);
            reader.setBlockState(posIn.south(2).west(3), leave, 3);
            reader.setBlockState(posIn.south(2).west(2), leave, 3);
            reader.setBlockState(posIn.south(2).west(), leave, 3);
            reader.setBlockState(posIn.south(2), leave, 3);
            reader.setBlockState(posIn.south(2).east(), leave, 3);
            reader.setBlockState(posIn.south(2).east(2), leave, 3);
            reader.setBlockState(posIn.south(2).east(3), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south(2).east(4), leave, 3);
            }
            reader.setBlockState(posIn.south(3).west(2), leave, 3);
            reader.setBlockState(posIn.south(3).west(), leave, 3);
            reader.setBlockState(posIn.south(3), leave, 3);
            reader.setBlockState(posIn.south(3).east(), leave, 3);
            reader.setBlockState(posIn.south(3).east(2), leave, 3);
            if (random.nextFloat() <= 0.5f) {
                reader.setBlockState(posIn.south(4).west(), leave, 3);
            }
            reader.setBlockState(posIn.south(4), leave, 3);
            reader.setBlockState(posIn.south(4).east(), leave, 3);

        }
    }


//    private boolean valid(int id, int n) {
//
////        for (Direction d : options) {
////            if (reader.getBlockState(posIn.offset(d)).equals(this.log) || reader.getBlockState(posIn.offset(d).down()).equals(this.log) || reader.getBlockState(posIn.offset(d).up()).equals(this.log)
////                    || reader.getBlockState(posIn.offset(d).down(2)).equals(this.log) || reader.getBlockState(posIn.offset(d).up(2)).equals(this.log)) {
////                return false;
////            }
////        }
////        return !(reader.getBlockState(posIn).equals(log) || reader.getBlockState(posIn.down()).equals(log) || reader.getBlockState(posIn.up()).equals(log) || reader.getBlockState(posIn.down(2)).equals(log) || reader.getBlockState(posIn.up(2)).equals(log));
//    }
//    /**
//     * @param reader normal ISeedReader
//     * @param posIn  BlockPos where to build at
//     * @param a      amount of splits
//     * @param min    min blocks build
//     * @param max    max blocks build
//     * @param flag   no infinite loop occurs if this reached a set value () stop building
//     */
//    private void placeTreeTop(ISeedReader reader, BlockPos posIn, int a, int min, int max, int flag) {
//        if (flag < 4) {
//            BlockPos[] splits = new BlockPos[a];
//            for (BlockPos in : splits) {
//                in = posIn;
//            }
//            BlockPos pos1;
//            //search for random locations around the trunk and save in splits
//            for (int i = 0; i < a; i++) {
//                pos1 = posIn;
//                pos1 = around(pos1, splits);
//                splits[i] = pos1;
//            }
//            //now build
//            for (BlockPos split : splits) {
//                if (flag == 0) {
//                    int start = ran.nextInt(3) - 1;
//                    split = split.add(0, start, 0);
//                }
//                int buildAmount = ran.nextInt(max - min + 1);
//                for (int i = 0; i < buildAmount; i++) {
//                    reader.setBlockState(split, log, 3);
//                    split = split.up();
//                }
//                if (ran.nextFloat() <= 0.5f) {
//                    placeTreeTop(reader, split, 2, 1, 3, ++flag);
//                } else {
//                    placeTreeTop(reader, split, 1, 1, 3, ++flag);
//                }
//                //call this method in itself or maybe copy over the contents
//                //and then on the end place the leaves
    // leaves only have 3-5 options to make it a bit easier
//            }
//        }
//    }
//
//
//    private BlockPos around(BlockPos pos, BlockPos... existing) {
//        BlockPos pos1 = pos;
//        //find random around
//        int i = ran.nextInt(8);
//        if (i < 3) {
//            pos = pos.north().west();
//            pos = pos.east(i);
//        } else if (i < 5) {
//            if (i == 3) {
//                pos = pos.west();
//            } else {
//                pos = pos.east();
//            }
//        } else {
//            pos = pos.south().west();
//            pos = pos.east(i - 5);
//        }
//        //check if valid
//        for (BlockPos t : existing) {
//            if (!valid(pos, t)) {
//                pos = around(pos1, t);
//            }
//        }
//        return pos;
//    }


//    //
//    //#
//    //##
//    private boolean valid(BlockPos posIn, BlockPos t) {
//        if (t != null) {
//            return posIn.getX() != t.getX() || posIn.getZ() != t.getZ();
//        }
//        return true;
//    }
}
