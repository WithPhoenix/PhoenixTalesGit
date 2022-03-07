package com.phoenix.phoenixtales.origins.world.gen.biome;

import com.phoenix.phoenixtales.origins.world.gen.feature.TalesConfiguredFeatures;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class TalesBiomeMaker {


    public static Biome makePlainsBiome() {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        DefaultBiomeFeatures.withNoiseTallGrass(biomegenerationsettings$builder);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_SUNFLOWER);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TalesConfiguredFeatures.ASH_PILE);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TalesConfiguredFeatures.FALLEN_TRUNK);


        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(0.1f).scale(0.1f)
                .temperature(2.0F).downfall(0.2F).setEffects((new BiomeAmbience.Builder()).setWaterColor(-3407872).setWaterFogColor(12127758)
                        .setFogColor(12127758).withSkyColor(12127758).withFoliageColor(12127758).withGrassColor(12127758).build()).withMobSpawnSettings(MobSpawnInfo.EMPTY).withGenerationSettings(biomegenerationsettings$builder.build()).build();
    }


}
