package com.phoenix.phoenixtales.fall.block;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.fall.block.alembic.Alembic;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class FallBlocks {

    public static List<Block> blocks = new ArrayList<>();

    public static final Block ALEMBIC = createBlock("alembic", new Alembic());

    private static Block createBlock(String id, Block block) {
        block.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        blocks.add(block);
        return block;
    }

}
