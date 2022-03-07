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
        ResourceLocation bygID = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (Registry.SURFACE_BUILDER.keySet().contains(bygID))
            throw new IllegalStateException("Surface Builder ID: \"" + bygID.toString() + "\" already exists in the Surface Builder registry!");

//        Registry.register(Registry.SURFACE_BUILDER, bygID, surfaceBuilder);
        surfaceBuilder.setRegistryName(bygID); //Forge
        TalesSurfaceBuilders.surfaceBuilders.add(surfaceBuilder);
        return surfaceBuilder;
    }
}
