package com.phoenix.phoenixtales.rise.network;

import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

    private static final String VERSION = "0.0.1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(PhoenixTales.MOD_ID, "network"),
            () -> VERSION,
            VERSION::equals,
            VERSION::equals
    );

    public static void init() {
        int id = -1;
        CHANNEL.registerMessage(++id, LeftClickPacket.class, LeftClickPacket::encode, LeftClickPacket::decode, LeftClickPacket::handle);
    }
}
