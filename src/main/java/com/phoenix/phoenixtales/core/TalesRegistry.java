package com.phoenix.phoenixtales.core;

import com.phoenix.phoenixtales.fall.item.FallItems;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import com.phoenix.phoenixtales.origins.block.OriginsTileEntities;
import com.phoenix.phoenixtales.origins.item.OriginsItems;
import com.phoenix.phoenixtales.origins.world.gen.feature.TalesFeatures;
import com.phoenix.phoenixtales.rise.RiseRecipeTypes;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.block.RiseContainers;
import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.item.RiseItems;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PhoenixTales.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TalesRegistry {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : OriginsBlocks.blocks) {
            event.getRegistry().register(block);
        }
        for (Block block : RiseBlocks.blocks) {
            event.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : OriginsItems.items) {
            event.getRegistry().register(item);
        }
        for (Item item : RiseItems.items) {
            event.getRegistry().register(item);
        }
        for (Item item : FallItems.items) {
            event.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public static void registerTiles(RegistryEvent.Register<TileEntityType<?>> event) {
        for (TileEntityType<?> tile : RiseTileEntities.tiles) {
            event.getRegistry().register(tile);
        }
        for (TileEntityType<?> tile : OriginsTileEntities.tiles) {
            event.getRegistry().register(tile);
        }
    }

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
        for (ContainerType<?> container : RiseContainers.containers) {
            event.getRegistry().register(container);
        }
    }

    @SubscribeEvent
    public static void registerRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        for (IRecipeSerializer<?> serializer : RiseRecipeTypes.serializers) {
            event.getRegistry().register(serializer);
        }
        RiseRecipeTypes.createPressRecipeTypes();
    }

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        for (Feature<?> feature : TalesFeatures.features) {
            event.getRegistry().register(feature);
        }
    }
}
