package com.phoenix.phoenixtales.origins.world.feature.talesdim;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import com.phoenix.phoenixtales.origins.block.blocks.OriginsLeavesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HuoTreeFeature extends Feature<NoFeatureConfig> {

    //TODO trunk is min. 3 - 4 blocks in the air maybe make the trunk higher; place each root independent like the hui tree branches
    //TODO this needs a rewrite


    private final BlockState log = OriginsBlocks.HUO_LOG.getDefaultState();
    private final BlockState leave = OriginsBlocks.HUO_LEAVES.getDefaultState().with(OriginsLeavesBlock.DISTANCE, 9);
    private List<BlockPos> roots = new ArrayList<>();
    private List<Integer> existing = new ArrayList<>();
    private final boolean isGiant;
    private int tries = 0;

    public HuoTreeFeature(Codec<NoFeatureConfig> codec, boolean isGiant) {
        super(codec);
        this.isGiant = isGiant;
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockPos trunk = pos;
        trunk = this.findGround(reader, trunk);
        if (this.canPlace(reader, trunk.down())) {
            if (!this.isGiant) {
                int trunkHeight = rand.nextInt(3) + 3;
                trunk = trunk.up(trunkHeight);
                //roots
                int rootCount = rand.nextInt(2) + 3;
                for (int i = 0; i < rootCount; i++) {
                    this.roots.add(this.getRootById(trunk, this.findRoots(rand)));
                }
                for (BlockPos root : roots) {
                    this.placeRoot(reader, root, trunk, rand);
                }
                //trunk
                trunk = this.placeStraightTrunk(reader, trunk, this.log, rand);
                //branches
                //these should be fairly simple, can i use code from @hui_tree
            } else {
                trunk = this.placeByHeight(reader, trunk, this.log, 20);
            }
            return true;
        }
        return false;
    }

    private void placeRoot(ISeedReader reader, BlockPos posIn, BlockPos trunk, Random random) {

    }


    private BlockPos placeStraightTrunk(ISeedReader reader, BlockPos posIn, BlockState state, Random random) {
        int height = random.nextInt(3) + 4;
        return this.placeByHeight(reader, posIn, state, height);
    }

    //returns the pos of the last block placed
    private BlockPos placeByHeight(ISeedReader reader, BlockPos posIn, BlockState state, int height) {
        for (int i = 0; i < height; i++) {
            reader.setBlockState(posIn, state, 2);
            posIn = posIn.up();
        }
        return posIn.down();
    }

    private int findRoots(Random random) {
        int id = random.nextInt(8);
        if (this.existing.contains(id)) {
            return this.findRoots(random);
        } else {
            this.existing.add(id);
            return id;
        }
    }

    private boolean canPlace(ISeedReader reader, BlockPos pos) {
        return !(reader.getBlockState(pos).matchesBlock(OriginsBlocks.HUO_LEAVES) || reader.getBlockState(pos).matchesBlock(OriginsBlocks.HUO_LOG));
    }

    private BlockPos getRootById(BlockPos pos, int id) {
        switch (id) {
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

    //4x4 not 3x3
//    private BlockPos getRootById(ISeedReader reader, BlockPos pos, int id) {
//        BlockPos re = pos.west(2);
//        if (id >= 0 && id < 5) {
//            re = re.north(2);
//            re = re.east(id);
//        } else if (id >= 5 && id < 10) {
//            re = re.north(1);
//            re = re.east(id - 5);
//        } else if (id >= 10 && id < 15) {
//            re = re.east(id - 10);
//        } else if (id >= 15 && id < 20) {
//            re = re.south(1);
//            re = re.east(id - 15);
//        } else if (id >= 20 && id < 25) {
//            re = re.south(2);
//            re = re.east(id - 20);
//        }
//        re = findGround(reader, re);
//        return re;
//    }

    private BlockPos findGround(ISeedReader reader, BlockPos pos) {
        BlockPos re = pos;
        for (re = re.up(); reader.isAirBlock(re) && re.getY() > 1; re = re.down()) {
        }
        re = re.up();
        return re;
    }//
//    private void placeRoot(ISeedReader reader, BlockPos posIn, BlockPos trunk, Random random) {
//
//
//
//        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        int dif = Math.abs(trunk.getY() - posIn.getY());
//        if (posIn.getY() < trunk.getY()) {
//            //the root is under the trunk, i end at the height of the trunk
//
//            for (int i = 0; i < dif; i++) {
//                reader.setBlockState(posIn, this.log, 3);
//                posIn = this.inOrUp(posIn, trunk, random, true);
//            }
//            //i need to calculate a value using trunk Y and root Y because if the root is really far under the trunk i want to make sure to build up
//            //the distance from root to trunk decides if i go up or in
//            //how do i know in what direction i have to build in, the root can be in every direction
////            //place one more log with a set chance 50% to get a bit more variation
////            reader.setBlockState(posIn.up(), this.log, 3);
//        } else if (posIn.getY() > trunk.getY()) {
//            //the root is over the trunk, build down, i should always end at the height of the trunk
//            for (int i = 0; i < dif; i++) {
//                reader.setBlockState(posIn, this.log, 3);
//                posIn = this.inOrUp(posIn, trunk, random, false);
//            }
//        }
//    }

//    //this checks if i should go in and returns the calculated pos
//    private BlockPos inOrUp(BlockPos posIn, BlockPos trunk, Random random, boolean lower) {
//        int difY = Math.abs(trunk.getY() - posIn.getY());
//        int difX = Math.abs(trunk.getX() - posIn.getX());
//        int difZ = Math.abs(trunk.getZ() - posIn.getZ());
//        //make a float chance and add chance to it
//        //in the end i have a value telling me the chance to build in
//        //then i need to find out where i build in
//        float weightY = difY * 0.15f;
//        //the higher the dif the lower the chance to build in
//        float cY = 1 - (difY * 0.15f);
//        //the fare out the higher th chance to build in
//        float cX = difX == 2 ? 0.75f : 0;
//        float cZ = difZ == 2 ? 0.75f : 0;
//
//        float chance = (cY * weightY) + (cX * ((1 - weightY) / 2)) + (cZ * ((1 - weightY) / 2));
//
//        if (random.nextFloat() <= chance) {
//            posIn = this.in(posIn, trunk, difX == 2, difZ == 2);
//            if (random.nextFloat() <= 0.5f) {
//                posIn = lower ? posIn.up() : posIn.down();
//            }
//        } else {
//            posIn = lower ? posIn.up() : posIn.down();
//        }
//        return posIn;
//    }
//
//    private BlockPos in(BlockPos posIn, BlockPos trunk, boolean xV, boolean zV) {
//        int x = xV ? (trunk.getX() > posIn.getX() ? 1 : -1) : 0;
//        int z = zV ? (trunk.getZ() > posIn.getZ() ? 1 : -1) : 0;
//        posIn = posIn.add(x, 0, z);
//        return posIn;
//    }
}
