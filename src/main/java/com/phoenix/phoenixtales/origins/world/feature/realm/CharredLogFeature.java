package com.phoenix.phoenixtales.origins.world.feature.realm;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class CharredLogFeature extends Feature<NoFeatureConfig> {
    private final BlockState logX = OriginsBlocks.CHARRED_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X);
    private final BlockState logY = OriginsBlocks.CHARRED_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y);
    private final BlockState logZ = OriginsBlocks.CHARRED_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z);

    //TODO a charred log; this is a fallen tree, but in many biomes and with the same block; how do i handle the rotation
    public CharredLogFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        return false;
    }

}
