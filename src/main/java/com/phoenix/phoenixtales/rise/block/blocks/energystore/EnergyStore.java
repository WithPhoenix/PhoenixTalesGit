package com.phoenix.phoenixtales.rise.block.blocks.energystore;

import com.google.common.collect.Maps;
import com.phoenix.phoenixtales.rise.block.blocks.EnergyBaseBlock;
import com.phoenix.phoenixtales.rise.service.EnergyHandlingType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@SuppressWarnings("deprecation")
public class EnergyStore extends EnergyBaseBlock {
    public static final EnumProperty<EnergyHandlingType> NORTH = EnumProperty.create("north", EnergyHandlingType.class);
    public static final EnumProperty<EnergyHandlingType> SOUTH = EnumProperty.create("south", EnergyHandlingType.class);
    public static final EnumProperty<EnergyHandlingType> WEST = EnumProperty.create("west", EnergyHandlingType.class);
    public static final EnumProperty<EnergyHandlingType> EAST = EnumProperty.create("east", EnergyHandlingType.class);
    public static final EnumProperty<EnergyHandlingType> DOWN = EnumProperty.create("down", EnergyHandlingType.class);
    public static final EnumProperty<EnergyHandlingType> UP = EnumProperty.create("up", EnergyHandlingType.class);

    public static Direction RIGHT;
    public static Direction LEFT;
    public static Direction BACK;
    public static Direction FRONT;


    public static final Map<Direction, EnumProperty<EnergyHandlingType>> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (p) -> {
        p.put(Direction.NORTH, NORTH);
        p.put(Direction.SOUTH, SOUTH);
        p.put(Direction.WEST, WEST);
        p.put(Direction.EAST, EAST);
        p.put(Direction.DOWN, DOWN);
        p.put(Direction.UP, UP);
    });

    public static final Map<Integer, Direction> DIRECTION_TO_FACING_MAP = Util.make(Maps.newHashMap(), (p) -> {
        p.put(0, FRONT);
        p.put(1, BACK);
        p.put(2, LEFT);
        p.put(3, RIGHT);
    });

    public EnergyStore() {
        super(Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(5.0f, 5.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool().sound(SoundType.METAL));
        this.setDefaultState(this.getStateContainer().getBaseState().with(NORTH, EnergyHandlingType.NONE).with(SOUTH, EnergyHandlingType.NONE).with(WEST, EnergyHandlingType.NONE).with(EAST, EnergyHandlingType.NONE).with(DOWN, EnergyHandlingType.NONE).with(UP, EnergyHandlingType.NONE));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            EnergyStoreTile tileEntity = (EnergyStoreTile) worldIn.getTileEntity(pos);
            if (tileEntity != null) {
                if (!player.isCrouching()) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, tileEntity, tileEntity.getPos());
                } else {
                    //remove only for testing
                    tileEntity.getCapability(CapabilityEnergy.ENERGY, Direction.DOWN).ifPresent((iEnergyStorage -> iEnergyStorage.receiveEnergy(15000, false)));
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == state.getBlock()) {
            return;
        }
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof EnergyStoreTile) {
            NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
            for (int i = 0; i < items.size(); i++) {
                items.set(i, ((EnergyStoreTile) tileentity).getItemOn(i));
            }
            InventoryHelper.dropItems(worldIn, pos, items);
            worldIn.removeTileEntity(pos);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EnergyStoreTile();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(NORTH, SOUTH, WEST, EAST, DOWN, UP);
    }
}
