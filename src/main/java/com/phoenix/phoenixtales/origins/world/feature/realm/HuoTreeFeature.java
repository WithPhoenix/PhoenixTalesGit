package com.phoenix.phoenixtales.origins.world.feature.realm;

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
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class HuoTreeFeature extends Feature<NoFeatureConfig> {

    //TODO trunk is min. 3 - 4 blocks in the air maybe make the trunk higher; place each root independent like the hui tree branches

    //this seed is cool 4761177788959882551
    //this seed to 1129351448522801061

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
//                roots.putAll(this.getRootLocation(trunk, rand));
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
                this.placeTreeTop(reader, trunk, rand);
            } else {
                trunk = this.placeByHeight(reader, trunk, this.log, 20);
            }
            return true;
        }
        return false;
    }

    //these methods are for the treetop
/////////////*******************************************************************************************************\\\\\\\\\\\\\\\\\\\\\\\\\\

    private void placeTreeTop(ISeedReader reader, BlockPos trunk, Random random) {
        Map<BlockPos, Integer> branchesOutMap = this.chooseBranches(trunk, random);
        for (BlockPos pos : branchesOutMap.keySet()) {
            reader.setBlockState(pos, this.log, 3);
            //build a bit out and go 1-2 times up and then place the end
            int length = random.nextInt(3) + 3;
            float chance_up = 0.3f;
            for (int i = 0; i < length; i++) {
                if (random.nextFloat() <= chance_up) {
                    pos = pos.up();
                }
                if (branchesOutMap.containsKey(pos)) {
                    pos = this.out(pos, branchesOutMap.get(pos));
                }
                reader.setBlockState(pos, this.log, 3);
            }
            if (random.nextFloat() <= 0.5f) {
                pos = pos.up();
                reader.setBlockState(pos, this.log, 3);
            }
            this.endBranch(reader, pos, random);
        }
        //60% to build in the middle
        if (random.nextFloat() <= 0.6f) {
            //place branch in the middle
            //this could be to high
            int height = random.nextInt(3) + 2;
            trunk = this.placeByHeight(reader, trunk.up(), this.log, height);
            this.endBranch(reader, trunk, random);
        }
    }

    private Map<BlockPos, Integer> chooseBranches(BlockPos trunk, Random random) {
        Map<BlockPos, Integer> temp = new HashMap<>();
        int id = random.nextInt(8);
        BlockPos l0 = trunk;
        BlockPos l1 = trunk;
        BlockPos l2 = trunk;
        int i0 = 9;
        int i1 = 9;
        int i2 = 9;

        switch (id) {
            case 0:
                l0 = trunk.south();
                l1 = trunk.west();
                l2 = trunk.north().east();
                i0 = 6;
                i1 = 3;
                i2 = 2;
                break;
            case 1:
                l0 = trunk.west();
                l1 = trunk.north();
                l2 = trunk.south().east();
                i0 = 3;
                i1 = 1;
                i2 = 7;
                break;
            case 2:
                l0 = trunk.north();
                l1 = trunk.east();
                l2 = trunk.south().west();
                i0 = 1;
                i1 = 4;
                i2 = 5;
                break;
            case 3:
                l0 = trunk.east();
                l1 = trunk.south();
                l2 = trunk.north().west();
                i0 = 4;
                i1 = 6;
                i2 = 0;
                break;
            case 4:
                l0 = trunk.east();
                l1 = trunk.south().west();
                l2 = trunk.north().west();
                i0 = 4;
                i1 = 5;
                i2 = 0;
                break;
            case 5:
                l0 = trunk.south();
                l1 = trunk.north().west();
                l2 = trunk.north().east();
                i0 = 6;
                i1 = 0;
                i2 = 2;
                break;
            case 6:
                l0 = trunk.west();
                l1 = trunk.north().east();
                l2 = trunk.south().east();
                i0 = 3;
                i1 = 2;
                i2 = 7;
                break;
            case 7:
                l0 = trunk.north();
                l1 = trunk.south().east();
                l2 = trunk.south().west();
                i0 = 1;
                i1 = 7;
                i2 = 5;
                break;
        }
        int start_height;
        start_height = random.nextInt(2) - 1;
        l0 = l0.add(0, start_height, 0);
        start_height = random.nextInt(2) - 1;
        l1 = l1.add(0, start_height, 0);
        start_height = random.nextInt(2) - 1;
        l2 = l2.add(0, start_height, 0);

        temp.put(l0, i0);
        temp.put(l1, i1);
        temp.put(l2, i2);
        return temp;
    }

    private void endBranch(ISeedReader reader, BlockPos posIn, Random random) {
        int height;
        float rand = random.nextFloat();
        if (rand <= 0.1f) {
            height = 4;
        } else if (rand <= 0.6f) {
            height = 2;
        } else {
            height = 3;
        }
        for (int i = 0; i < height; i++) {
            posIn = posIn.up();
            reader.setBlockState(posIn, this.log, 3);
            if (i == height - 1) {
                this.placeAround(reader, posIn, true);
            }
            this.placeAround(reader, posIn, false);
        }
    }

    private void placeAround(ISeedReader reader, BlockPos pos, boolean top) {
        if (top) {
            Direction[] directions = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.UP};
            for (Direction d : directions) {
                BlockPos pos1 = pos.offset(d);
                if (reader.getBlockState(pos1) != log) {
                    reader.setBlockState(pos1, leave, 3);
                }
            }
        } else {
            Direction[] directions = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
            for (Direction d : directions) {
                BlockPos pos1 = pos.offset(d);
                if (reader.getBlockState(pos1) != log) {
                    reader.setBlockState(pos1, leave, 3);
                }
            }
        }
    }

    //the following methods are for the roots
/////////////*******************************************************************************************************\\\\\\\\\\\\\\\\\\\\\\\\\\

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

    //these methods are for the trunk or both the treetop and toots
////////////*******************************************************************************************************\\\\\\\\\\\\\\\\\\\\\\\\\\

    private BlockPos placeStraightTrunk(ISeedReader reader, BlockPos posIn, BlockState state, Random random) {
        int height = random.nextInt(3) + 6;
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


    private boolean canPlace(ISeedReader reader, BlockPos pos) {
        return !(reader.getBlockState(pos).matchesBlock(OriginsBlocks.HUI_LEAVES) || reader.getBlockState(pos).matchesBlock(OriginsBlocks.HUI_LOG) || reader.getBlockState(pos).matchesBlock(OriginsBlocks.HUO_LEAVES) || reader.getBlockState(pos).matchesBlock(OriginsBlocks.HUO_LOG));
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
