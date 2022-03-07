package com.phoenix.phoenixtales.origins.client.ister;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;

public class OPister extends ItemStackTileEntityRenderer {

    public static final OPister instance = new OPister();

    public static OPister getInstance() {
        return instance;
    }

    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

//        DecoPortalTile tile = ((DecoPortal)((BlockItem)stack.getItem()).getBlock()).createNewTileEntity();


        super.func_239207_a_(stack, p_239207_2_, matrixStack, buffer, combinedLight, combinedOverlay);
    }




}
