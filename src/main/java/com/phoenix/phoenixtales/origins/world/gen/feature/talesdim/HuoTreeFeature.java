package com.phoenix.phoenixtales.origins.world.gen.feature.talesdim;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class HuoTreeFeature extends Feature<NoFeatureConfig> {
    public HuoTreeFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        //look for a location

        //search for 2-4 locations around and place

        //go 1 to 2 blocks out

        //build on some up and on others to the middle

        //repeat

        //now i should be 2-3 blocks  in the air in the middle

        //build in the middle 3-6 blocks



        return true;
    }
}
