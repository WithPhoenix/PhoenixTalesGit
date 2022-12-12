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
    private int guiLeft;
    private int guiTop;
    protected int xSize = 512;
    protected int ySize = 256;

    public GuideScreen() {
        super(new StringTextComponent("Guide"));


    }

    @Override
    public void init(Minecraft minecraft, int width, int height) {
        super.init(minecraft, width, height);
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    protected void init() {
        super.init();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderBackground(MatrixStack matrixStack) {
        this.minecraft.getTextureManager().bindTexture(BACKGROUND);
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.renderBackground(matrixStack);
    }

    public void openScreen() {
        if (minecraft == null) {
            this.minecraft = Minecraft.getInstance();
        }
        minecraft.displayGuiScreen(this);
    }
}
