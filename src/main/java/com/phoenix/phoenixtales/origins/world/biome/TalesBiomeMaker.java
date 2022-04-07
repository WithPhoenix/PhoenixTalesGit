package com.phoenix.phoenixtales.origins.world.biome;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.world.feature.TalesConfiguredFeatures;
import com.phoenix.phoenixtales.origins.world.surfacebuilder.TalesSurfaceBuilders;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;

public class TalesBiomeMaker {

    //TODO a extremely rare perma frost biome with screen modifications

    //coom means ash on brit. reg

    /**
     * Sets a block state into this world.Flags are as follows:
     * 1 will cause a block update.
     * 2 will send the change to clients.
     * 4 will prevent the block from being re-rendered.
     * 8 will force any re-renders to run on the main thread instead
     * 16 will prevent neighbor reactions (e.g. fences connecting, observers pulsing).
     * 32 will prevent neighbor reactions from spawning drops.
     * 64 will signify the block is being moved.
     * Flags can be OR-ed
     */

    //TODO maybe make java class for each biome, not here
    public static Biome makeAshenPlainsBiome() {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.ASHEN_PLAINS_SURFACE);

        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, TalesConfiguredFeatures.STONY_SURFACE);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, TalesConfiguredFeatures.ROCKS);
        DefaultBiomeFeatures.withNoiseTallGrass(biomegenerationsettings$builder);


        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(0.1f).scale(0.1f).temperature(2.0F).downfall(0.2F).setEffects((new BiomeAmbience.Builder()).setWaterColor(/*MathHelper.rgb(74,117,114)*/4879730).setWaterFogColor(4879730).setFogColor(/*MathHelper.rgb(117,91,74)*/7691082).withSkyColor(/*MathHelper.rgb(117,81,74)*/7688522).withFoliageColor(/*MathHelper.rgb(122,105,77)*/8022349).withGrassColor(8022349).build()).withMobSpawnSettings(MobSpawnInfo.EMPTY).withGenerationSettings(biomegenerationsettings$builder.build()).build();
    }

    public static Biome makeAshenForest() {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.ASHEN_PLAINS_SURFACE);

        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, TalesConfiguredFeatures.STONY_SURFACE);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, TalesConfiguredFeatures.ROCKS);
        DefaultBiomeFeatures.withNoiseTallGrass(biomegenerationsettings$builder);


        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.FOREST).depth(0.1f).scale(0.1f).temperature(2.0F).downfall(0.2F).setEffects((new BiomeAmbience.Builder()).setWaterColor(/*MathHelper.rgb(74,117,114)*/4879730).setWaterFogColor(4879730).setFogColor(/*MathHelper.rgb(117,91,74)*/7691082).withSkyColor(/*MathHelper.rgb(117,81,74)*/7688522).withFoliageColor(/*MathHelper.rgb(122,105,77)*/8022349).withGrassColor(8022349).build()).withMobSpawnSettings(MobSpawnInfo.EMPTY).withGenerationSettings(biomegenerationsettings$builder.build()).build();
    }

    public static Biome makeSearingWoodsBiome() {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders.GRASS);

        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, TalesConfiguredFeatures.STONY_SURFACE);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, TalesConfiguredFeatures.TREES_SEARING_WOODS);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, TalesConfiguredFeatures.ROCKS);
        DefaultBiomeFeatures.withNoiseTallGrass(biomegenerationsettings$builder);

        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.FOREST).depth(0.1f).scale(0.1f).temperature(2.0F).downfall(0.2F).setEffects((new BiomeAmbience.Builder()).setWaterColor(/*MathHelper.rgb(74,117,114)*/4879730).setWaterFogColor(4879730).setFogColor(/*MathHelper.rgb(117,91,74)*/7691082).withSkyColor(/*MathHelper.rgb(117,81,74)*/7688522).withFoliageColor(/*MathHelper.rgb(122,105,77)*/8022349).withGrassColor(8022349).build()).withMobSpawnSettings(MobSpawnInfo.EMPTY).withGenerationSettings(biomegenerationsettings$builder.build()).build();

    }

    public static Biome makePermaFrost() {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ConfiguredSurfaceBuilders.ASHEN_PLAINS_SURFACE);

        return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.ICY).depth(0.1f).scale(0.5f).temperature(0.0F).downfall(0.2F).setEffects((new BiomeAmbience.Builder()).setWaterColor(/*MathHelper.rgb(74,117,114)*/4879730).setWaterFogColor(4879730).setFogColor(/*MathHelper.rgb(117,91,74)*/7691082).withSkyColor(/*MathHelper.rgb(117,81,74)*/7688522).withFoliageColor(/*MathHelper.rgb(122,105,77)*/8022349).withGrassColor(8022349).build()).withMobSpawnSettings(MobSpawnInfo.EMPTY).withGenerationSettings(biomegenerationsettings$builder.build()).build();
    }

    public static class ConfiguredSurfaceBuilders {
        public static final ConfiguredSurfaceBuilder<?> ASHEN_PLAINS_SURFACE = createConfiguredSurfaceBuilder("ashen_plains_csb", new ConfiguredSurfaceBuilder<>(TalesSurfaceBuilders.ASHEN_PLAINS_SURFACE_BUILDER, TalesSurfaceBuilders.Configs.ASHEN_PLAINS));

        public static <SC extends ISurfaceBuilderConfig, CSB extends ConfiguredSurfaceBuilder<SC>> CSB createConfiguredSurfaceBuilder(String id, CSB configuredSurfaceBuilder) {
            ResourceLocation location = new ResourceLocation(PhoenixTales.MOD_ID, id);
            if (WorldGenRegistries.CONFIGURED_SURFACE_BUILDER.keySet().contains(location)) {
                throw new IllegalStateException(location.toString() + "already registered");
            }
            Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, location, configuredSurfaceBuilder);
            return configuredSurfaceBuilder;
        }
    }


}
