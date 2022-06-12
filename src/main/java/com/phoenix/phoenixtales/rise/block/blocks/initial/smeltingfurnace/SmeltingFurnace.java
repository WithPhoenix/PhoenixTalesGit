package com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace;

import com.phoenix.phoenixtales.rise.block.blocks.initial.smeltingfurnace.tile.SmeltingFurnaceTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.function.ToIntFunction;

@SuppressWarnings("deprecation")
public abstract class SmeltingFurnace extends Block {

    public SmeltingFurnace() {
        super(Properties.create(Material.ROCK, MaterialColor.ADOBE).setRequiresTool().notSolid().hardnessAndResistance(1.25F, 4.2F).setLightLevel(getLightValueLit(13)));
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof SmeltingFurnaceTile) {
            InventoryHelper.dropItems(worldIn, pos, ((SmeltingFurnaceTile) tileentity).getItems());
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosionIn) {
        return false;
    }

    public static boolean isBuild(BlockState state, BlockPos pos, World world) {
        if (state.getBlock() instanceof SmeltingFurnaceBottom) {
            BlockState top = world.getBlockState(pos.up());
            return top.getBlock() instanceof SmeltingFurnaceTop;
        } else if (state.getBlock() instanceof SmeltingFurnaceTop) {
            BlockState bot = world.getBlockState(pos.down());
            return bot.getBlock() instanceof SmeltingFurnaceBottom;
        }
        return false;
    }

    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> {
            return state.get(BlockStateProperties.LIT) ? lightValue : 0;
        };
    }
}
