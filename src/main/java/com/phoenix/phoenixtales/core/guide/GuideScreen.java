package com.phoenix.phoenixtales.core.guide;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

@SuppressWarnings("deprecation")
public class GuideScreen extends Screen {
    private final ResourceLocation BACKGROUND = new ResourceLocation(PhoenixTales.MOD_ID, "textures/guide/background.png");

    public GuideScreen() {
        super(new StringTextComponent("Guide"));

        this.minecraft = Minecraft.getInstance();
    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderBackground(MatrixStack matrixStack) {
        this.minecraft.getTextureManager().bindTexture(BACKGROUND);
        this.blit(matrixStack, 10, 10, 0, 0, this.width, this.height);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.renderBackground(matrixStack);
    }


    public void openScreen() {
        minecraft.displayGuiScreen(this);
    }
}
