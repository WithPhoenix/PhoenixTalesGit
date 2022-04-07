package com.phoenix.phoenixtales.origins.world.feature.talesdim;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class HuiTreeFeature extends Feature<NoFeatureConfig> {

    private final BlockState log = OriginsBlocks.HUI_LOG.getDefaultState();
    private final BlockState leave = OriginsBlocks.HUI_LEAVES.getDefaultState();

    private final Random ran = new Random();

    public HuiTreeFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        /*
                   ###  I
                ########I
                    I   I
                    I I
                    I


         */
        BlockPos trunk = pos;
        trunk = this.findGround(reader, trunk);
        this.placeTrunk(reader, trunk);


        return true;
    }

    private BlockPos findGround(ISeedReader reader, BlockPos pos) {
        BlockPos re = pos;
        for (re = re.up(); reader.isAirBlock(re) && re.getY() > 1; re = re.down()) {
        }
        re = re.up();
        return re;
    }

    private void placeTrunk(ISeedReader reader, BlockPos posIn) {
        int height = ran.nextInt(5) + 5;
        for (int i = 0; i < height; i++) {
            reader.setBlockState(posIn, this.log, 3);
            posIn = posIn.up();
        }
    }

    private void placeTreeTop(ISeedReader reader, BlockPos posIn) {

    }
}
