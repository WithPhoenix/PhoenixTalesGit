package com.phoenix.phoenixtales.origins.world.feature.realm;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class RockFeature extends Feature<NoFeatureConfig> {
    //random height 1 - 5 closer to mid higher on random area
    public RockFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        while(reader.isAirBlock(pos) && pos.getY() > 2) {
            pos = pos.down();
        }

        return true;
    }
}
