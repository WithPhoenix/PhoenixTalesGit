package com.phoenix.phoenixtales.origins.world.surfacebuilder;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class SearingSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public static final BlockState GRASS = OriginsBlocks.SEARING_GRASS_BLOCK.getDefaultState();
    public static final BlockState STONE = OriginsBlocks.SEARING_STONE.getDefaultState();

    public static final SurfaceBuilderConfig GRASS_CONFIG = new SurfaceBuilderConfig(GRASS, STONE, STONE);

    public SearingSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, config);
    }
}