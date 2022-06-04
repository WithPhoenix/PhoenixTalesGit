package com.phoenix.phoenixtales.origins.world.feature.ov;

import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import net.minecraft.block.Block;

public enum Ores {
    OVERWORLD_NICKEL(RiseBlocks.ORE_NICKEL, 4, 10, 65, 4),
    OVERWORLD_VANADIUM(RiseBlocks.ORE_VANADIUM, 4, 5, 60, 4),
    OVERWORLD_KERNITE(RiseBlocks.ORE_KERNITE, 3, 40, 90, 3),
    OVERWORLD_APATITE(RiseBlocks.ORE_APATITE, 3, 40, 90, 3),
    OVERWORLD_TIN(RiseBlocks.ORE_TIN, 4, 0, 80, 4),
    OVERWORLD_COPPER(RiseBlocks.ORE_COPPER, 4, 10, 90, 4);

    private final Block block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;
    private final int veinsPerChunk;

    Ores(Block block, int maxVeinSize, int minHeight, int maxHeight, int veinsPerChunk) {
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinsPerChunk = veinsPerChunk;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getVeinsPerChunk() {
        return veinsPerChunk;
    }

    public Block getBlock() {
        return block;
    }
}
