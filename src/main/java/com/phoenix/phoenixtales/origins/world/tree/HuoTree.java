package com.phoenix.phoenixtales.origins.world.tree;

import com.phoenix.phoenixtales.origins.world.feature.TalesFeatures;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

@SuppressWarnings("rawtypes")
public class HuoTree extends TalesTree {
    @Override
    protected Feature getFeature(Random random) {
        return TalesFeatures.HUO_TREE_FEATURE;
    }
}
