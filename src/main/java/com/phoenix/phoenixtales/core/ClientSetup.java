package com.phoenix.phoenixtales.core;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.phoenix.phoenixtales.origins.block.OriginsBlocks;
import com.phoenix.phoenixtales.origins.block.OriginsTileEntities;
import com.phoenix.phoenixtales.origins.block.blocks.decoportal.DecoPortalTile;
import com.phoenix.phoenixtales.origins.client.entity.render.WraithRenderer;
import com.phoenix.phoenixtales.origins.client.ter.DecoPortalTileRenderer;
import com.phoenix.phoenixtales.origins.client.ter.PortalTileRenderer;
import com.phoenix.phoenixtales.origins.entity.OriginsEntityTypes;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.block.RiseContainers;
import com.phoenix.phoenixtales.rise.block.RiseTileEntities;
import com.phoenix.phoenixtales.rise.client.render.EngineersAnvilRenderer;
import com.phoenix.phoenixtales.rise.client.render.GenericCableRender;
import com.phoenix.phoenixtales.rise.client.screen.AlloyScreen;
import com.phoenix.phoenixtales.rise.client.screen.AssemblerScreen;
import com.phoenix.phoenixtales.rise.client.screen.PressScreen;
import com.phoenix.phoenixtales.rise.client.screen.energystore.EnergyStoreScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = PhoenixTales.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            setRenderLayer();

            registerScreens();

            bindTileRenderers();

        });
        bindEntityRenderers();
    }

//    @SubscribeEvent
//    public static void renderOverlay(final RenderGameOverlayEvent event) {
////TODO if in perma frost biome or special effect, aplly effect here
//    }

    private static void setRenderLayer() {


//        RenderTypeLookup.setRenderLayer(OriginsBlocks.HUO_LOG, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.HUO_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.HUO_DOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.HUO_TRAPDOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.HUO_LEAVES, RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(OriginsBlocks.SEARING_GRASS, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.SEARING_FERN, RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(OriginsBlocks.TALL_SEARING_GRASS, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.LARGE_SEARING_FERN, RenderType.getCutout());

//        RenderTypeLookup.setRenderLayer(OriginsBlocks.HUI_LOG, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.HUI_SAPLING, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.HUI_DOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.HUI_TRAPDOOR, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.HUI_LEAVES, RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(OriginsBlocks.ASHEN_GRASS, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.ASHEN_FERN, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.TALL_ASHEN_GRASS, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(OriginsBlocks.LARGE_ASHEN_FERN, RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(RiseBlocks.VANADIUM_CHASSIS, RenderType.getCutout());
    }

    private static void registerScreens() {
//        ScreenManager.registerFactory(OriginsContainers.CHANGE_BLOCK_CONTAINER_TYPE.get(), ChangeScreen::new);
        ScreenManager.registerFactory(RiseContainers.ENERGY_STORE_CONTAINER, EnergyStoreScreen::new);
        ScreenManager.registerFactory(RiseContainers.ASSEMBLER_CONTAINER, AssemblerScreen::new);
        ScreenManager.registerFactory(RiseContainers.PRESS_CONTAINER, PressScreen::new);
        ScreenManager.registerFactory(RiseContainers.ALLOY_CONTAINER, AlloyScreen::new);
//        ScreenManager.registerFactory(OriginsContainers.ENHANCING_CONTAINER.get(), EnhancingScreen::new);
    }

    private static void bindTileRenderers() {
        ClientRegistry.bindTileEntityRenderer(OriginsTileEntities.PORTAL_TILE, PortalTileRenderer::new);
        ClientRegistry.bindTileEntityRenderer(OriginsTileEntities.DECO_PORTAL_TILE, DecoPortalTileRenderer::new);

        ClientRegistry.bindTileEntityRenderer(RiseTileEntities.ENGINEERS_ANVIL, EngineersAnvilRenderer::new);
    }

    private static void bindEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(OriginsEntityTypes.WRAITH, WraithRenderer::new);
    }

    private static final DecoPortalTile deco_tile = new DecoPortalTile();
    private static final NonNullLazy<ItemStackTileEntityRenderer> renderer = NonNullLazy.of(() -> new ItemStackTileEntityRenderer() {
        @Override
        public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
            DecoPortalTileRenderer.instance.renderFromItem(stack, deco_tile, p_239207_2_, matrixStack, buffer, combinedLight, combinedOverlay);
        }
    });

    public static ItemStackTileEntityRenderer getDecoPortRenderer() {
        return renderer.get();
    }

}
