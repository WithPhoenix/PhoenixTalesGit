package com.phoenix.phoenixtales.rise.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.phoenix.phoenixtales.rise.block.blocks.cable.CableTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class CableRender extends TileEntityRenderer<CableTile> {
    public CableRender(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(CableTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
    }
}
