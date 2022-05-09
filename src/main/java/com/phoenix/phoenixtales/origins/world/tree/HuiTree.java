package com.phoenix.phoenixtales.origins.world.tree;

import com.phoenix.phoenixtales.origins.world.feature.TalesFeatures;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

@SuppressWarnings("rawtypes")
public class HuiTree extends TalesTree {
    @Override
    protected Feature getFeature(Random random) {
        return TalesFeatures.HUI_TREE_FEATURE;
    }
}
