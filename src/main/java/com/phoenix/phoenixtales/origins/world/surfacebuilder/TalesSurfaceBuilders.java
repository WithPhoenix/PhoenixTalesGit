package com.phoenix.phoenixtales.origins.world.surfacebuilder;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.ArrayList;
import java.util.List;

public class TalesSurfaceBuilders {
    public static List<SurfaceBuilder<?>> builders = new ArrayList<>();

    public static final AshenPlainsSurfaceBuilder ASHEN_PLAINS_SURFACE_BUILDER = createSurfaceBuilder("ashen_plains_sb", new AshenPlainsSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SearingSurfaceBuilder SEARING_SURFACE_BUILDER = createSurfaceBuilder("searing_sb", new SearingSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final LiquidatedSurfaceBuilder LIQUIDATED_SURFACE_BUILDER = createSurfaceBuilder("liquidated_sb", new LiquidatedSurfaceBuilder(SurfaceBuilderConfig.CODEC));

    public static <SC extends ISurfaceBuilderConfig, SB extends SurfaceBuilder<SC>> SB createSurfaceBuilder(String id, SB surfaceBuilder) {
        ResourceLocation location = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (Registry.SURFACE_BUILDER.keySet().contains(location)) {
            throw new IllegalStateException("Surface Builder " + location.toString() + "\" already registered");
        }

        surfaceBuilder.setRegistryName(location);
        builders.add(surfaceBuilder);
        return surfaceBuilder;
    }


    public static class Configs {
        public static final SurfaceBuilderConfig ASHEN_PLAINS = new SurfaceBuilderConfig(OriginsBlocks.ASHEN_GRASS_BLOCK.getDefaultState(), OriginsBlocks.ASHEN_STONE.getDefaultState(), OriginsBlocks.ASHEN_STONE.getDefaultState());
        public static final SurfaceBuilderConfig SEARING_CONFIG = new SurfaceBuilderConfig(OriginsBlocks.SEARING_GRASS_BLOCK.getDefaultState(), OriginsBlocks.SEARING_STONE.getDefaultState(), OriginsBlocks.SEARING_STONE.getDefaultState());
    }
}
