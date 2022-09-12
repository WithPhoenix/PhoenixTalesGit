package com.phoenix.phoenixtales.rise.client.screen.component;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.Collections;

public class EnergyField extends Widget {

    private ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/component/energy_field.png");
    private final IEnergyStorage storage;

    public EnergyField(int x, int y, int width, int height, ITextComponent title, IEnergyStorage storage) {
        super(x, y, width, height, title);
        this.storage = storage;
    }

    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(this.TEXTURE);


        double energy = (double) (this.storage.getEnergyStored()) / (double) (this.storage.getMaxEnergyStored());
        RenderSystem.enableDepthTest();
        this.blit(matrixStack, x, y, 0, 0, 3, 75 - ((int) (energy * 75d)));

        if (this.isHovered()) {
            this.renderToolTip(matrixStack, mouseX, mouseY);
        }
    }

    @Override
    public void renderToolTip(MatrixStack matrixStack, int mouseX, int mouseY) {
        Minecraft minecraft = Minecraft.getInstance();
        FontRenderer fontRenderer = minecraft.fontRenderer;
        String text = this.storage.getEnergyStored() + "/" + this.storage.getMaxEnergyStored();
        net.minecraftforge.fml.client.gui.GuiUtils.drawHoveringText(matrixStack, Collections.singletonList(new StringTextComponent(text)), mouseX, mouseY, width, height, -1, fontRenderer);
    }
}
