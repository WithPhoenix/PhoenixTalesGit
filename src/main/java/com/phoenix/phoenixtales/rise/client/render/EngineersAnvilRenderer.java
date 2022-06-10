package com.phoenix.phoenixtales.rise.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.phoenix.phoenixtales.rise.block.blocks.initial.engineersanvil.EngineersAnvil;
import com.phoenix.phoenixtales.rise.block.blocks.initial.engineersanvil.EngineersAnvilTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;

public class EngineersAnvilRenderer extends TileEntityRenderer<EngineersAnvilTile> {
    public EngineersAnvilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(EngineersAnvilTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Direction direction = tileEntityIn.getBlockState().get(EngineersAnvil.FACING).getOpposite();
        ItemStack stack = tileEntityIn.getStack();

        if (stack != ItemStack.EMPTY) {
            matrixStackIn.push();
            matrixStackIn.translate(0.5d, 0.55d, 0.5d);
            Direction direction1 = Direction.byHorizontalIndex((direction.getHorizontalIndex()) % 4);
            float f = direction1.getHorizontalAngle();
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
            matrixStackIn.pop();
        }

    }
}
