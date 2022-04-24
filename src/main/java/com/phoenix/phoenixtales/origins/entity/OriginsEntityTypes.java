package com.phoenix.phoenixtales.origins.entity;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.entity.entities.WraithEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = PhoenixTales.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OriginsEntityTypes {
    public static List<EntityType<?>> entityTypes = new ArrayList<>();

    public static final EntityType<WraithEntity> WRAITH = create("wraith", EntityType.Builder.<WraithEntity>create(WraithEntity::new, EntityClassification.MONSTER).size(0.4f, 2.0f).trackingRange(32));

    public static <T extends Entity> EntityType<T> create(String id, EntityType.Builder<T> builder) {
        ResourceLocation location = new ResourceLocation(PhoenixTales.MOD_ID, id);
        EntityType<T> entityType = builder.build(location.toString());
        entityType.setRegistryName(location);
        entityTypes.add(entityType);
        return entityType;
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(WRAITH, WraithEntity.registerAttributes().create());
    }

    static {

    }
}
