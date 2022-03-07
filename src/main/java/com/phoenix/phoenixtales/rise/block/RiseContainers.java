package com.phoenix.phoenixtales.rise.block;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.alloyfactory.AlloyContainer;
import com.phoenix.phoenixtales.rise.block.blocks.assembler.AssemblerContainer;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;

import java.util.ArrayList;
import java.util.List;

public class RiseContainers {
    public static List<ContainerType<?>> containers = new ArrayList<>();

    public static ContainerType<PressContainer> PRESS_CONTAINER = createContainer("press_container",
            IForgeContainerType.create((((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new PressContainer(windowId, world, pos, inv, inv.player);
            }))));

    public static ContainerType<AlloyContainer> ALLOY_CONTAINER = createContainer("alloy_container",
            IForgeContainerType.create((((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new AlloyContainer(windowId, world, pos, inv, inv.player);
            }))));

    public static ContainerType<AssemblerContainer> ASSEMBLER_CONTAINER = createContainer("assembler_container",
            IForgeContainerType.create((((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new AssemblerContainer(windowId, world, pos, inv, inv.player);
            }))));


    private static <C extends Container> ContainerType<C> createContainer(String id, ContainerType<C> container) {
        container.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        containers.add(container);
        return container;
    }

}
