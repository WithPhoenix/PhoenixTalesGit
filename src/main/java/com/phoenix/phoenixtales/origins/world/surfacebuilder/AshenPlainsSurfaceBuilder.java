package com.phoenix.phoenixtales.origins.world.surfacebuilder;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class AshenPlainsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public static final BlockState DIRT = OriginsBlocks.ASHEN_DIRT.getDefaultState();
    public static final BlockState GRASS = OriginsBlocks.ASHEN_GRASS_BLOCK.getDefaultState();
    public static final BlockState GRAVEL = Blocks.GRAVEL.getDefaultState();

    public static final BlockState STONE = OriginsBlocks.ASHEN_STONE.getDefaultState();

    public static final SurfaceBuilderConfig DIRT_CONFIG = new SurfaceBuilderConfig(DIRT, STONE, STONE);
    public static final SurfaceBuilderConfig GRASS_CONFIG = new SurfaceBuilderConfig(GRASS, STONE, STONE);
    public static final SurfaceBuilderConfig GRAVEL_CONFIG = new SurfaceBuilderConfig(GRAVEL, STONE, STONE);

    public AshenPlainsSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        int temp = random.nextInt(3);
        if (temp == 0) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, DIRT_CONFIG);
        } else if (temp == 1) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRASS_CONFIG);
        } else {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRAVEL_CONFIG);

        }
    }
}
