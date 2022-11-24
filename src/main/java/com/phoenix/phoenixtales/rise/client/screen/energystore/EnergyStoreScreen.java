package com.phoenix.phoenixtales.rise.client.screen.energystore;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStore;
import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStoreContainer;
import com.phoenix.phoenixtales.rise.block.blocks.energystore.EnergyStoreTile;
import com.phoenix.phoenixtales.rise.network.LeftClickPacket;
import com.phoenix.phoenixtales.rise.network.PacketHandler;
import com.phoenix.phoenixtales.rise.service.enums.EnergyHandlingType;
import com.phoenix.phoenixtales.rise.service.enums.RelativeDirection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;


public class EnergyStoreScreen extends ContainerScreen<EnergyStoreContainer> {


    private static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/energy_store/main_gui.png");
    private static final ResourceLocation CONFIG_BUTTON = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/config_button.png");
    private static final ResourceLocation NEXT_PAGE_BUTTON_TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/next_page_button.png");
    private static final ResourceLocation SIDE_CONFIG_BUTTON = new ResourceLocation(PhoenixTales.MOD_ID, "textures/gui/side_config_button.png");
    private EnergyStoreTile tile = null;
    private int page;

    //todo button der mit der himmelsrichtung beziffert ist und beim hovern den status anzeigt
    //todo die buttons neu placen

    //todo widget das als energyanzeige fungiert, für alles verwendbar, zeigt alle möglchen stats an
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
            this.font.drawString(matrixStack, "Stored: " + this.tile.getStored() + " kJ", 12, 19, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "Capacity: " + ((double) (this.tile.getCapacity() / 1000)) + " kJ", 12, 31, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "max Receive: " + this.tile.getR() + " J", 12, 46, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "max Extract: " + this.tile.getE() + " J", 12, 57, MathHelper.rgb(142, 143, 144));
        } else if (page == 1) {
            this.font.drawString(matrixStack, "Side-Configuration", 8, 12, MathHelper.rgb(175, 34, 34));
        } else if (page == 2) {
            this.font.drawString(matrixStack, "add upgrades here", 12, 19, MathHelper.rgb(142, 143, 144));
            this.font.drawString(matrixStack, "capacity speed", 12, 31, MathHelper.rgb(142, 143, 144));
        }
