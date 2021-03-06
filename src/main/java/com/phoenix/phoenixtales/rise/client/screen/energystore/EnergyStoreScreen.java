package com.phoenix.phoenixtales.rise.client.screen.energystore;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStore;
import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStoreContainer;
import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStoreTile;
import com.phoenix.phoenixtales.rise.network.LeftClickPacket;
import com.phoenix.phoenixtales.rise.network.PacketHandler;
import com.phoenix.phoenixtales.rise.service.EnergyHandlingType;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;


public class EnergyStoreScreen extends ContainerScreen<EnergyStoreContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/energy_store/main_gui.png");
    private static final ResourceLocation CONFIG_BUTTON = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/config_button.png");
    private static final ResourceLocation NEXT_PAGE_BUTTON_TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/next_page_button.png");
    private static final ResourceLocation SIDE_CONFIG_BUTTON = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/side_config_button.png");
    private EnergyStoreTile tile = null;
    private int page;
    SideConfigButton[] sideConfigButtons = new SideConfigButton[6];
    private ImageButton nextPage = new ImageButton(this.guiLeft + 177, this.guiTop + 70, 15, 15, 0, 0, 16, NEXT_PAGE_BUTTON_TEXTURE, (button) -> {
        if (this.page != 2) {
            ++this.page;
        }
        for (SideConfigButton btn : sideConfigButtons) {
            btn.visible = this.page == 1;
        }
        ((ImageButton) button).setPosition(this.guiLeft + 177, this.guiTop + 70);
    });
    private ImageButton previousPage = new ImageButton(this.guiLeft - 16, this.guiTop + 70, 15, 15, 16, 0, 16, NEXT_PAGE_BUTTON_TEXTURE, (button) -> {
        if (this.page != 0) {
            --this.page;
        }
        for (SideConfigButton btn : sideConfigButtons) {
            btn.visible = this.page == 1;
        }
        ((ImageButton) button).setPosition(this.guiLeft - 16, this.guiTop + 70);
    });
    //use an array so i can track the whole array, probably also with the other ints so i have one single array
//    private List<SideConfigButton> sideConfigButtons = new ArrayList<>();

    public EnergyStoreScreen(EnergyStoreContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.page = 0;
//        this.xSize = 276;
//        this.playerInventoryTitleX = 107;
    }

    @Override
    protected void init() {
        super.init();

        this.tile = this.getTileEntity();

        this.addButton(nextPage);
        this.nextPage.setPosition(this.guiLeft + 177, this.guiTop + 70);

        this.addButton(previousPage);
        this.previousPage.setPosition(this.guiLeft - 16, this.guiTop + 70);

        initializeSCButtons();
        for (SideConfigButton btn : this.sideConfigButtons) {
            this.addButton(btn);
            btn.visible = false;
        }
    }

    @Override
    public void tick() {
        super.tick();
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

        // r255 g37 b0
        double energyP = (double) (this.tile.getEnergyPercent()) / 100d;
        this.blit(matrixStack, i + 161, j + 5, 178, 0, 8, 75 - ((int) (energyP * 75d)));

    }

    //Draw all the important info
    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        super.drawGuiContainerForegroundLayer(matrixStack, x, y);
        if (page == 0) {
            this.font.drawString(matrixStack, "Stored: " + this.tile.getStored()  + " kJ", 12, 19, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "Capacity: " + ((double) (this.tile.getCapacity() / 1000)) + " kJ", 12, 31, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "max Receive: " + this.tile.getR()  + " J", 12, 46, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "max Extract: " + this.tile.getE() + " J", 12, 57, MathHelper.rgb(142, 143, 144));
        } else if (page == 1) {
            this.font.drawString(matrixStack, "north", 12, 22, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "west", 12, 38, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "down", 12, 54, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "south", 75, 22, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "east", 75, 38, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "up", 75, 54, MathHelper.rgb(142, 143, 144));
        } else if (page == 2) {
            this.font.drawString(matrixStack, "add upgrades here", 12, 19, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "capacity speed", 12, 31, MathHelper.rgb(142, 143, 144));
        }
//        this.font.drawText(matrixStack, ITextComponent.getTextComponentOrEmpty(new TranslationTextComponent("screen.phoenixtales.energystore.energy").toString()), i + 20, i + 20, y, 4210752);
    }

    private void initializeSCButtons() {
        this.sideConfigButtons[0] = new SideConfigButton(this.tile.getBlockState().get(EnergyStore.NORTH), this.guiLeft + 45, this.guiTop + 18, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button, Direction.NORTH);
            ((SideConfigButton) button).setPosition(this.guiLeft + 45, this.guiTop + 18);
        });
        this.sideConfigButtons[0].setPosition(this.guiLeft + 45, this.guiTop + 18);
        this.sideConfigButtons[1] = new SideConfigButton(this.tile.getBlockState().get(EnergyStore.SOUTH), this.guiLeft + 108, this.guiTop + 18, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button, Direction.SOUTH);
            ((SideConfigButton) button).setPosition(this.guiLeft + 108, this.guiTop + 18);
        });
        this.sideConfigButtons[1].setPosition(this.guiLeft + 108, this.guiTop + 18);
        this.sideConfigButtons[2] = new SideConfigButton(this.tile.getBlockState().get(EnergyStore.WEST), this.guiLeft + 45, this.guiTop + 34, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button, Direction.WEST);
            ((SideConfigButton) button).setPosition(this.guiLeft + 45, this.guiTop + 34);
        });
        this.sideConfigButtons[2].setPosition(this.guiLeft + 45, this.guiTop + 34);
        this.sideConfigButtons[3] = new SideConfigButton(this.tile.getBlockState().get(EnergyStore.EAST), this.guiLeft + 108, this.guiTop + 34, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button, Direction.EAST);
            ((SideConfigButton) button).setPosition(this.guiLeft + 108, this.guiTop + 34);
        });
        this.sideConfigButtons[3].setPosition(this.guiLeft + 108, this.guiTop + 34);
        this.sideConfigButtons[4] = new SideConfigButton(this.tile.getBlockState().get(EnergyStore.DOWN), this.guiLeft + 45, this.guiTop + 50, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button, Direction.DOWN);
            ((SideConfigButton) button).setPosition(this.guiLeft + 45, this.guiTop + 50);
        });
        this.sideConfigButtons[4].setPosition(this.guiLeft + 45, this.guiTop + 50);
        this.sideConfigButtons[5] = new SideConfigButton(this.tile.getBlockState().get(EnergyStore.UP), this.guiLeft + 108, this.guiTop + 50, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button, Direction.UP);
            ((SideConfigButton) button).setPosition(this.guiLeft + 108, this.guiTop + 50);
        });
        this.sideConfigButtons[5].setPosition(this.guiLeft + 108, this.guiTop + 50);
    }

    private void handleClick(SideConfigButton button, Direction direction) {
        BlockPos pos = this.tile.getPos();
        if (button.getState() == EnergyHandlingType.NONE) {
            PacketHandler.CHANNEL.sendToServer(new LeftClickPacket(EnergyHandlingType.RECEIVE, direction, pos));
            button.setState(EnergyHandlingType.RECEIVE);
        } else if (button.getState() == EnergyHandlingType.RECEIVE) {
            PacketHandler.CHANNEL.sendToServer(new LeftClickPacket(EnergyHandlingType.EXTRACT, direction, pos));
            button.setState(EnergyHandlingType.EXTRACT);
        } else {
            PacketHandler.CHANNEL.sendToServer(new LeftClickPacket(EnergyHandlingType.NONE, direction, pos));
            button.setState(EnergyHandlingType.NONE);
        }
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
