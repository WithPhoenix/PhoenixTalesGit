package com.phoenix.phoenixtales.origins.world.feature.realm;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class SurfaceFeature extends Feature<NoFeatureConfig> {
    private final BlockState stone = OriginsBlocks.REALMSTONE.getDefaultState();

    public SurfaceFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    public boolean isValidPosition(ISeedReader world, BlockPos pos) {
        return !(world.getBlockState(pos).matchesBlock(OriginsBlocks.HUI_LEAVES) || world.getBlockState(pos).matchesBlock(OriginsBlocks.HUI_LOG) || world.getBlockState(pos).matchesBlock(OriginsBlocks.HUO_LEAVES) || world.getBlockState(pos).matchesBlock(OriginsBlocks.HUO_LOG));
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockPos pos1 = pos;
        for (pos1 = pos1.up(); reader.isAirBlock(pos1) && pos1.getY() > 1; pos1 = pos1.down()) {
        }


        reader.setBlockState(pos1, this.stone, 3);
        Random r = new Random();
        //north
        BlockPos pos2 = pos1;
        int count = r.nextInt(5) + 1;
        for (int i = 0; i < count; i++) {
            pos2 = pos2.north();
            pos2 = goDown(reader, pos2);
            this.setStone(reader, pos2);
            int amount = r.nextInt(6) + 1;
            for (int j = 0; j < amount; j++) {
                BlockPos posA = pos2.west();
                posA = goDown(reader, posA);
                this.setStone(reader, posA);
            }
            amount = r.nextInt(6) + 1;
            for (int k = 0; k < amount; k++) {
                BlockPos posB = pos2.east();
                posB = goDown(reader, posB);
                this.setStone(reader, posB);
            }
        }
        //east
        BlockPos pos3 = pos1;
        count = r.nextInt(5) + 1;
        for (int i = 0; i < count; i++) {
            pos3 = pos3.east();
            pos3 = goDown(reader, pos3);
            this.setStone(reader, pos3);
            int amount = r.nextInt(6) + 1;
            for (int j = 0; j < amount; j++) {
                BlockPos posA = pos3.south();
                posA = goDown(reader, posA);
                this.setStone(reader, posA);
            }
            amount = r.nextInt(6) + 1;
            for (int k = 0; k < amount; k++) {
                BlockPos posB = pos3.north();
                posB = goDown(reader, posB);
                this.setStone(reader, posB);
            }
        }
        //south
        BlockPos pos4 = pos1;
        count = r.nextInt(5) + 1;
        for (int i = 0; i < count; i++) {
            pos4 = pos4.south();
            pos4 = goDown(reader, pos4);
            this.setStone(reader, pos4);
            int amount = r.nextInt(6) + 1;
            for (int j = 0; j < amount; j++) {
                BlockPos posA = pos4.west();
                posA = goDown(reader, posA);
                this.setStone(reader, posA);
            }
            amount = r.nextInt(6) + 1;
            for (int k = 0; k < amount; k++) {
                BlockPos posB = pos4.east();
                posB = goDown(reader, posB);
                this.setStone(reader, posB);
            }
        }
        //west
        BlockPos pos5 = pos1;
        count = r.nextInt(5) + 1;
        for (int i = 0; i < count; i++) {
            pos5 = pos5.west();
            pos5 = goDown(reader, pos5);
            this.setStone(reader, pos5);
            int amount = r.nextInt(6) + 1;
            for (int j = 0; j < amount; j++) {
                BlockPos posA = pos5.south();
                posA = goDown(reader, posA);
                this.setStone(reader, posA);
            }
            amount = r.nextInt(6) + 1;
            for (int k = 0; k < amount; k++) {
                BlockPos posB = pos5.north();
                posB = goDown(reader, posB);
                this.setStone(reader, posB);
            }
        }
        return true;
    }

    private void setStone(ISeedReader reader, BlockPos pos) {
        if (this.isValidPosition(reader, pos)) {
            reader.setBlockState(pos, this.stone, 3);
        }
    }

    private BlockPos goDown(ISeedReader reader, BlockPos pos) {
        BlockPos re = pos;
        for (re = re.up(); reader.isAirBlock(re) && re.getY() > 1; re = re.down()) {
        }
        return re;
    }
}
