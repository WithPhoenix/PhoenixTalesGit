package com.phoenix.phoenixtales.origins.world;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.world.feature.ov.OresOverworld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PhoenixTales.MOD_ID)
public class PTWorldEvents {

    @SubscribeEvent
    public static void onBiomeLoading(final BiomeLoadingEvent e) {
        OresOverworld.generate(e);
    }
}
