package com.phoenix.phoenixtales.origins.world.gen.feature;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;

public class TalesConfiguredFeatures {


    public static final ConfiguredFeature<?, ?> ASH_PILE = createConfiguredFeature("allium_bush", Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(OriginsBlocks.ASH.getDefaultState()), new SimpleBlockPlacer())).tries(100).preventProjection().build()));

    public static final ConfiguredFeature<?, ?> FALLEN_TRUNK = createConfiguredFeature("fallen_trunk", TalesFeatures.FALLEN_TRUNK.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).chance(10));

    public static <FC extends IFeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF createConfiguredFeature(String id, CF configuredFeature) {
        ResourceLocation tid = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (WorldGenRegistries.CONFIGURED_FEATURE.keySet().contains(tid)) {
            throw new IllegalStateException("Configured Feature: " + tid.toString() + " already exists!");
        }

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, tid, configuredFeature);
        return configuredFeature;
    }


}
