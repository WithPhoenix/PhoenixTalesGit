package com.phoenix.phoenixtales.origins.block.blocks.portal;

import com.phoenix.phoenixtales.origins.block.OriginsTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OriginsPortalTile extends TileEntity {


    //TODO if true only usable in the magical portal if false use tech variant
    private Boolean isMagic;

    public OriginsPortalTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public OriginsPortalTile() {
        this(OriginsTileEntities.PORTAL_TILE);
        this.isMagic = null;
    }


    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.isMagic = nbt.getBoolean("portal_type");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("portal_type", isMagic);
        return super.write(compound);
    }

    public void setMagic(boolean flag) {
        this.isMagic = flag;
    }


    @Override
    public double getMaxRenderDistanceSquared() {
        return 128.0D;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderFace(Direction face) {
        return Block.shouldSideBeRendered(this.getBlockState(), this.world, this.getPos(), face);
    }

}