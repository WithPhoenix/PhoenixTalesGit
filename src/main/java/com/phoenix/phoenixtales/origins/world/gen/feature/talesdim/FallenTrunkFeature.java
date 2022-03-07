package com.phoenix.phoenixtales.origins.world.gen.feature.talesdim;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class FallenTrunkFeature extends Feature<NoFeatureConfig> {
    private final BlockState log = OriginsBlocks.HUO_LOG.getDefaultState();

    public FallenTrunkFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockPos pos1 = pos;
        for (pos1 = pos1.up(); reader.isAirBlock(pos1) && pos1.getY() > 1; pos1 = pos1.down()) {
        }

        Random r = new Random();
        int dir = r.nextInt(4);
        int count = r.nextInt(5) + 2;
        switch (dir) {
            case 0:

                for (int i = 0; i < count; i++) {
                    reader.setBlockState(pos1.north(i), this.log, 2);
                }
                break;
            case 1:

                for (int i = 0; i < count; i++) {
                    reader.setBlockState(pos1.east(i), this.log, 2);
                }
                break;
            case 2:

                for (int i = 0; i < count; i++) {
                    reader.setBlockState(pos1.south(i), this.log, 2);
                }
                break;
            case 3:
            default:
                for (int i = 0; i < count; i++) {
                    reader.setBlockState(pos1.west(i), this.log, 2);
                }
                break;

        }

        return true;
    }
}