//        this.font.drawText(matrixStack, ITextComponent.getTextComponentOrEmpty(new TranslationTextComponent("screen.phoenixtales.energystore.energy").toString()), i + 20, i + 20, y, 4210752);
    }

    //todo make the button use translation text component
    private void initializeSCButtons() {
        this.sideConfigButtons[0] = new SideConfigButton(this.tile.CONFIG.get(RelativeDirection.FRONT), "front", this.guiLeft + 35, this.guiTop + 36, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button,RelativeDirection.FRONT);
            ((SideConfigButton) button).setPosition(this.guiLeft + 35, this.guiTop + 36);
        });
        this.sideConfigButtons[0].setPosition(this.guiLeft + 35, this.guiTop + 36);
        this.sideConfigButtons[1] = new SideConfigButton(this.tile.CONFIG.get(RelativeDirection.BACK), "back", this.guiLeft + 80, this.guiTop + 36, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button, RelativeDirection.BACK);
            ((SideConfigButton) button).setPosition(this.guiLeft + 80, this.guiTop + 36);
        });
        this.sideConfigButtons[1].setPosition(this.guiLeft + 80, this.guiTop + 36);
        this.sideConfigButtons[2] = new SideConfigButton(this.tile.CONFIG.get(RelativeDirection.LEFT), "left", this.guiLeft + 95, this.guiTop + 36, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button, RelativeDirection.LEFT);
            ((SideConfigButton) button).setPosition(this.guiLeft + 95, this.guiTop + 36);
        });
        this.sideConfigButtons[2].setPosition(this.guiLeft + 95, this.guiTop + 36);
        this.sideConfigButtons[3] = new SideConfigButton(this.tile.CONFIG.get(RelativeDirection.RIGHT), "right", this.guiLeft + 50, this.guiTop + 36, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button, RelativeDirection.RIGHT);
            ((SideConfigButton) button).setPosition(this.guiLeft + 50, this.guiTop + 36);
        });
        this.sideConfigButtons[3].setPosition(this.guiLeft + 50, this.guiTop + 36);
        this.sideConfigButtons[4] = new SideConfigButton(this.tile.CONFIG.get(RelativeDirection.TOP), "up", this.guiLeft + 65, this.guiTop + 44, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button, RelativeDirection.TOP);
            ((SideConfigButton) button).setPosition(this.guiLeft + 65, this.guiTop + 44);
        });
        this.sideConfigButtons[4].setPosition(this.guiLeft + 65, this.guiTop + 44);
        this.sideConfigButtons[5] = new SideConfigButton(this.tile.CONFIG.get(RelativeDirection.BOTTOM), "bottom", this.guiLeft + 65, this.guiTop + 29, 15, 15, 0, 0, 16, 16, SIDE_CONFIG_BUTTON, (button) -> {
            this.handleClick((SideConfigButton) button, RelativeDirection.BOTTOM);
            ((SideConfigButton) button).setPosition(this.guiLeft + 65, this.guiTop + 29);
        });
        this.sideConfigButtons[5].setPosition(this.guiLeft + 65, this.guiTop + 29);
    }

    private void handleClick(SideConfigButton button, RelativeDirection direction) {
        BlockPos pos = this.tile.getPos();
        if (button.getState() == EnergyHandlingType.NONE) {
            PacketHandler.CHANNEL.sendToServer(new LeftClickPacket(EnergyHandlingType.INPUT, direction, pos));
            button.setState(EnergyHandlingType.INPUT);
        } else if (button.getState() == EnergyHandlingType.INPUT) {
            PacketHandler.CHANNEL.sendToServer(new LeftClickPacket(EnergyHandlingType.OUTPUT, direction, pos));
            button.setState(EnergyHandlingType.OUTPUT);
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

    //render the actual state
    private class SideConfigButton extends Button {

        protected final ResourceLocation resourceLocation;
        protected final int xTexStart;
        protected final int yTexStart;
        protected final int yDiffText;
        protected final int xDiffText;
        protected final int textureWidth;
        protected final int textureHeight;
        protected EnergyHandlingType state;
        protected String name;

        public SideConfigButton(EnergyHandlingType state, String label, int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn, int yDiffTextIn, int xDiffTextIn, ResourceLocation resourceLocationIn, Button.IPressable onPressIn) {
            this(state, label, xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, xDiffTextIn, resourceLocationIn, 256, 256, onPressIn);
        }

        public SideConfigButton(EnergyHandlingType state, String label, int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn, int yDiffTextIn, int xDiffTextIn, ResourceLocation resourceLocationIn, int textureWidth, int textureHeight, Button.IPressable onPressIn) {
            this(state, label, xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, xDiffTextIn, resourceLocationIn, textureWidth, textureHeight, onPressIn, StringTextComponent.EMPTY);
        }

        public SideConfigButton(EnergyHandlingType state, String label, int x, int y, int width, int height, int xTexStart, int yTexStart, int yDiffText, int xDiffText, ResourceLocation resourceLocation, int textureWidth, int textureHeight, Button.IPressable onPress, ITextComponent title) {
            this(state, label, x, y, width, height, xTexStart, yTexStart, yDiffText, xDiffText, resourceLocation, textureWidth, textureHeight, onPress, EMPTY_TOOLTIP, title);
        }

        public SideConfigButton(EnergyHandlingType state, String label, int x, int y, int width, int height, int xTexStart, int yTexStart, int yDiffText, int xDiffText, ResourceLocation resourceLocation, int textureWidth, int textureHeight, Button.IPressable onPress, Button.ITooltip onHover, ITextComponent title) {
            super(x, y, width, height, title, onPress, onHover);
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
            this.xTexStart = xTexStart;
            this.yTexStart = yTexStart;
            this.yDiffText = yDiffText;
            this.xDiffText = xDiffText;
            this.resourceLocation = resourceLocation;
            this.state = state;
            this.name = label;
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
                case INPUT:
                    j += this.xDiffText;
                    break;
                case OUTPUT:
                    j += (this.xDiffText + this.xDiffText);
                    break;
            }

            RenderSystem.enableDepthTest();
            blit(matrixStack, this.x, this.y, (float) j, (float) i, this.width, this.height, this.textureWidth, this.textureHeight);
            if (this.isHovered()) {
                this.renderToolTip(matrixStack, mouseX, mouseY);
            }
        }

        @Override
        public void renderToolTip(MatrixStack matrixStack, int mouseX, int mouseY) {
            String text = "none";
            switch (this.state) {
                case INPUT:
                    text = "input";
                    break;
                case OUTPUT:
                    text = "output";
                    break;
            }
            EnergyStoreScreen.this.renderTooltip(matrixStack, new StringTextComponent(this.name + " | " + text), mouseX, mouseY);
        }

        public void setState(EnergyHandlingType stateIn) {
            this.state = stateIn;
        }

        public EnergyHandlingType getState() {
            return this.state;
        }
    }

}
