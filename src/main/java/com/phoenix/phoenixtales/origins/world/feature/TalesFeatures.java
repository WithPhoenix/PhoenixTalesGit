package com.phoenix.phoenixtales.origins.world.feature;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import com.phoenix.phoenixtales.origins.world.feature.placer.TalesDoublePlantBlockPlacer;
import com.phoenix.phoenixtales.origins.world.feature.talesdim.*;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class TalesFeatures {

    public static List<Feature<?>> features = new ArrayList<>();

    public static final Feature<NoFeatureConfig> STONY_SURFACE_FEATURE = createFeature("stony_surface_feature", new SurfaceFeature(NoFeatureConfig.CODEC.stable()));
    public static final Feature<NoFeatureConfig> ROCK_FEATURE = createFeature("rock_feature", new RockFeature(NoFeatureConfig.CODEC.stable()));
    public static final Feature<NoFeatureConfig> HUO_TREE_FEATURE = createFeature("huo_tree_feature", new HuoTreeFeature(NoFeatureConfig.CODEC.stable(), false));
    public static final Feature<NoFeatureConfig> GIANT_HUO_TREE_FEATURE = createFeature("giant_huo_tree_feature", new HuoTreeFeature(NoFeatureConfig.CODEC.stable(), true));
    public static final Feature<NoFeatureConfig> HUI_TREE_FEATURE = createFeature("hui_tree_feature", new HuiTreeFeature(NoFeatureConfig.CODEC.stable()));
    public static final Feature<NoFeatureConfig> HUI_BUSH_FEATURE = createFeature("hui_bush_feature", new HuiBushFeature(NoFeatureConfig.CODEC.stable()));

    public static final class States {
        protected static final BlockState HUO_LEAVES = OriginsBlocks.HUO_LEAVES.getDefaultState();
        protected static final BlockState HUO_LOG = OriginsBlocks.HUO_LOG.getDefaultState();
        protected static final BlockState SMOULDERING_STONE = OriginsBlocks.SEARING_STONE.getDefaultState();
        protected static final BlockState ASHEN_TALL_GRASS = OriginsBlocks.TALL_ASHEN_GRASS.getDefaultState();
        protected static final BlockState ASHEN_GRASS = OriginsBlocks.ASHEN_GRASS.getDefaultState();
        protected static final BlockState SEARING_TALL_GRASS = OriginsBlocks.TALL_SEARING_GRASS.getDefaultState();
        protected static final BlockState SEARING_GRASS = OriginsBlocks.SEARING_GRASS.getDefaultState();
        protected static final BlockState REALMSTONE = OriginsBlocks.REALMSTONE.getDefaultState();
    }

    public static final class Configs {
        public static final BlockClusterFeatureConfig ASHEN_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.ASHEN_GRASS), SimpleBlockPlacer.PLACER)).tries(48).build();
        public static final BlockClusterFeatureConfig ASHEN_TALL_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.ASHEN_TALL_GRASS), new TalesDoublePlantBlockPlacer())).tries(48).preventProjection().build();
        public static final BlockClusterFeatureConfig SEARING_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.SEARING_GRASS), SimpleBlockPlacer.PLACER)).tries(48).build();
        public static final BlockClusterFeatureConfig SEARING_TALL_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(States.ASHEN_TALL_GRASS), new TalesDoublePlantBlockPlacer())).tries(48).preventProjection().build();
    }

    public static <C extends IFeatureConfig, F extends Feature<C>> F createFeature(String id, F feature) {
        ResourceLocation location = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (Registry.FEATURE.keySet().contains(location)) {
            throw new IllegalStateException(location.toString() + " already exists!");
        }
        feature.setRegistryName(location);
        features.add(feature);
        return feature;
    }
}
