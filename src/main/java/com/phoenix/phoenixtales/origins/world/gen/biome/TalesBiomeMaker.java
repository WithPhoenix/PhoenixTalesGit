package com.phoenix.phoenixtales.origins.world.gen.biome;

import com.phoenix.phoenixtales.origins.world.gen.feature.TalesConfiguredFeatures;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class TalesBiomeMaker {

    //TODO maybe make java class for each biome, not here
    public static Biome makeAshenPlainsBiome() {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, TalesConfiguredFeatures.SURFACE_MODIFICATION);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, TalesConfiguredFeatures.ROCKS);
        DefaultBiomeFeatures.withNoiseTallGrass(biomegenerationsettings$builder);


        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(0.1f).scale(0.1f).temperature(2.0F).downfall(0.2F).setEffects((new BiomeAmbience.Builder()).setWaterColor(/*MathHelper.rgb(74,117,114)*/4879730).setWaterFogColor(4879730).setFogColor(/*MathHelper.rgb(117,91,74)*/7691082).withSkyColor(/*MathHelper.rgb(117,81,74)*/7688522).withFoliageColor(/*MathHelper.rgb(122,105,77)*/8022349).withGrassColor(8022349).build()).withMobSpawnSettings(MobSpawnInfo.EMPTY).withGenerationSettings(biomegenerationsettings$builder.build()).build();
    }
    
    public static Biome makeSearingWoodsBiome() {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, TalesConfiguredFeatures.SURFACE_MODIFICATION);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TalesConfiguredFeatures.TREES_SEARING_WOODS);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, TalesConfiguredFeatures.ROCKS);
        DefaultBiomeFeatures.withNoiseTallGrass(biomegenerationsettings$builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(0.1f).scale(0.1f).temperature(2.0F).downfall(0.2F).setEffects((new BiomeAmbience.Builder()).setWaterColor(/*MathHelper.rgb(74,117,114)*/4879730).setWaterFogColor(4879730).setFogColor(/*MathHelper.rgb(117,91,74)*/7691082).withSkyColor(/*MathHelper.rgb(117,81,74)*/7688522).withFoliageColor(/*MathHelper.rgb(122,105,77)*/8022349).withGrassColor(8022349).build()).withMobSpawnSettings(MobSpawnInfo.EMPTY).withGenerationSettings(biomegenerationsettings$builder.build()).build();

    }


}
