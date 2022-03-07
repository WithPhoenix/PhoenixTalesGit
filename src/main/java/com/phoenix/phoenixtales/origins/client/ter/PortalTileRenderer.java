package com.phoenix.phoenixtales.origins.client.ter;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.block.blocks.portal.OriginsPortalTile;
import com.phoenix.phoenixtales.origins.client.OriginsRenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PortalTileRenderer <T extends OriginsPortalTile> extends TileEntityRenderer<T> {
    public static final ResourceLocation SKY_TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/environment/dimension_sky.png");
    public static final ResourceLocation PORTAL_TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/entity/dimension_portal.png");
    protected static final Random RANDOM = new Random(31100L);
    protected static final List<RenderType> RENDER_TYPES = IntStream.range(0, 16).mapToObj((p_228882_0_) -> {
        return OriginsRenderType.getPortal(p_228882_0_ + 1);
    }).collect(ImmutableList.toImmutableList());

    public PortalTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(T tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
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

//    protected void renderCube(T tileEntityIn, float p_228883_2_, float p_228883_3_, Matrix4f p_228883_4_, IVertexBuilder p_228883_5_) {
//        float f = (RANDOM.nextFloat() * 0.5F + 0.1F) * p_228883_3_;
//        float f1 = (RANDOM.nextFloat() * 0.5F + 0.4F) * p_228883_3_;
//        float f2 = (RANDOM.nextFloat() * 0.5F + 0.5F) * p_228883_3_;
//        this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 0.0F, 0.5F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F, f, f1, f2, Direction.SOUTH);
//        this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 0.0F, 0.5F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f, f1, f2, Direction.NORTH);
//        this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 0.5F, 0.5F, 0.5F, 0.0F, 0.0F, 0.5F, 0.5F, 0.0F, f, f1, f2, Direction.EAST);
//        this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 0.0F, 0.0F, 0.0F, 0.5F, 0.0F, 0.5F, 0.5F, 0.0F, f, f1, f2, Direction.WEST);
//        this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 0.0F, 0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5F, 0.5F, f, f1, f2, Direction.DOWN);
//        this.renderFace(tileEntityIn, p_228883_4_, p_228883_5_, 0.0F, 0.5F, p_228883_2_, p_228883_2_, 0.5F, 0.5F, 0.0F, 0.0F, f, f1, f2, Direction.UP);
//    }

    protected void renderCube(T tileEntityIn, float p_228883_2_, float p_228883_3_, Matrix4f matrix4f, IVertexBuilder builder) {
        float f = (RANDOM.nextFloat() * 0.5F + 0.1F) * p_228883_3_;
        float f1 = (RANDOM.nextFloat() * 0.5F + 0.4F) * p_228883_3_;
        float f2 = (RANDOM.nextFloat() * 0.5F + 0.5F) * p_228883_3_;
//        this.renderFace(tileEntityIn, matrix4f, builder, 0.0F, 0.5F, p_228883_2_, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, f, f1, f2, Direction.SOUTH);
//        this.renderFace(tileEntityIn, matrix4f, builder, 0.0F, 0.5F, p_228883_2_, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f, f1, f2, Direction.NORTH);
//        this.renderFace(tileEntityIn, matrix4f, builder, 0.5F, 0.5F, p_228883_2_, 0.0F, 0.0F, 0.5F, 0.5F, 0.0F, f, f1, f2, Direction.EAST);
//        this.renderFace(tileEntityIn, matrix4f, builder, 0.0F, 0.0F, p_228883_2_, 0.0F, 0.0F, 0.5F, 0.5F, 0.0F, f, f1, f2, Direction.WEST);
//        this.renderFace(tileEntityIn, matrix4f, builder, 0.0F/**/, 0.5F/*done*/, p_228883_2_, 0.0F, 0.0F, 0.0F, 0.5F, 0.5F, f, f1, f2, Direction.DOWN);
//        this.renderFace(tileEntityIn, matrix4f, builder, 0.0F, 0.5F, p_228883_2_, p_228883_2_, 0.75F, 0.75F, 0.25F, 0.25F, f, f1, f2, Direction.UP);
        this.renderFace(tileEntityIn, matrix4f, builder, 0.0f, 0.5f, p_228883_3_, p_228883_3_, 0.0f, p_228883_3_, 0.0f, 0.25f, f, f1, f2, Direction.UP);
    }

    protected void renderFace(T tileEntityIn, Matrix4f matrix4f, IVertexBuilder builder, float x, float surfacetoX, float p_228884_6_, float p_228884_7_, float p_228884_8_, float p_228884_9_, float p_228884_10_, float p_228884_11_, float p_228884_12_, float p_228884_13_, float p_228884_14_, Direction p_228884_15_) {
        if (tileEntityIn.shouldRenderFace(p_228884_15_)) {
            builder.pos(matrix4f, x, p_228884_6_, p_228884_8_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();
            builder.pos(matrix4f, surfacetoX, p_228884_6_, p_228884_9_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();
            builder.pos(matrix4f, surfacetoX, p_228884_7_, p_228884_10_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();
            builder.pos(matrix4f, x, p_228884_7_, p_228884_11_).color(p_228884_12_, p_228884_13_, p_228884_14_, 1.0F).endVertex();
        }
    }

    protected int getPasses(double p_191286_1_) {
        if (p_191286_1_ > 36864.0D) {
            return 1;
        } else if (p_191286_1_ > 25600.0D) {
            return 3;
        } else if (p_191286_1_ > 16384.0D) {
            return 5;
        } else if (p_191286_1_ > 9216.0D) {
            return 7;
        } else if (p_191286_1_ > 4096.0D) {
            return 9;
        } else if (p_191286_1_ > 1024.0D) {
            return 11;
        } else if (p_191286_1_ > 576.0D) {
            return 13;
        } else {
            return p_191286_1_ > 256.0D ? 14 : 15;
        }
    }

    protected int passes(double p_191286_1_) {
        return getPasses(p_191286_1_) + 1;
    }

    protected float getOffset() {
        return 0.75F;
    }
}
