package com.phoenix.phoenixtales.origins.event;

import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PhoenixTales.MOD_ID)
public class OriginsEvents {

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if (!event.getOriginal().getEntityWorld().isRemote()) {
            event.getPlayer().getPersistentData().putLong(PhoenixTales.MOD_ID + "_last", event.getOriginal().getPersistentData().getLong(PhoenixTales.MOD_ID + "_last"));
        }
    }
}
