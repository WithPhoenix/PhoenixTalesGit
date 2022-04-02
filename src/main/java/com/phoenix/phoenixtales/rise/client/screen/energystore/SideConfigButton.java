package com.phoenix.phoenixtales.rise.client.screen.energystore;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.phoenix.phoenixtales.rise.service.EnergyHandlingType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SideConfigButton extends Button {

    protected final ResourceLocation resourceLocation;
    protected final int xTexStart;
    protected final int yTexStart;
    protected final int yDiffText;
    protected final int xDiffText;
    protected final int textureWidth;
    protected final int textureHeight;
    protected EnergyHandlingType state;

    public SideConfigButton(EnergyHandlingType state, int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn, int yDiffTextIn, int xDiffTextIn, ResourceLocation resourceLocationIn, Button.IPressable onPressIn) {
        this(state, xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, xDiffTextIn, resourceLocationIn, 256, 256, onPressIn);
    }

    public SideConfigButton(EnergyHandlingType state, int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn, int yDiffTextIn, int xDiffTextIn, ResourceLocation resourceLocationIn, int textureWidth, int textureHeight, Button.IPressable onPressIn) {
        this(state, xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, xDiffTextIn, resourceLocationIn, textureWidth, textureHeight, onPressIn, StringTextComponent.EMPTY);
    }

    public SideConfigButton(EnergyHandlingType state, int x, int y, int width, int height, int xTexStart, int yTexStart, int yDiffText, int xDiffText, ResourceLocation resourceLocation, int textureWidth, int textureHeight, Button.IPressable onPress, ITextComponent title) {
        this(state, x, y, width, height, xTexStart, yTexStart, yDiffText, xDiffText, resourceLocation, textureWidth, textureHeight, onPress, EMPTY_TOOLTIP, title);
    }

    public SideConfigButton(EnergyHandlingType state, int x, int y, int width, int height, int xTexStart, int yTexStart, int yDiffText, int xDiffText, ResourceLocation resourceLocation, int textureWidth, int textureHeight, Button.IPressable onPress, Button.ITooltip onHover, ITextComponent title) {
        super(x, y, width, height, title, onPress, onHover);
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.xTexStart = xTexStart;
        this.yTexStart = yTexStart;
        this.yDiffText = yDiffText;
        this.xDiffText = xDiffText;
        this.resourceLocation = resourceLocation;
        this.state = state;
    }

    public void setPosition(int xIn, int yIn) {
        this.x = xIn;
        this.y = yIn;
    }

    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(this.resourceLocation);
        int i = this.yTexStart;
        if (this.isHovered()) {
            i += this.yDiffText;
        }
        int j = this.xTexStart;
        switch (this.state) {
            case RECEIVE:
                j += this.xDiffText;
                break;
            case EXTRACT:
                j += (this.xDiffText + this.xDiffText);
                break;
        }

        RenderSystem.enableDepthTest();
        blit(matrixStack, this.x, this.y, (float) j, (float) i, this.width, this.height, this.textureWidth, this.textureHeight);
        if (this.isHovered()) {
            this.renderToolTip(matrixStack, mouseX, mouseY);
        }
    }

    public void setState(EnergyHandlingType stateIn) {
        this.state = stateIn;
    }

    public EnergyHandlingType getState() {
        return this.state;
    }
}
