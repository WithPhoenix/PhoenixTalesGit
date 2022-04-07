package com.phoenix.phoenixtales.origins.block.blocks;

import com.phoenix.phoenixtales.origins.world.tree.TalesTree;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;


public class OriginsSapling extends SaplingBlock {

    TalesTree tree;

    public OriginsSapling(TalesTree tree, Properties properties) {
        super(null, properties);
        this.tree = tree;
    }

    @Override
    public void placeTree(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        if (state.get(STAGE) == 0) {
            world.setBlockState(pos, state.cycleValue(STAGE), 4);
        } else {
            this.tree.attemptGrow(world, world.getChunkProvider().getChunkGenerator(), pos, state, random);
        }
    }
}
