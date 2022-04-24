package com.phoenix.phoenixtales.origins.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.phoenix.phoenixtales.origins.entity.entities.WraithEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class WraithModel<T extends WraithEntity> extends EntityModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer tail_1;
    private final ModelRenderer tail;
    private final ModelRenderer mid_right;
    private final ModelRenderer mid_right_1;
    private final ModelRenderer mid_right_r2;
    private final ModelRenderer mid_right_1_r2;
    private final ModelRenderer bottom_right_r1;
    private final ModelRenderer bottom_right_1_r1;
    private final ModelRenderer bottom_left_1_r1;
    private final ModelRenderer bottom_left_r1;
    private final ModelRenderer top_right_r1;
    private final ModelRenderer top_right_1_r1;
    private final ModelRenderer top_left_1_r1;
    private final ModelRenderer top_left_r1;

    public WraithModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(45, 13).addBox(-2.5F, -28.0F, -2.0F, 5.0F, 13.0F, 4.0F, 0.0F, false);
        body.setTextureOffset(34, 0).addBox(-2.5F, -32.0F, -2.0F, 5.0F, 3.0F, 4.0F, 0.0F, false);

        tail_1 = new ModelRenderer(this);
        tail_1.setRotationPoint(0.0F, -3.0F, 0.0F);
        body.addChild(tail_1);
        setRotationAngle(tail_1, 0.6109F, 0.0F, 0.0F);
        tail_1.setTextureOffset(17, 2).addBox(-1.5F, -6.7F, 5.8F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, -15.0F, 2.0F);
        body.addChild(tail);
        setRotationAngle(tail, 0.3491F, 0.0F, 0.0F);
        tail.setTextureOffset(0, 0).addBox(-2.0F, -1.3F, -3.4F, 4.0F, 4.0F, 3.0F, 0.0F, false);

        mid_right = new ModelRenderer(this);
        mid_right.setRotationPoint(0.0F, -19.0F, 6.0F);
        body.addChild(mid_right);
        setRotationAngle(mid_right, -0.0479F, 0.2129F, 0.1694F);
        mid_right.setTextureOffset(0, 7).addBox(2.6F, -3.0F, -5.2F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        mid_right_1 = new ModelRenderer(this);
        mid_right_1.setRotationPoint(6.0F, -20.0F, -1.1F);
        body.addChild(mid_right_1);
        setRotationAngle(mid_right_1, 0.2232F, -0.3145F, -0.4357F);
        mid_right_1.setTextureOffset(0, 0).addBox(0.5F, -0.6F, -14.1F, 1.0F, 1.0F, 15.0F, 0.0F, false);

        mid_right_r2 = new ModelRenderer(this);
        mid_right_r2.setRotationPoint(-15.0F, -19.0F, 6.0F);
        body.addChild(mid_right_r2);
        setRotationAngle(mid_right_r2, 1.116F, -0.047F, -0.3149F);
        mid_right_r2.setTextureOffset(0, 9).addBox(8.0F, -5.7F, -5.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        mid_right_1_r2 = new ModelRenderer(this);
        mid_right_1_r2.setRotationPoint(-9.0F, -20.0F, -1.1F);
        body.addChild(mid_right_1_r2);
        setRotationAngle(mid_right_1_r2, 0.0192F, 0.1726F, 0.3242F);
        mid_right_1_r2.setTextureOffset(0, 16).addBox(1.7F, -1.3F, -13.3F, 1.0F, 1.0F, 15.0F, 0.0F, false);

        bottom_right_r1 = new ModelRenderer(this);
        bottom_right_r1.setRotationPoint(-15.0F, -15.0F, 6.0F);
        body.addChild(bottom_right_r1);
        setRotationAngle(bottom_right_r1, 1.4335F, -0.5864F, -0.6224F);
        bottom_right_r1.setTextureOffset(0, 11).addBox(3.0F, -10.7F, -7.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        bottom_right_1_r1 = new ModelRenderer(this);
        bottom_right_1_r1.setRotationPoint(-9.0F, -16.0F, -1.1F);
        body.addChild(bottom_right_1_r1);
        setRotationAngle(bottom_right_1_r1, 0.4465F, -0.1891F, 0.3129F);
        bottom_right_1_r1.setTextureOffset(17, 1).addBox(3.3F, -2.1F, -15.0F, 1.0F, 1.0F, 15.0F, 0.0F, false);

        bottom_left_1_r1 = new ModelRenderer(this);
        bottom_left_1_r1.setRotationPoint(6.0F, -16.0F, -1.1F);
        body.addChild(bottom_left_1_r1);
        setRotationAngle(bottom_left_1_r1, 1.0264F, 0.1037F, -0.076F);
        bottom_left_1_r1.setTextureOffset(17, 17).addBox(-0.3F, -0.9F, -14.6F, 1.0F, 1.0F, 15.0F, 0.0F, false);

        bottom_left_r1 = new ModelRenderer(this);
        bottom_left_r1.setRotationPoint(0.0F, -15.0F, 6.0F);
        body.addChild(bottom_left_r1);
        setRotationAngle(bottom_left_r1, 0.0F, 0.4363F, 0.3927F);
        bottom_left_r1.setTextureOffset(0, 13).addBox(3.0F, -4.0F, -5.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        top_right_r1 = new ModelRenderer(this);
        top_right_r1.setRotationPoint(-15.0F, -22.0F, 6.0F);
        body.addChild(top_right_r1);
        setRotationAngle(top_right_r1, 1.1347F, -0.1413F, -0.5126F);
        top_right_r1.setTextureOffset(0, 16).addBox(7.0F, -5.7F, -7.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        top_right_1_r1 = new ModelRenderer(this);
        top_right_1_r1.setRotationPoint(-9.0F, -23.0F, -1.1F);
        body.addChild(top_right_1_r1);
        setRotationAngle(top_right_1_r1, 0.9624F, -0.2344F, -0.1712F);
        top_right_1_r1.setTextureOffset(0, 32).addBox(2.5F, 0.0F, -15.2F, 1.0F, 1.0F, 15.0F, 0.0F, false);

        top_left_1_r1 = new ModelRenderer(this);
        top_left_1_r1.setRotationPoint(6.0F, -23.0F, -1.1F);
        body.addChild(top_left_1_r1);
        setRotationAngle(top_left_1_r1, 1.0642F, 0.2815F, 0.1201F);
        top_left_1_r1.setTextureOffset(17, 33).addBox(-0.5F, -0.9F, -14.5F, 1.0F, 1.0F, 15.0F, 0.0F, false);

        top_left_r1 = new ModelRenderer(this);
        top_left_r1.setRotationPoint(0.0F, -22.0F, 6.0F);
        body.addChild(top_left_r1);
        setRotationAngle(top_left_r1, 0.0F, 0.4363F, 0.3927F);
        top_left_r1.setTextureOffset(17, 0).addBox(3.0F, -4.0F, -5.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
