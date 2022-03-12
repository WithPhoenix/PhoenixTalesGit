package com.phoenix.phoenixtales.rise.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStoreContainer;
import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStoreTile;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;


public class EnergyStoreScreen extends ContainerScreen<EnergyStoreContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/energy_store_gui.png");
    private EnergyStoreTile tile = null;

    public EnergyStoreScreen(EnergyStoreContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {
        super.init();

        this.tile = this.getTileEntity();
    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);

        double energyP = (double) (this.tile.getEnergyPercent()) / 100d;
        this.blit(matrixStack, i + 161, j + 6, 178, 0, 8, 75 - ((int) (energyP * 75d)));

    }

    //Draw all the important info
    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        super.drawGuiContainerForegroundLayer(matrixStack, x, y);
//        this.font.drawText(matrixStack, ITextComponent.getTextComponentOrEmpty(new TranslationTextComponent("screen.phoenixtales.energystore.energy").toString()), i + 20, i + 20, y, 4210752);
        this.font.drawString(matrixStack, "Stored: " + ((double) (this.tile.getEnergy() / 1000)) + " kJ", 12, 19, MathHelper.rgb(142, 143, 144));
        this.font.drawString(matrixStack, "max. Stored: " + ((double) (this.tile.getMaxEnergy() / 1000)) + " kJ", 12, 28, MathHelper.rgb(142, 143, 144));
        this.font.drawString(matrixStack, "Transfer Rate: " + this.tile.getTransferRatePerTick() + " J/t", 12, 37, MathHelper.rgb(142, 143, 144));
        this.font.drawString(matrixStack, "max. Transfer Rate: " + this.tile.getMaxTransferRate() + " J/t", 12, 46, MathHelper.rgb(142, 143, 144));
    }

    private EnergyStoreTile getTileEntity() {
        ClientWorld world = this.getMinecraft().world;

        if (world != null) {
            TileEntity tile = world.getTileEntity(this.getContainer().getPos());

            if (tile instanceof EnergyStoreTile) {
                return (EnergyStoreTile) tile;
            }
        }

        return null;
    }
}
