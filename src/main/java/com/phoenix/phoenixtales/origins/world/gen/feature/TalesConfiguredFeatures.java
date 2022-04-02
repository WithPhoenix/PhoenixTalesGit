package com.phoenix.phoenixtales.origins.world.gen.feature;

import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class TalesConfiguredFeatures {


    public static final ConfiguredFeature<?, ?> SURFACE_MODIFICATION = createConfiguredFeature("surface_modification", TalesFeatures.SURFACE_MODIFICATION.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).chance(14));
    public static final ConfiguredFeature<?, ?> HUO_BUSH = createConfiguredFeature("huo_bush", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(TalesFeatures.States.HUO_LOG), new SimpleBlockStateProvider(TalesFeatures.States.HUO_LEAVES), new BushFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(1), 2), new StraightTrunkPlacer(1, 0, 0), new TwoLayerFeature(0, 0, 0))).setHeightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));
    public static final ConfiguredFeature<?, ?> ROCKS = createConfiguredFeature("rocks", TalesFeatures.ROCK_FEATURE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).chance(10));

    public static <FC extends IFeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF createConfiguredFeature(String id, CF configuredFeature) {
        ResourceLocation tid = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (WorldGenRegistries.CONFIGURED_FEATURE.keySet().contains(tid)) {
            throw new IllegalStateException("Configured Feature: " + tid.toString() + " already exists");
        }

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, tid, configuredFeature);
        return configuredFeature;
    }
}
