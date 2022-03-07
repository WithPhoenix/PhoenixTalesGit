package com.phoenix.phoenixtales.rise.block.blocks.tank;

import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class TankBlock extends Block {
    public TankBlock() {
        super(Properties.create(Material.IRON, MaterialColor.LIGHT_GRAY).hardnessAndResistance(2.5f, 2.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool().sound(SoundType.METAL));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            if (!player.isSneaking()) {
                TileEntity te = worldIn.getTileEntity(pos);

                if (te instanceof TankTile) {
                    Item stack = player.getHeldItem(handIn).getItem();





//                    if (stack.equals(Items.WATER_BUCKET)) {
//                        if (((TankTile) te).getFluidS().equals(Fluids.WATER.toString())) {
//                            ((TankTile) te).addMB(1000);
//                            player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
//                        } else if (((TankTile) te).getFluidS().equals("")) {
//                            ((TankTile) te).setFluidS(Fluids.WATER.toString());
//                            ((TankTile) te).setMB(1000);
//                            player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
//                        }
//                    } else if (stack.equals(Items.LAVA_BUCKET)) {
//                        if (((TankTile) te).getFluidS().equals(Fluids.LAVA.toString())) {
//                            ((TankTile) te).addMB(1000);
//                            player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
//                        } else if (((TankTile) te).getFluidS().equals("")) {
//                            ((TankTile) te).setFluidS(Fluids.LAVA.toString());
//                            ((TankTile) te).setMB(1000);
//                            player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
//                        }
//                    } else if (stack.equals(Items.BUCKET)) {
//                        if (((TankTile) te).getFluidS().equals(Fluids.WATER.toString())) {
//                            ((TankTile) te).removeMB(1000);
//                            player.setHeldItem(handIn, Items.WATER_BUCKET.getDefaultInstance());
//                        } else if (((TankTile) te).getFluidS().equals(Fluids.LAVA.toString())) {
//                            ((TankTile) te).removeMB(1000);
//                            player.setHeldItem(handIn, Items.LAVA_BUCKET.getDefaultInstance());
//                        }
//                    }
//                    if (((TankTile) te).getMB() == 0) {
//                        ((TankTile) te).setFluidS("");
//                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RiseTileEntities.TANK_TILE.create();
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
