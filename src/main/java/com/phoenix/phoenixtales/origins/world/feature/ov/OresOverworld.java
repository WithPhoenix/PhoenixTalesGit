package com.phoenix.phoenixtales.origins.world.feature.ov;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OresOverworld {
    public static void generate(final BiomeLoadingEvent e) {
        spawnOreInOverworldInAllBiomes(Ores.OVERWORLD_NICKEL, e);
        spawnOreInOverworldInAllBiomes(Ores.OVERWORLD_VANADIUM, e);
        spawnOreInOverworldInAllBiomes(Ores.OVERWORLD_APATITE, e);
        spawnOreInOverworldInAllBiomes(Ores.OVERWORLD_KERNITE, e);
        spawnOreInOverworldInAllBiomes(Ores.OVERWORLD_TIN, e);
        spawnOreInOverworldInAllBiomes(Ores.OVERWORLD_COPPER, e);
    }

    private static void spawnOreInOverworldInAllBiomes(Ores ore, final BiomeLoadingEvent event) {
        OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, ore.getBlock().getDefaultState(), ore.getMaxVeinSize());
        ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configure(new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));
        ConfiguredFeature<?, ?> oreFeature = createOreFeature(ore, oreFeatureConfig, configuredPlacement);
        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
    }

    private static ConfiguredFeature<?, ?> createOreFeature(Ores ore, OreFeatureConfig oreFeatureConfig, ConfiguredPlacement configuredPlacement) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, ore.getBlock().getRegistryName(), Feature.ORE.withConfiguration(oreFeatureConfig).withPlacement(configuredPlacement).square().count(ore.getVeinsPerChunk()));
    }
}
