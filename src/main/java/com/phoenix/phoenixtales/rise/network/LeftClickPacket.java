package com.phoenix.phoenixtales.rise.network;

import com.phoenix.phoenixtales.rise.block.blocks.EnergyBaseTile;
import com.phoenix.phoenixtales.rise.service.enums.EnergyHandlingType;
import com.phoenix.phoenixtales.rise.service.enums.RelativeDirection;
import net.minecraft.block.BlockState;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class LeftClickPacket {
    public EnergyHandlingType type;
    public RelativeDirection direction;
    public BlockPos pos;

    public LeftClickPacket() {
    }

    public LeftClickPacket(EnergyHandlingType type, RelativeDirection direction, BlockPos pos) {
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
        return new LeftClickPacket(buf.readEnumValue(EnergyHandlingType.class), buf.readEnumValue(RelativeDirection.class), buf.readBlockPos());
    }

    public static void handle(LeftClickPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isServer()) {
            ctx.get().enqueueWork(() -> {
                ServerWorld world = ctx.get().getSender().getServerWorld();
                BlockState state = world.getBlockState(msg.pos);
                EnergyBaseTile tile = (EnergyBaseTile) world.getTileEntity(msg.pos);
                tile.CONFIG.set(msg.direction, msg.type);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
