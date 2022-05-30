package com.phoenix.phoenixtales.rise.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.phoenix.phoenixtales.rise.block.blocks.initial.solderingtable.SolderingTableTile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class SolderingTableRenderer extends TileEntityRenderer<SolderingTableTile> {
    public SolderingTableRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(SolderingTableTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

    }
}
