package com.phoenix.phoenixtales.origins.world.gen.feature;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import com.phoenix.phoenixtales.origins.world.gen.feature.talesdim.HuoTreeFeature;
import com.phoenix.phoenixtales.origins.world.gen.feature.talesdim.RockFeature;
import com.phoenix.phoenixtales.origins.world.gen.feature.talesdim.SurfaceFeature;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class TalesFeatures {

    public static List<Feature<?>> features = new ArrayList<>();

    public static final Feature<NoFeatureConfig> SURFACE_MODIFICATION = createFeature("surface_modification", new SurfaceFeature(NoFeatureConfig.CODEC.stable()));
    public static final Feature<NoFeatureConfig> ROCK_FEATURE = createFeature("rock_feature", new RockFeature(NoFeatureConfig.CODEC.stable()));
    public static final Feature<NoFeatureConfig> HUO_TREE_FEATURE = createFeature("huo_tree_feature", new HuoTreeFeature(NoFeatureConfig.CODEC.stable()));

    public static final class States {
        protected static final BlockState CRYSTAL = OriginsBlocks.CRYSTAL.getDefaultState();
        protected static final BlockState HUO_LEAVES = OriginsBlocks.HUO_LEAVES.getDefaultState();
        protected static final BlockState HUO_LOG = OriginsBlocks.HUO_LOG.getDefaultState();
        protected static final BlockState SMOULDERING_STONE = OriginsBlocks.SMOULDERING_STONE.getDefaultState();
    }

    public static final class Configs {
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
