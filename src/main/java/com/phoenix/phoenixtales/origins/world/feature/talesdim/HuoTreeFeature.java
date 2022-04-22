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
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class HuoTreeFeature extends Feature<NoFeatureConfig> {

    //TODO trunk is min. 3 - 4 blocks in the air maybe make the trunk higher; place each root independent like the hui tree branches
    //TODO this needs a rewrite

    //this seed is cool 4761177788959882551

    private final BlockState log = OriginsBlocks.HUO_LOG.getDefaultState();
    private final BlockState leave = OriginsBlocks.HUO_LEAVES.getDefaultState().with(OriginsLeavesBlock.DISTANCE, 9);
    private final boolean isGiant;
    private int tries = 0;
    private List<Integer> existing;

    public HuoTreeFeature(Codec<NoFeatureConfig> codec, boolean isGiant) {
        super(codec);
        this.isGiant = isGiant;
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        Map<BlockPos, Integer> roots = new HashMap<>();
        this.existing = new ArrayList<>();
        this.tries = 0;
        BlockPos trunk = pos;
        trunk = this.findGround(reader, trunk);
        if (this.canPlace(reader, trunk.down())) {
            if (!this.isGiant) {
                int trunkHeight = rand.nextInt(2) + 2;
                trunk = trunk.up(trunkHeight);
                //roots
                roots.putAll(this.getRootLocation(trunk, rand));
                roots.putAll(this.getRootLocation(trunk, rand));
                roots.putAll(this.getRootLocation(trunk, rand));
                if (rand.nextFloat() <= 0.5f) {
                    roots.putAll(this.getRootLocation(trunk, rand));
                }
                for (BlockPos root : roots.keySet()) {
                    this.placeRoot(reader, root, trunk, rand, roots.get(root));
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

    //TODo may be even decide which one im using
    //TODO should the roots go out at the beginning and then go down
    /*
            #
           ##
           #
           #
          #
     */
    //TODO or should the roots go out and at the end down
    /*
               ##
            ###
          ##
     */
    private void placeRoot(ISeedReader reader, BlockPos posIn, BlockPos trunk, Random random, int out) {
        int start = random.nextInt(2) - 1;
        posIn = posIn.add(0, start, 0);
        //always place block at the beginning, then loop
        reader.setBlockState(posIn, this.log, 3);
        //the more placed the higher the chance to build down
        int placed = 1;
        float chance_down = 0.6f;
        int count_down = 0;
        int length = random.nextInt(2) + 4;

        for (int i = 0; i < length; i++) {
            // if (placed / 2 >)
            if (placed == 2 && count_down < 2 || placed == 4 && count_down < 3) {
                posIn = posIn.down();
                ++count_down;
            } else {
                if (random.nextFloat() <= chance_down) {
                    posIn = posIn.down();
                    ++count_down;
                } else {
                    if (random.nextFloat() <= 0.1f && placed > (length / 2)) {
                        out = random.nextInt(8);
                    }
                    posIn = this.out(posIn, out);
                }
            }
            reader.setBlockState(posIn, this.log, 3);
            ++placed;
        }
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

    private @NotNull Map<BlockPos, Integer> getRootLocation(BlockPos trunk, Random random) {
        Map<BlockPos, Integer> temp = new HashMap<>();
        int id = this.findRootLocation(random);
        BlockPos pos = this.out(trunk, id);
        temp.put(pos, id);
        return temp;
    }

    private int findRootLocation(Random random) {
        int id = random.nextInt(9);
        if (this.tries <= 18) {
            if (this.existing.contains(id)) {
                ++this.tries;
                return this.findRootLocation(random);
            } else {
                this.tries = 0;
                this.existing.add(id);
                return id;
            }
        }
        return id;
    }

    private boolean canPlace(ISeedReader reader, BlockPos pos) {
        return !(reader.getBlockState(pos).matchesBlock(OriginsBlocks.HUO_LEAVES) || reader.getBlockState(pos).matchesBlock(OriginsBlocks.HUO_LOG));
    }

    private BlockPos findGround(ISeedReader reader, BlockPos pos) {
        BlockPos re = pos;
        for (re = re.up(); reader.isAirBlock(re) && re.getY() > 1; re = re.down()) {
        }
        re = re.up();
        return re;
    }

    //7x7 not 5x5
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
