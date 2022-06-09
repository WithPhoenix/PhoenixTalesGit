package com.phoenix.phoenixtales.rise.block.blocks.initial.solderingtable;

import com.phoenix.phoenixtales.rise.item.RiseItems;
import com.phoenix.phoenixtales.rise.service.RiseBlockStateProps;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class SolderingTableBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty TIN_SOLDER = RiseBlockStateProps.TIN_SOLDER;
    public static final BooleanProperty SOLDERING_IRON = RiseBlockStateProps.SOLDERING_IRON;
    private final VoxelShape SHAPE = Block.makeCuboidShape(1, 0, 1, 15, 11, 15);

    public SolderingTableBlock() {
        super(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).notSolid().sound(SoundType.WOOD));
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(TIN_SOLDER, Boolean.valueOf(false)).with(SOLDERING_IRON, Boolean.valueOf(false)));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.getTileEntity(pos) instanceof SolderingTableTile) {
            SolderingTableTile tile = (SolderingTableTile) worldIn.getTileEntity(pos);
            if (tile == null) return ActionResultType.FAIL;
            //check if there is enough tin and a soldering iron
            if (!(tile.hasRecipe() && tile.hasTinAndIron())) {
                ItemStack item = player.getHeldItem(handIn);
                if (item.getItem() == RiseItems.TIN_SOLDER) {
                    tile.getTin().grow(1);
                    worldIn.setBlockState(pos, state.with(TIN_SOLDER, Boolean.valueOf(true)));
                    if (!player.abilities.isCreativeMode) {
                        item.shrink(1);
                    }
                    return ActionResultType.SUCCESS;
                } else if (item.getItem() == RiseItems.SOLDERING_IRON) {
                    tile.setIron(item);
                    worldIn.setBlockState(pos, state.with(SOLDERING_IRON, Boolean.valueOf(true)));
                    if (!player.abilities.isCreativeMode) {
                        item.shrink(1);
                    }
                    return ActionResultType.SUCCESS;
                } else {
                    if (tile.addStack(new ItemStack(item.getItem(), 1))) {
                        if (!player.abilities.isCreativeMode) {
                            item.shrink(1);
                        }
                        return ActionResultType.SUCCESS;
                    }
                }
            } else {
                tile.increaseProgress();
                if (tile.progress() == tile.getTime()) {
                    tile.craft(worldIn, player);
                    tile.clearProgress();
                }
                return ActionResultType.PASS;
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (worldIn.getTileEntity(pos) instanceof SolderingTableTile) {
            SolderingTableTile tile = (SolderingTableTile) worldIn.getTileEntity(pos);
            if (player.isCrouching()) {
                InventoryHelper.dropItems(worldIn, pos, tile.removeAll());
            } else {
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), tile.removeStack());
            }
        }
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SolderingTableTile();
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.with(FACING, direction.rotate(state.get(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TIN_SOLDER, SOLDERING_IRON);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
