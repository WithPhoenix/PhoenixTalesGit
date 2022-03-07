package com.phoenix.phoenixtales.origins.client.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.phoenix.phoenixtales.origins.block.blocks.decoportal.DecoPortalTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Matrix4f;

public class DecoPortalTileRenderer extends PortalTileRenderer<DecoPortalTile> {

    public static DecoPortalTileRenderer instance = null;

    public DecoPortalTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
        instance = this;
    }

    public void renderFromItem(ItemStack stack, DecoPortalTile te, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

//        ItemStack lock = KeyUtils.getLock(KeyUtils.getKey(stack), KeyUtils.isPrivate(stack), KeyUtils.getBound(stack));
        render(te, 0, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn,matrixStackIn,bufferIn);
    }

    @Override
    public void render(DecoPortalTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        RANDOM.setSeed(31100L);
        double d0 = tileEntityIn.getPos().distanceSq(this.renderDispatcher.renderInfo.getProjectedView(), true);
        int i = this.passes(d0);
        float f = this.getOffset();
        Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
        this.renderCube(tileEntityIn, f, 0.15F, matrix4f, bufferIn.getBuffer(RENDER_TYPES.get(0)));

        //this.renderCube0(tileEntityIn, f, 0.15F, matrix4f, bufferIn.getBuffer(RENDER_TYPES.get(0)));

        for (int j = 1; j < i; ++j) {
            this.renderCube(tileEntityIn, f, 2.0F / (float) (18 - j), matrix4f, bufferIn.getBuffer(RENDER_TYPES.get(j)));
            //this.renderCube0(tileEntityIn, f, 2.0F / (float) (18 - j), matrix4f, bufferIn.getBuffer(RENDER_TYPES.get(j)));
        }
    }

    @Override
    protected void renderCube(DecoPortalTile tileEntityIn, float p_228883_2_, float p_228883_3_, Matrix4f matrix4f, IVertexBuilder builder) {
        float f = (RANDOM.nextFloat() * 0.5F + 0.1F) * p_228883_3_;
        float f1 = (RANDOM.nextFloat() * 0.5F + 0.4F) * p_228883_3_;
        float f2 = (RANDOM.nextFloat() * 0.5F + 0.5F) * p_228883_3_;
        this.renderFace(tileEntityIn, matrix4f, builder, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, f, f1, f2, Direction.SOUTH);
        this.renderFace(tileEntityIn, matrix4f, builder, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f, f1, f2, Direction.NORTH);
        this.renderFace(tileEntityIn, matrix4f, builder, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, f, f1, f2, Direction.EAST);
        this.renderFace(tileEntityIn, matrix4f, builder, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, f, f1, f2, Direction.WEST);
        this.renderFace(tileEntityIn, matrix4f, builder, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f, f1, f2, Direction.DOWN);
        this.renderFace(tileEntityIn, matrix4f, builder, 0.0F, 1.0F, p_228883_2_, p_228883_2_, 1.0F, 1.0F, 0.0F, 0.0F, f, f1, f2, Direction.UP);
    }

    @Override
    protected float getOffset() {
        return 1.0F;
    }
}
