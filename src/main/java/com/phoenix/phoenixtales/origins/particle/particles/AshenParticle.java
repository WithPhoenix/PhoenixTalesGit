package com.phoenix.phoenixtales.origins.particle.particles;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;

public class AshenParticle extends Particle {

    public static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/particles/ashen_tile.png");



    protected AshenParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);

    }

    @Override
    public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {

    }

    @Override
    public IParticleRenderType getRenderType() {
        return null;
    }
}
