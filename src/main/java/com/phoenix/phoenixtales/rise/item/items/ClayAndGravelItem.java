package com.phoenix.phoenixtales.rise.item.items;

import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.SmeltingFurnaceBottom;
import com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.SmeltingFurnaceTop;
import com.phoenix.phoenixtales.rise.item.RiseBlockItem;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ClayAndGravelItem extends RiseBlockItem {
    public ClayAndGravelItem(Properties properties) {
        super(RiseBlocks.SMELTING_FURNACE_BOTTOM, properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof SmeltingFurnaceBottom) {
            if (!state.get(SmeltingFurnaceBottom.BUILD)) {
                world.setBlockState(pos, state.with(SmeltingFurnaceBottom.BUILD, true));
                context.getPlayer().getHeldItem(context.getHand()).shrink(1);
            } else {
                if (!(world.getBlockState(pos.up()).getBlock() instanceof SmeltingFurnaceTop)) {
                    world.setBlockState(pos.up(), RiseBlocks.SMELTING_FURNACE_TOP.getDefaultState());
                    context.getPlayer().getHeldItem(context.getHand()).shrink(1);
                }
            }
            return ActionResultType.SUCCESS;
        } else {
            return super.onItemUse(context);
        }
    }

    @Override
    public String getTranslationKey() {
        return this.getDefaultTranslationKey();
    }
}
