package com.phoenix.phoenixtales.rise.client.screen.energystore;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.gui.ConfigGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ConfigScreen extends ContainerScreen<ConfigGui> {
    private static final ResourceLocation CONFIG_GUI = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/config_gui.png");
    private static final ResourceLocation CONFIG_BUTTON = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/config_button.png");
    private int width;
    private int height;

    public ConfigScreen(ConfigGui screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {
        super.init();

        this.addButton(new ImageButton(this.guiLeft + 136, this.guiTop + 33, 19, 19, 0, 0, 20, CONFIG_BUTTON, (button) -> {
            ((ImageButton) button).setPosition(this.guiLeft + 136, this.guiTop + 33);
        }));

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(CONFIG_GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }
}
