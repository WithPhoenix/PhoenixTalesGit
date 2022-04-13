package com.phoenix.phoenixtales.origins.world.feature.talesdim;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HuoTreeFeature extends Feature<NoFeatureConfig> {

    private final BlockState log = OriginsBlocks.HUO_LOG.getDefaultState();
    private final BlockState leave = OriginsBlocks.HUO_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 7);

    private boolean isGiant;

    private Random random = new Random();

    public HuoTreeFeature(Codec<NoFeatureConfig> codec, boolean isGiant) {
        super(codec);
        this.isGiant = isGiant;
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {

        BlockPos pos1 = pos;
        List<BlockPos> roots = new ArrayList<>();
        for (pos1 = pos1.up(); reader.isAirBlock(pos1) && pos1.getY() > 1; pos1 = pos1.down()) {
        }

        //starting point
        pos1 = pos1.up();

        ////////*************fill root options************////////////


        if (!this.isGiant) {
            //calculate height of the trunk
            int trunkHeight = random.nextInt(2) + 2;
            BlockPos trunk = pos1.up(trunkHeight);
            ////////*************roots************////////////
            //look for 2-4 locations
            int rootCount = random.nextInt(3) + 2;
            for (int i = 0; i < rootCount; i++) {
                roots.add(getRootStart(reader, pos1, random.nextInt(25)));
            }
            //build on some up and on others to the middle
            //the height is 2-3 blocks

            //build each root after each other
            for (BlockPos root : roots) {
                //always build at the start
                reader.setBlockState(root, this.log, 3);
                //more logic deciding up or in, compare to @trunk
//                boolean up = random.nextBoolean();
//                root = up ? buildUp(reader, root, pos1) : buildRoot(reader, root, pos1);
//                //more logic deciding up or in, compare to @trunk
//                up = random.nextBoolean();
//                root = up ? buildUp(reader, root, pos1) : buildRoot(reader, root, pos1);
                //depending on distance to @trunk build even more
            }


            //repeat

            ////////*************trunk************////////////
            //now i should be 2-3 blocks  in the air in the middle
            //build in the middle 3-6 blocks
            for (int i = 0; i < random.nextInt(4) + 3; i++) {
                reader.setBlockState(trunk.up(i), this.log, 3);
            }

            ////////*************treetop************////////////
            //2-3/4 limbs
            //first go side

            //then build 1(30%)-2(70%) up and place leaves


            ////////*************leaves************////////////


        } else {

            for (int i = 0; i < 14; i++) {
                reader.setBlockState(pos1.up(i), this.log, 3);
            }

        }
        return true;
    }


    //with while loop: while not the same height build root
    //also decides if I should build up or not
    private BlockPos buildRoot(ISeedReader reader, BlockPos posIn, BlockPos midIn) {
        BlockPos build = posIn;
        if (midIn.getY() - build.getY() > 4) {
            //way under the mid
            //so build up 10% it goes in not up
            if (random.nextInt(10) != 9) {
                build = build.up();
                reader.setBlockState(build, this.log, 3);
            } else {
                buildIn(reader, build, midIn);
            }
        } else if (midIn.getY() - build.getY() < 1) {
            //over the mid
            //two options
            //same height
            if (midIn.getY() == build.getY()) {
                if (random.nextInt(10) != 9) {
                    //logic to decide diagonal or straight in
                } else {
                    //10% to build up or down
                    if (random.nextBoolean()) {
                        //up
                        build = build.up();
                        reader.setBlockState(build, this.log, 3);
                    } else {
                        //down
                        build = build.down();
                        reader.setBlockState(build, this.log, 3);
                    }
                }
            //or up
            } else {
                //build down to find to mid
            }
            build = build.down();
            reader.setBlockState(build, this.log, 3);
        } else {

        }

        return build;
    }

    //also decide if i should even build up
    private BlockPos buildIn(ISeedReader reader, BlockPos posIn, BlockPos midIn) {
        BlockPos build = posIn;
        build = build.south();
        return build;
    }

    private BlockPos findGround(ISeedReader reader, BlockPos pos) {
        BlockPos re = pos;
        for (re = re.up(); reader.isAirBlock(re) && re.getY() > 1; re = re.down()) {
        }
        re = re.up();
        return re;
    }

    private BlockPos getRootStart(ISeedReader reader, BlockPos pos, int id) {
        BlockPos re = pos.west(2);
        if (id >= 0 && id < 5) {
            re = re.north(2);
            re = re.east(id);
        } else if (id >= 5 && id < 10) {
            re = re.north(1);
            re = re.east(id - 5);
        } else if (id >= 10 && id < 15) {
            re = re.east(id - 10);
        } else if (id >= 15 && id < 20) {
            re = re.south(1);
            re = re.east(id - 15);
        } else if (id >= 20 && id < 25) {
            re = re.south(2);
            re = re.east(id - 20);
        }
        re = findGround(reader, re);
        return re;
    }

    private void placeLeaves(ISeedReader reader, BlockPos pos) {

    }
}
