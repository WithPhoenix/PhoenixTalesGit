package com.phoenix.phoenixtales.origins.world.biome;

import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class OriginsBiomes {
    public static List<Biome> biomes = new ArrayList<>();

    public static Biome ASHEN_PLAINS = createBiome("ashen_plains", TalesBiomeMaker.makeAshenPlainsBiome());
    public static Biome SEARING_WOODS = createBiome("searing_woods", TalesBiomeMaker.makeSearingWoodsBiome());

    public static Biome createBiome(String id, Biome biome) {
        ResourceLocation location = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (WorldGenRegistries.BIOME.keySet().contains(location)) {
            throw new IllegalStateException("Biome, " + location.toString() + " ,already registered");
        }
        biome.setRegistryName(location);
        biomes.add(biome);
        return biome;
    }
}
