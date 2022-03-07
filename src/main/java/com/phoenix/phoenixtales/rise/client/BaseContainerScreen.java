package com.phoenix.phoenixtales.rise.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BaseContainerScreen<T extends Container> extends ContainerScreen<T> {
    protected ResourceLocation background;
    protected int width;
    private int height;

    public BaseContainerScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

//    public BaseContainerScreen(T container, PlayerInventory inventoryIn, ITextComponent titleIn, ResourceLocation backgroundIn, int widthIn, int heightIn, int backgroundW, int backgroundH) {
//        super(container, inventoryIn, titleIn);
//        this.background = backgroundIn;
//    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.getMinecraft().getTextureManager().bindTexture(this.background);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }
}
