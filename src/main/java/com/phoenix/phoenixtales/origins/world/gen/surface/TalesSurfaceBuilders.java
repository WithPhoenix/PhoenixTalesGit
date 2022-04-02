package com.phoenix.phoenixtales.origins.world.gen.surface;

import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import java.util.ArrayList;
import java.util.List;

public class TalesSurfaceBuilders {

    public static List<SurfaceBuilder<?>> surfaceBuilders = new ArrayList<>();

    public static <SBC extends ISurfaceBuilderConfig, SB extends SurfaceBuilder<SBC>> SB createSurfaceBuilder(String id, SB surfaceBuilder) {
        ResourceLocation location = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (Registry.SURFACE_BUILDER.keySet().contains(location)) {
            throw new IllegalStateException("Surface Builder " + location.toString() + " already registered");
        }

        surfaceBuilder.setRegistryName(location);
        TalesSurfaceBuilders.surfaceBuilders.add(surfaceBuilder);
        return surfaceBuilder;
    }
}
