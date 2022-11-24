package com.phoenix.phoenixtales.rise.network;

import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStore;
import com.phoenix.phoenixtales.rise.service.enums.EnergyHandlingType;
import net.minecraft.block.BlockState;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class LeftClickPacket {
    public EnergyHandlingType type;
    public Direction direction;
    public BlockPos pos;

    public LeftClickPacket() {
    }

    public LeftClickPacket(EnergyHandlingType type, Direction direction, BlockPos pos) {
        this.type = type;
        this.direction = direction;
        this.pos = pos;
    }

    public static void encode(LeftClickPacket msg, PacketBuffer buffer) {
        buffer.writeEnumValue(msg.type);
        buffer.writeEnumValue(msg.direction);
        buffer.writeBlockPos(msg.pos);
    }

    public static LeftClickPacket decode(PacketBuffer buf) {
        return new LeftClickPacket(buf.readEnumValue(EnergyHandlingType.class), buf.readEnumValue(Direction.class), buf.readBlockPos());
    }

    public static void handle(LeftClickPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            ctx.get().enqueueWork(() -> {
                ServerWorld world = ctx.get().getSender().getServerWorld();
                BlockState state = world.getBlockState(msg.pos);
                switch (msg.type) {
                    case NONE:
                        world.setBlockState(msg.pos, state.with(EnergyStore.FACING_TO_PROPERTY_MAP.get(msg.direction), EnergyHandlingType.NONE));
                        break;
                    case INPUT:
                        world.setBlockState(msg.pos, state.with(EnergyStore.FACING_TO_PROPERTY_MAP.get(msg.direction), EnergyHandlingType.INPUT));
                        break;
                    case OUTPUT:
                        world.setBlockState(msg.pos, state.with(EnergyStore.FACING_TO_PROPERTY_MAP.get(msg.direction), EnergyHandlingType.OUTPUT));
                        break;
                }
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
