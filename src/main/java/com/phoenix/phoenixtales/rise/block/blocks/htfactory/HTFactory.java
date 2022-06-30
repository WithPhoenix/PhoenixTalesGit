package com.phoenix.phoenixtales.rise.block.blocks.htfactory;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class HTFactory extends Block {
    public HTFactory() {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(5.0f, 5.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool().sound(SoundType.METAL));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (!player.isCrouching()) {
//            if (Helpers.isTechnician(player)) {
                if (tileEntity instanceof HTFactoryTile) {
                    NetworkHooks.openGui(((ServerPlayerEntity) player), (INamedContainerProvider) tileEntity, tileEntity.getPos());
                } else {
                    throw new IllegalStateException("Container provider is missing");
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }

        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof HTFactoryTile) {
           InventoryHelper.dropItems(worldIn, pos, ((HTFactoryTile) tileentity).getItems());
        }

        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RiseTileEntities.HT_TILE.create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
