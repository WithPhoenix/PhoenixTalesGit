package com.phoenix.phoenixtales.origins.world.feature.placer;

import com.mojang.serialization.Codec;
import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockplacer.BlockPlacer;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class TalesPlacerType<P extends BlockPlacer> extends BlockPlacerType<P> {
    public static List<BlockPlacerType<?>> placertypes = new ArrayList<>();

    public static final TalesPlacerType<TalesDoublePlantBlockPlacer> DOUBLE_PLANT = register("double_plant", new TalesPlacerType<>(TalesDoublePlantBlockPlacer.CODEC));
    private final Codec<P> codec;

    public TalesPlacerType(Codec<P> codec) {
        super(codec);
        this.codec = codec;
    }

    public static <P extends BlockPlacer, BP extends BlockPlacerType<P>> BP register(String id, BP placertype) {
        ResourceLocation location = new ResourceLocation(PhoenixTales.MOD_ID, id);
        if (Registry.SURFACE_BUILDER.keySet().contains(location)) {
            throw new IllegalStateException("Surface Builder " + location.toString() + "\" already registered");
        }

        placertype.setRegistryName(location);
        placertypes.add(placertype);
        return placertype;
    }


    public Codec<P> getCodec() {
        return this.codec;
    }
}
