package com.phoenix.phoenixtales.rise.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.alloyfactory.AlloyContainer;
import com.phoenix.phoenixtales.rise.block.blocks.alloyfactory.AlloyTile;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AlloyScreen extends ContainerScreen<AlloyContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/alloy_gui.png");
    private AlloyTile tile = null;

    public AlloyScreen(AlloyContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
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

        double percent = (double) (this.tile.getProgressPercent()) / 100d;
        if (percent < 0.4285d) {
            double p1 = 0.4285d * percent;
            this.blit(matrixStack, i + 64, j + 26, 199, 0, (int) (p1 * 24d), 3);
            this.blit(matrixStack, i + 88, j + 26, 223, 0, 24 - (int) (p1 * 24d), 3);
        } else {
            double p2 = 0.5715d * percent;
            this.blit(matrixStack, i + 64, j + 26, 199, 0, 24, 3);
            this.blit(matrixStack, i + 88, j + 26, 223, 0, 0, 3);
            this.blit(matrixStack, i + 84, j + 28, 219, 2, 8, (int) (p2 * 32d));
        }


        double energyP = (double) (this.tile.getEnergyPercent()) / 100d;
        this.blit(matrixStack, i + 167, j + 5, 194, 1, 3, 75 - ((int) (energyP * 75d)));


//        if (container.getProgress() > 0) {
//            this.playerInventory.player.sendMessage(new StringTextComponent("progress = " + this.getProgress() + " and total = " + this.getTotal()), playerInventory.player.getUniqueID());
//            int i1 = this.getContainer().getProgressScaled(23);
//            this.blit(matrixStack, i + 84, j + 33, 192, 0, 8, i1 + 23);
//        }

//        int k = this.container.getCookProgressionScaled();
//        this.blit(matrixStack, /*k*/i + 84, /*k*/j + 33, /*k*/192, /*k*/0, 8, k + 1);
//        if (container.isValid()) {
//            this.blit(matrixStack, i + 82, j + 9, 176, 0, 13, 17);
//        }
    }


    private AlloyTile getTileEntity() {
        ClientWorld world = this.getMinecraft().world;

        if (world != null) {
            TileEntity tile = world.getTileEntity(this.getContainer().getPos());

            if (tile instanceof AlloyTile) {
                return (AlloyTile) tile;
            }
        }

        return null;
    }

}