package com.phoenix.phoenixtales.origins.world.feature;

import com.google.common.collect.ImmutableList;
import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class TalesConfiguredFeatures {


    public static final ConfiguredFeature<?, ?> STONY_SURFACE = createConfiguredFeature("stony_surface", TalesFeatures.STONY_SURFACE_FEATURE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).chance(20));
    public static final ConfiguredFeature<?, ?> HUO_TREE = createConfiguredFeature("huo_tree", TalesFeatures.HUO_TREE_FEATURE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
    public static final ConfiguredFeature<?, ?> HUI_TREE = createConfiguredFeature("hui_tree", TalesFeatures.HUI_TREE_FEATURE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
    public static final ConfiguredFeature<?, ?> GIANT_HUO_TREE = createConfiguredFeature("giant_huo_tree", TalesFeatures.GIANT_HUO_TREE_FEATURE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
    public static final ConfiguredFeature<?, ?> HUO_BUSH = createConfiguredFeature("huo_bush", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(TalesFeatures.States.HUO_LOG), new SimpleBlockStateProvider(TalesFeatures.States.HUO_LEAVES), new BushFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(1), 2), new StraightTrunkPlacer(1, 0, 0), new TwoLayerFeature(0, 0, 0))).setHeightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));
    public static final ConfiguredFeature<?, ?> ROCKS = createConfiguredFeature("rocks", TalesFeatures.ROCK_FEATURE.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).chance(10));

    public static final ConfiguredFeature<?, ?> TREES_SEARING_WOODS = createConfiguredFeature("trees_huo", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(HUO_BUSH.withChance(0.05F), GIANT_HUO_TREE.withChance(0.04F)), HUO_TREE)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(2, 0.05F, 1))));

    public static <FC extends IFeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF createConfiguredFeature(String id, CF configuredFeature) {
        ResourceLocation location = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (WorldGenRegistries.CONFIGURED_FEATURE.keySet().contains(location)) {
            throw new IllegalStateException("Configured Feature: " + location.toString() + " already exists");
        }
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, location, configuredFeature);
        return configuredFeature;
    }
}
