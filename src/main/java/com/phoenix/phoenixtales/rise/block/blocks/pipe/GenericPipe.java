package com.phoenix.phoenixtales.rise.block.blocks.pipe;

import com.phoenix.phoenixtales.rise.block.blocks.ConduitBlock;
import com.phoenix.phoenixtales.rise.service.enums.TechnologyType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class GenericPipe extends ConduitBlock {
    private final TechnologyType type;

    public GenericPipe(TechnologyType type) {
        super( Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(2.0f).sound(SoundType.METAL));
        this.type = type;
    }


    @Override
    protected boolean connectsTo(IWorldReader world, BlockPos pos, Direction facing) {
        TileEntity tile = world.getTileEntity(pos.offset(facing));
        return tile != null && tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing.getOpposite()).isPresent();
    }

    @Override
    protected boolean isConduit(IWorldReader world, BlockPos pos, Direction facing) {
        Block block = world.getBlockState(pos.offset(facing)).getBlock();
        return block instanceof GenericPipe;
    }
}
