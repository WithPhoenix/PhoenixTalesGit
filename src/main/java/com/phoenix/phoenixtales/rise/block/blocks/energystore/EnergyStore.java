package com.phoenix.phoenixtales.rise.block.blocks.energystore;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
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
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class EnergyStore extends Block {
    public EnergyStore() {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(5.0f, 5.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool().sound(SoundType.METAL));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if (!player.isCrouching()) {
                if (tileEntity instanceof EnergyStoreTile) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
                    return ActionResultType.SUCCESS;
                } else {
                    throw new IllegalStateException("Missing Container Provider");
                }
            } else {
                if (tileEntity instanceof EnergyStoreTile) {
                    //remove only for testing
                    ((EnergyStoreTile) tileEntity).addEnergy(1000);
//                    ((PressTile) tileEntity).receiveEnergy(1000, false);
                }
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof EnergyStoreTile) {
            NonNullList<ItemStack> items = NonNullList.withSize(6, ItemStack.EMPTY);
            for (int i = 0; i < items.size(); i++) {
                items.set(i, ((EnergyStoreTile) tileentity).getItemOn(i));
            }
            InventoryHelper.dropItems(worldIn, pos, items);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RiseTileEntities.ENERGY_STORE_TILE.create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
