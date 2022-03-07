package com.phoenix.phoenixtales.origins.block.blocks.decoportal;

import net.minecraft.block.ContainerBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class DecoPortal extends ContainerBlock {

//    public static final BooleanProperty ACTIVE = CoreBlockStateProperties.ACTIVE;

    public DecoPortal(Properties properties) {
        super(properties);
//        this.setDefaultState(this.getDefaultState().with(ACTIVE, Boolean.valueOf(false)));
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new DecoPortalTile();
    }


//    @Override
//    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
//        if (stateIn.get(ACTIVE)) {
//            float chance = 0.35f;
//            if (chance < rand.nextFloat()) {
//                worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + rand.nextDouble(),
//                        pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
//                        0d, 0.05d, 0d);
//
//                worldIn.addParticle(new BlockParticleData(ParticleTypes.BLOCK, stateIn), pos.getX() + rand.nextDouble(),
//                        pos.getY() + 0.5D, pos.getZ() + rand.nextDouble(),
//                        0.0D, 0.05D, 0.0D);
//            }
//        }
//
//        super.animateTick(stateIn, worldIn, pos, rand);
//    }
}
