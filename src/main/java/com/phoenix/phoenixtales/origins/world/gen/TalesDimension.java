package com.phoenix.phoenixtales.origins.world.gen;

import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class TalesDimension {

    //TODO make custom sky

    public static final RegistryKey<DimensionType> DIMENSION_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, new ResourceLocation(PhoenixTales.MOD_ID, "burning_realm"));
    public static final RegistryKey<World> DIMENSION = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(PhoenixTales.MOD_ID, "burning_realm"));

}