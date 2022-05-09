package com.phoenix.phoenixtales.origins.service;

import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import com.phoenix.phoenixtales.origins.block.blocks.portal.OriginsPortal;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class RealmTeleporter implements ITeleporter {
    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean intoRealm = true;

    public RealmTeleporter(BlockPos posIn, boolean isIntoRealm) {
        thisPos = posIn;
        intoRealm = isIntoRealm;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        double y = 61;
        if (!intoRealm) {
            y = thisPos.getY();
        }
        BlockPos destPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());
        int tries = 0;
        while ((destWorld.getBlockState(destPos).getMaterial() != Material.AIR && !destWorld.getBlockState(destPos).isReplaceable(Fluids.WATER)) && (destWorld.getBlockState(destPos.up()).getMaterial() != Material.AIR && !destWorld.getBlockState(destPos.up()).isReplaceable(Fluids.WATER)) && tries < 25) {
            destPos = destPos.up(2);
            tries++;
        }
        entity.setPositionAndUpdate(destPos.getX(), destPos.getY(), destPos.getZ());
        if (intoRealm) {
            boolean setBlock = true;
            for (BlockPos checkPos : BlockPos.getAllInBoxMutable(destPos.down(10).west(10), destPos.up(10).east(10))) {
                if (destWorld.getBlockState(checkPos).getBlock() instanceof OriginsPortal) {
                    setBlock = false;
                    break;
                }
            }
            if (setBlock) {
                destWorld.setBlockState(destPos, OriginsBlocks.PORTAL.getDefaultState());
            }
        }
        return entity;
    }
}
