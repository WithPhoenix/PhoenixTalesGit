package com.phoenix.phoenixtales.rise.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressContainer;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressTile;
import com.phoenix.phoenixtales.rise.client.screen.component.EnergyField;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import org.jetbrains.annotations.Nullable;

public class PressScreen extends ContainerScreen<PressContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/press_gui.png");
    private PressTile tile = null;
    private EnergyField energyField;

    public PressScreen(PressContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }


    @Override
    protected void init() {
        super.init();

        this.tile = this.getTileEntity();
        this.energyField = new EnergyField(this.guiLeft + 167, this.guiTop + 5, 3, 75,null, this.tile.getEnergyStorage());
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


        //Bretzelfresser#1927
        //progress
        double percent = (double) (this.tile.getProgressPercent()) / 100d;
        this.blit(matrixStack, i + 84, j + 33, 192, 0, 8, (int) (percent * 23d));

        //energy
//        double energyP = (double) (this.tile.getEnergyPercent()) / 100d;
//        this.blit(matrixStack, i + 167, j + 5, 202, 1, 3, 75 - ((int) (energyP * 75d)));

    }

    @Override
    protected void renderComponentHoverEffect(MatrixStack matrixStack, @Nullable Style style, int mouseX, int mouseY) {
        super.renderComponentHoverEffect(matrixStack, style, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        super.drawGuiContainerForegroundLayer(matrixStack, x, y);
    }

    private void renderEnergyTooltip() {
        if (this.areaIsHovered()) {

        }
    }

    //    public List<Component> getTooltips() {
//
////        return List.of(Component(energy.getEnergyStored()+"/"+energy.getMaxEnergyStored()+" FE"));
//    }
    private boolean areaIsHovered() {
        return true;
    }

    @Override
    protected void renderHoveredTooltip(MatrixStack matrixStack, int x, int y) {
        super.renderHoveredTooltip(matrixStack, x, y);
    }

    private PressTile getTileEntity() {
        ClientWorld world = this.getMinecraft().world;

        if (world != null) {
            TileEntity tile = world.getTileEntity(this.getContainer().getPos());

            if (tile instanceof PressTile) {
                return (PressTile) tile;
            }
        }

        return null;
    }

}