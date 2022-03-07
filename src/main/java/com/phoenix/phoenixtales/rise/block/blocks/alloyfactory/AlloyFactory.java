package com.phoenix.phoenixtales.rise.block.blocks.alloyfactory;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class AlloyFactory extends Block {
    public AlloyFactory() {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(5.0f, 5.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool().sound(SoundType.METAL));

    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
//            if (Helpers.isTechnician(player)) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if (tileEntity instanceof AlloyTile) {
                INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos, (AlloyTile) tileEntity);
                NetworkHooks.openGui(((ServerPlayerEntity) player), containerProvider, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Container provider is missing!");
            }
        }
//        }
        return ActionResultType.SUCCESS;
    }


    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos, AlloyTile tile) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.phoenixtales.alloy");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new AlloyContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }

        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof AlloyTile) {
            NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
            for (int i = 0; i < items.size(); i++) {
                items.set(i, ((AlloyTile) tileentity).getItemOn(i));
            }
            InventoryHelper.dropItems(worldIn, pos, items);
        }

        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RiseTileEntities.ALLOY_TILE.create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
