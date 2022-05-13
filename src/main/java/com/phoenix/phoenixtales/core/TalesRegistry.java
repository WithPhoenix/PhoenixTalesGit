package com.phoenix.phoenixtales.core;

import com.phoenix.phoenixtales.fall.block.FallBlocks;
import com.phoenix.phoenixtales.fall.item.FallItems;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import com.phoenix.phoenixtales.origins.entity.OriginsEntityTypes;
import com.phoenix.phoenixtales.origins.item.OriginsItems;
import com.phoenix.phoenixtales.origins.world.biome.OriginsBiomes;
import com.phoenix.phoenixtales.origins.world.feature.TalesFeatures;
import com.phoenix.phoenixtales.origins.world.feature.placer.TalesPlacerType;
import com.phoenix.phoenixtales.origins.world.surfacebuilder.TalesSurfaceBuilders;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.block.RiseContainers;
import com.phoenix.phoenixtales.rise.item.RiseItems;
import com.phoenix.phoenixtales.rise.service.RiseRecipeTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
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
        for (Block block: FallBlocks.blocks) {
            event.getRegistry().register(block);
        }
        doneMsg("blocks");
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
        doneMsg("items");
    }

    @SubscribeEvent
    public static void registerEntityType(RegistryEvent.Register<EntityType<?>> event) {
        for (EntityType<?> entity : OriginsEntityTypes.entityTypes) {
            event.getRegistry().register(entity);
        }
        doneMsg("entities");
    }

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
        for (ContainerType<?> container : RiseContainers.containers) {
            event.getRegistry().register(container);
        }
        doneMsg("containers");
    }

    @SubscribeEvent
    public static void registerRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        for (IRecipeSerializer<?> serializer : RiseRecipeTypes.serializers) {
            event.getRegistry().register(serializer);
        }
        RiseRecipeTypes.createPressRecipeTypes();
        doneMsg("recipe types");
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        OriginsBiomes.biomes.forEach(biome -> event.getRegistry().register(biome));
        doneMsg("biomes");
    }


    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        for (Feature<?> feature : TalesFeatures.features) {
            event.getRegistry().register(feature);
        }
        doneMsg("features");
    }

    @SubscribeEvent
    public static void registerSB(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        for (SurfaceBuilder<?> builder : TalesSurfaceBuilders.builders) {
            event.getRegistry().register(builder);
        }
        doneMsg("surface builders");
    }

    @SubscribeEvent
    public static void registerPlacerTypes(RegistryEvent.Register<BlockPlacerType<?>> event) {
        for (BlockPlacerType<?> placerType : TalesPlacerType.placertypes) {
            event.getRegistry().register(placerType);
        }
        doneMsg("block placer types");
    }

    private static void doneMsg(String msg) {
        PhoenixTales.log.info("registered " + msg);
    }
}
