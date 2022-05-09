package com.phoenix.phoenixtales.origins.world.feature.placer;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.blocks.OriginsDoublePlant;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;

import java.util.Random;

public class TalesDoublePlantBlockPlacer extends BlockPlacer {
    public static final Codec<TalesDoublePlantBlockPlacer> CODEC;
    public static final TalesDoublePlantBlockPlacer PLACER = new TalesDoublePlantBlockPlacer();

    protected BlockPlacerType<?> getBlockPlacerType() {
        return TalesPlacerType.DOUBLE_PLANT;
    }

    public void place(IWorld world, BlockPos pos, BlockState state, Random random) {
        ((OriginsDoublePlant) state.getBlock()).placeAt(world, pos, 2);
    }

    static {
        CODEC = Codec.unit(() -> {
            return PLACER;
        });
    }
}