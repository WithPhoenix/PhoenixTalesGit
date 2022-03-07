package com.phoenix.phoenixtales.origins.world.gen.feature;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.world.gen.feature.talesdim.FallenTrunkFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class TalesFeatures {

    public static List<Feature<?>> features = new ArrayList<>();

    public static final Feature<NoFeatureConfig> FALLEN_TRUNK = createFeature("fallen_trunk", new FallenTrunkFeature(NoFeatureConfig.CODEC.stable()));

    public static <C extends IFeatureConfig, F extends Feature<C>> F createFeature(String id, F feature) {
        ResourceLocation tid = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (Registry.FEATURE.keySet().contains(tid)) {
            throw new IllegalStateException(tid.toString() + " already exists!");
        }
//        Registry.register(Registry.FEATURE, tid, feature);
        feature.setRegistryName(tid); //Forge
        features.add(feature);
        return feature;
    }
}
