package com.phoenix.phoenixtales.rise.block.blocks.press;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.item.RiseItems;
import com.phoenix.phoenixtales.rise.item.items.EnergyHolder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
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
public class PressFactory extends HorizontalBlock {

    public PressFactory() {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(5.0f, 5.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool().sound(SoundType.METAL));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (!player.isCrouching()) {
//            if (Helpers.isTechnician(player)) {
                if (tileEntity instanceof PressTile) {
                    NetworkHooks.openGui(((ServerPlayerEntity) player), (INamedContainerProvider) tileEntity, tileEntity.getPos());
                } else {
                    throw new IllegalStateException("Container provider is missing");
                }
                return ActionResultType.SUCCESS;
            } else {

                if (tileEntity instanceof PressTile) {
                    ((PressTile) tileEntity).addEnergy(500);
                        //remove only for testing
//                    ((PressTile) tileEntity).receiveEnergy(1000, false);
                }
                return ActionResultType.CONSUME;
            }
//        }

        }
        return ActionResultType.PASS;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }

        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof PressTile) {
            NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
            for (int i = 0; i < items.size(); i++) {
                items.set(i, ((PressTile) tileentity).getItemOn(i));
            }
            InventoryHelper.dropItems(worldIn, pos, items);
        }

        super.onReplaced(state, worldIn, pos, newState, isMoving);

    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RiseTileEntities.PRESS_TILE.create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}