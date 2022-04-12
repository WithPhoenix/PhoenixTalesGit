package com.phoenix.phoenixtales.origins.world.surfacebuilder;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class LiquidatedSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public static final BlockState DEBRIS = OriginsBlocks.CHARRED_DEBRIS.getDefaultState();

    public static final SurfaceBuilderConfig CONFIG = new SurfaceBuilderConfig(DEBRIS, DEBRIS, DEBRIS);

    public LiquidatedSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, config);
    }
}