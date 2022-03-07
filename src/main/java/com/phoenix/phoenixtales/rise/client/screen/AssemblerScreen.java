package com.phoenix.phoenixtales.rise.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.assembler.AssemblerContainer;
import com.phoenix.phoenixtales.rise.block.blocks.assembler.AssemblerTile;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AssemblerScreen extends ContainerScreen<AssemblerContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/assembler_gui.png");
    private AssemblerTile tile = null;

    public AssemblerScreen(AssemblerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
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
        this.blit(matrixStack, i + 84, j + 48, 191, 0, 10, (int) (percent * 13d));

        //energy
        double energyP = (double) (this.tile.getEnergyPercent()) / 100d;
        this.blit(matrixStack, i + 167, j + 5, 218, 1, 3, 75 - ((int) (energyP * 75d)));

//        //progress
//
//        //arrow
//        this.blit(matrixStack, i + 84, j + 48, 191, 0, 10, 13);
//        //left top
//        this.blit(matrixStack, i + 40, j + 32, 177, 22, 3, 8);
//        //left bottom
//        this.blit(matrixStack, i + 40, j + 40, 177, 23, 3, 13);
//        //left mid
//        this.blit(matrixStack, i + 43, j + 38, 180, 21, 36, 3);
//        //right top
//        this.blit(matrixStack, i + 133, j + 32, 213, 37, 3, 8);
//        //right bottom
//        this.blit(matrixStack, i + 133, j + 40, 213, 45, 3, 13);
//        //right mid
//        this.blit(matrixStack, i + 97, j + 38, 177, 43, 36, 3);


//        if (container.setTrue()) {
//            this.blit(matrixStack, i + 82, j + 9, 176, 0, 13, 15);
//        }

//        int l = this.container.getCookProgressionScaled();


    }


    private AssemblerTile getTileEntity() {
        ClientWorld world = this.getMinecraft().world;

        if (world != null) {
            TileEntity tile = world.getTileEntity(this.getContainer().getPos());

            if (tile instanceof AssemblerTile) {
                return (AssemblerTile) tile;
            }
        }

        return null;
    }
}