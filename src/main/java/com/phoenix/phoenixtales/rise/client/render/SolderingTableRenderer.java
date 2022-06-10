package com.phoenix.phoenixtales.rise.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.phoenix.phoenixtales.rise.block.blocks.initial.solderingtable.SolderingTableBlock;
import com.phoenix.phoenixtales.rise.block.blocks.initial.solderingtable.SolderingTableTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Vector3f;

public class SolderingTableRenderer extends TileEntityRenderer<SolderingTableTile> {
    public SolderingTableRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(SolderingTableTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Direction direction = tileEntityIn.getBlockState().get(SolderingTableBlock.FACING);
        NonNullList<ItemStack> nonnulllist = tileEntityIn.getItems();
        ItemStack stack = nonnulllist.get(0);
        if (stack != ItemStack.EMPTY) {
            matrixStackIn.push();
            matrixStackIn.translate(0.5d, 0.7d, 0.5d);
            Direction direction1 = Direction.byHorizontalIndex((direction.getHorizontalIndex()) % 4);
            float f = -direction1.getHorizontalAngle();
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
            matrixStackIn.scale(0.2F, 0.2F, 0.2F);
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
            matrixStackIn.pop();
        }

        ItemStack stack1 = nonnulllist.get(1);
        if (stack1 != ItemStack.EMPTY) {
            matrixStackIn.push();
            matrixStackIn.translate(0.25d, 0.7d, 0.25d);
            Direction direction1 = Direction.byHorizontalIndex((direction.getHorizontalIndex()) % 4);
            float f = -direction1.getHorizontalAngle();
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
            matrixStackIn.scale(0.2F, 0.2F, 0.2F);
            Minecraft.getInstance().getItemRenderer().renderItem(stack1, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
            matrixStackIn.pop();
        }

        ItemStack stack2 = nonnulllist.get(2);
        if (stack2 != ItemStack.EMPTY) {
            matrixStackIn.push();
            matrixStackIn.translate(0.75d, 0.7d, 0.25d);
            Direction direction1 = Direction.byHorizontalIndex((direction.getHorizontalIndex()) % 4);
            float f = -direction1.getHorizontalAngle();
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
            matrixStackIn.scale(0.2F, 0.2F, 0.2F);
            Minecraft.getInstance().getItemRenderer().renderItem(stack2, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
            matrixStackIn.pop();
        }

        ItemStack stack3 = nonnulllist.get(3);
        if (stack3 != ItemStack.EMPTY) {
            matrixStackIn.push();
            matrixStackIn.translate(0.5d, 0.7d, 0.75d);
            Direction direction1 = Direction.byHorizontalIndex((direction.getHorizontalIndex()) % 4);
            float f = -direction1.getHorizontalAngle();
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
            matrixStackIn.scale(0.2F, 0.2F, 0.2F);
            Minecraft.getInstance().getItemRenderer().renderItem(stack3, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
            matrixStackIn.pop();
        }
    }
}
