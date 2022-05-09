package com.phoenix.phoenixtales.origins.world.tree;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class TalesTree {

    protected abstract Feature getFeature(Random random);

    public boolean attemptGrow(ISeedReader reader, ChunkGenerator generator, BlockPos pos, BlockState blockUnder, Random random) {
        Feature tree = getFeature(random);
        if (tree == null) return false;

        reader.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
        if (tree.generate(reader, generator, random, pos, new NoFeatureConfig())) {
            return true;
        }
        reader.setBlockState(pos, blockUnder, 4);
        return false;
    }
}
