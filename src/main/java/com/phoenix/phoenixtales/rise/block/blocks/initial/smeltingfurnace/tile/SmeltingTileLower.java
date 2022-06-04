package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.tile;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.item.RiseItems;
import net.minecraft.item.ItemStack;

public class SmeltingTileLower extends SmeltingFurnaceTile {

    public SmeltingTileLower() {
        super(RiseTileEntities.SMELTING_TILE_LOWER);
        this.items.set(0, new ItemStack(RiseItems.SLAG, 0));
        this.items.set(1, new ItemStack(RiseItems.INGOT_STEEL, 0));
    }

    public void receiveProgressDone() {
        this.items.get(0).grow(RANDOM.nextInt(2) + 1);
        if (RANDOM.nextFloat() <= 0.6f) {
            this.items.get(1).grow(2);
        } else {
            this.items.get(1).grow(1);
        }
    }
}
