package com.phoenix.phoenixtales.origins.client.entity.render;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.client.entity.model.WraithModel;
import com.phoenix.phoenixtales.origins.entity.entities.WraithEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class WraithRenderer extends MobRenderer<WraithEntity, WraithModel<WraithEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/entity/wraith.png");

    public WraithRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WraithModel<>(), 0.01f);
    }

    @Override
    public ResourceLocation getEntityTexture(WraithEntity entity) {
        return TEXTURE;
    }
}
