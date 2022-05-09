package com.phoenix.phoenixtales.rise.block.blocks.assembler;

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
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class Assembler extends Block {

    //TODO: type parameter um zwischen den verschiedenen versionen unterscheiden. durch verschiedene blockItems sollte dies möglich sein, wenn die nicht funktioniert diese class abstract machen und für die typen zuschneiden (BESSER!!!!)
//TODO: mit enum die verschiedenen tiers/ typen machen


    //TODO animate item when crafting and on the end, move the top part down and up to finish the item
    public Assembler() {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(5.0f, 5.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool().sound(SoundType.METAL));
    }

    //TODO some cubes are missing
    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(1, 11, 15, 15, 13.5, 15.5),
            Block.makeCuboidShape(15, 11, 1, 15.5, 13.5, 15),
            Block.makeCuboidShape(1, 11.5, 1, 15, 13, 15),
            Block.makeCuboidShape(1, 11, 0.5, 15, 13.5, 1),
            Block.makeCuboidShape(0.5, 11, 1, 1, 13.5, 15),
            Block.makeCuboidShape(1, 11.3, 7, 15, 11.5, 9),
            Block.makeCuboidShape(7, 11.3, 1, 9, 11.5, 7),
            Block.makeCuboidShape(7, 11.3, 9, 9, 11.5, 15),
            Block.makeCuboidShape(1, 1.5, 1, 15, 5.5, 15),
            Block.makeCuboidShape(15, 2, 1, 15.5, 7, 15),
            Block.makeCuboidShape(0.5, 2, 1, 1, 7, 15),
            Block.makeCuboidShape(1, 2, 15, 15, 7, 15.5),
            Block.makeCuboidShape(0, 0, 0, 1, 16, 1),
            Block.makeCuboidShape(1, 2, 0.5, 15, 7, 1),
            Block.makeCuboidShape(15, 0, 0, 16, 16, 1),
            Block.makeCuboidShape(15, 0, 15, 16, 16, 16),
            Block.makeCuboidShape(0, 0, 15, 1, 16, 16),
            Block.makeCuboidShape(1, 5.5, 7, 15, 6.5, 9),
            Block.makeCuboidShape(7, 5.5, 9, 9, 6.5, 15),
            Block.makeCuboidShape(7, 5.5, 1, 9, 6.5, 7),
            Block.makeCuboidShape(1, 7, 1, 15, 11, 15)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            //TODO only technicians
//            if (Helpers.isTechnician(player)) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (!player.isCrouching()) {

                if (tileEntity instanceof AssemblerTile) {
                    NetworkHooks.openGui(((ServerPlayerEntity) player), (INamedContainerProvider) tileEntity, tileEntity.getPos());
                } else {
                    throw new IllegalStateException("Missing Container Provider");
                }
                return ActionResultType.SUCCESS;
            } else {
                if (tileEntity instanceof AssemblerTile) {
                    //remove only for testing
                    ((AssemblerTile) tileEntity).addEnergy(500);
//                    ((PressTile) tileEntity).receiveEnergy(1000, false);
                }
                return ActionResultType.CONSUME;
            }
//        } else {
//            player.sendMessage(new StringTextComponent("You are not a Technician"), player.getUniqueID());
//        }
        }
        return ActionResultType.PASS;
    }


    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof AssemblerTile) {
            NonNullList<ItemStack> items = NonNullList.withSize(6, ItemStack.EMPTY);
            for (int i = 0; i < items.size(); i++) {
                items.set(i, ((AssemblerTile) tileentity).getItemOn(i));
            }
            InventoryHelper.dropItems(worldIn, pos, items);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RiseTileEntities.ASSEMBLER_TILE.create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
