package com.phoenix.phoenixtales.origins.client;

import com.phoenix.phoenixtales.origins.client.ter.PortalTileRenderer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;

public class OriginsRenderType extends RenderType {
    public OriginsRenderType(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
    }

    public static RenderType getPortal(int iterationIn) {
        RenderState.TransparencyState renderstate$transparencystate;
        RenderState.TextureState renderstate$texturestate;
        if (iterationIn <= 1) {
            renderstate$transparencystate = TRANSLUCENT_TRANSPARENCY;
            renderstate$texturestate = new RenderState.TextureState(PortalTileRenderer.SKY_TEXTURE, false, false);
        } else {
            renderstate$transparencystate = ADDITIVE_TRANSPARENCY;
            renderstate$texturestate = new RenderState.TextureState(PortalTileRenderer.PORTAL_TEXTURE, false, false);
        }
        return makeType("phoenix_portal", DefaultVertexFormats.POSITION_COLOR, 7, 256, false, true, RenderType.State.getBuilder().transparency(renderstate$transparencystate).texture(renderstate$texturestate).texturing(new RenderState.PortalTexturingState(iterationIn)).fog(BLACK_FOG).build(false));
    }
}
