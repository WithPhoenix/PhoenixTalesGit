package com.phoenix.phoenixtales.origins.block.blocks.portal;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.service.RealmTeleporter;
import com.phoenix.phoenixtales.origins.world.TalesDimension;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings("deprecation")
public class OriginsPortal extends ContainerBlock {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4, 4, 4, 12, 12, 12);

    public OriginsPortal(Properties builder) {
        super(builder);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new OriginsPortalTile();
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            MinecraftServer server = worldIn.getServer();
            if (server != null) {
                if (worldIn.getDimensionKey() == TalesDimension.DIMENSION) {
                    ServerWorld serverworld = server.getWorld(World.OVERWORLD);
                    if (serverworld != null) {
                        BlockPos p = BlockPos.fromLong(player.getPersistentData().getLong(PhoenixTales.MOD_ID + "_last"));
                        player.changeDimension(serverworld, new RealmTeleporter(p, false));
                    }
                } else {
                    ServerWorld realm = server.getWorld(TalesDimension.DIMENSION);
                    if (realm != null) {
                        player.getPersistentData().putLong(PhoenixTales.MOD_ID + "_last", pos.toLong());
                        BlockPos p = new BlockPos(0, pos.getY(), 0);
                        player.changeDimension(realm, new RealmTeleporter(p, true));
                    }
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    //TODO check which portal type magic or tech
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
//        double d0 = (double)pos.getX() + rand.nextDouble();
//        double d1 = (double)pos.getY() + 0.8D;
//        double d2 = (double)pos.getZ() + rand.nextDouble();
//        //TODO: own particle type
//        worldIn.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }


    @Override
    public boolean isReplaceable(BlockState state, Fluid fluid) {
        return false;
    }
}