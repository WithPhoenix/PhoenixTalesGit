package com.phoenix.phoenixtales.origins.world.gen.surfacebuilder;

import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import java.util.ArrayList;
import java.util.List;

public class TalesSurfaceBuilders {
    public static List<SurfaceBuilder<?>> builders = new ArrayList<>();



    public static <SC extends ISurfaceBuilderConfig, SB extends SurfaceBuilder<SC>> SB createSurfaceBuilder(String id, SB surfaceBuilder) {
        ResourceLocation location = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (Registry.SURFACE_BUILDER.keySet().contains(location)) {
            throw new IllegalStateException("Surface Builder ID: \"" + location.toString() + "\" already exists in the Surface Builder registry!");
        }

        surfaceBuilder.setRegistryName(location);
        builders.add(surfaceBuilder);
        return surfaceBuilder;
    }
}
