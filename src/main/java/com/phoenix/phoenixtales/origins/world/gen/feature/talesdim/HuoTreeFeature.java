package com.phoenix.phoenixtales.origins.world.gen.feature.talesdim;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
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

    private final BlockState log = OriginsBlocks.HUO_LOG.getDefaultState();
    private final BlockState leave = OriginsBlocks.HUO_LEAVES.getDefaultState();

    private boolean isGiant;

    public HuoTreeFeature(Codec<NoFeatureConfig> codec, boolean isGiant) {
        super(codec);
        this.isGiant = isGiant;
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        Random random = new Random();
        BlockPos pos1 = pos;
        List<BlockPos> roots = new ArrayList<>();
        for (pos1 = pos1.up(); reader.isAirBlock(pos1) && pos1.getY() > 1; pos1 = pos1.down()) {
        }

        //starting point
        pos1 = pos1.up();

        ////////*************fill root options************////////////


        if (!this.isGiant) {
            ////////*************roots************////////////
            //look for 2-4 locations
            int rootCount = rand.nextInt(3) + 2;
            for (int i = 0; i < rootCount; i++) {
                roots.add(getRootStart(reader, pos1, rand.nextInt(25)));
            }
            //first layer
            for (BlockPos root : roots) {
                reader.setBlockState(root, this.log, 2);
            }
            //the height is 2-3 blocks
            //search for 2-4 locations around and place

            //go 1 to 2 blocks out

            //build on some up and on others to the middle

            //repeat

            ////////*************trunk************////////////
            //now i should be 2-3 blocks  in the air in the middle
            //build in the middle 3-6 blocks
            pos1 = pos1.up(random.nextInt(2) + 2);
            for (int i = 0; i < random.nextInt(4) + 3; i++) {
                reader.setBlockState(pos1.up(i), this.log, 2);
            }

            ////////*************treetop************////////////
            //2-3 limbs


            ////////*************leaves************////////////


        } else {

            for (int i = 0; i < 14; i++) {
                reader.setBlockState(pos1.up(i), this.log, 2);
            }

        }
        return true;
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
}
