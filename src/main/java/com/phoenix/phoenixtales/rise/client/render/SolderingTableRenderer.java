package com.phoenix.phoenixtales.rise.client.render;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.phoenix.phoenixtales.rise.block.blocks.initial.solderingtable.SolderingTableBlock;
import com.phoenix.phoenixtales.rise.block.blocks.initial.solderingtable.SolderingTableTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3f;

import java.util.HashMap;

public class SolderingTableRenderer extends TileEntityRenderer<SolderingTableTile> {
    private final HashMap<Integer, Double[]> NORTH_VALUES = Util.make(Maps.newHashMap(), (p) -> {
        p.put(0, new Double[]{0.5d, 0.7d, 0.5d});
        p.put(1, new Double[]{0.7d, 0.7d, 0.7d});
        p.put(2, new Double[]{0.3d, 0.7d, 0.7d});
        p.put(3, new Double[]{0.5d, 0.7d, 0.8d});
    });
    private final HashMap<Integer, Double[]> SOUTH_VALUES = Util.make(Maps.newHashMap(), (p) -> {
        p.put(0, new Double[]{0.5d, 0.7d, 0.5d});
        p.put(1, new Double[]{0.7d, 0.7d, 0.3d});
        p.put(2, new Double[]{0.3d, 0.7d, 0.3d});
        p.put(3, new Double[]{0.5d, 0.7d, 0.2d});
    });
    private final HashMap<Integer, Double[]> EAST_VALUES = Util.make(Maps.newHashMap(), (p) -> { //done
        p.put(0, new Double[]{0.5d, 0.7d, 0.5d});
        p.put(1, new Double[]{0.3d, 0.7d, 0.7d});
        p.put(2, new Double[]{0.3d, 0.7d, 0.3d});
        p.put(3, new Double[]{0.2d, 0.7d, 0.5d});
    });
    private final HashMap<Integer, Double[]> WEST_VALUES = Util.make(Maps.newHashMap(), (p) -> { //done
        p.put(0, new Double[]{0.5d, 0.7d, 0.5d});
        p.put(1, new Double[]{0.7d, 0.7d, 0.7d});
        p.put(2, new Double[]{0.7d, 0.7d, 0.3d});
        p.put(3, new Double[]{0.8d, 0.7d, 0.5d});
    });

    public SolderingTableRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(SolderingTableTile tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Direction direction = tileEntityIn.getBlockState().get(SolderingTableBlock.FACING).getOpposite();
        NonNullList<ItemStack> nonnulllist = tileEntityIn.getItems();
        HashMap<Integer, Double[]> map;
        switch (direction) {
            case NORTH:
                map = NORTH_VALUES;
                break;
            case SOUTH:
                map = SOUTH_VALUES;
                break;
            case EAST:
                map = EAST_VALUES;
                break;
            case WEST:
                map = WEST_VALUES;
                break;
            default:
                return;
        }

        for (int i = 0; i < 4; i++) {
            ItemStack stack = nonnulllist.get(i);
            if (stack != ItemStack.EMPTY) {
                matrixStackIn.push();
                matrixStackIn.translate(map.get(i)[0], map.get(i)[1], map.get(i)[2]);
                Direction direction1 = Direction.byHorizontalIndex((direction.getHorizontalIndex()) % 4);
                float f = -direction1.getHorizontalAngle();
                matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
                matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
                matrixStackIn.scale(0.2F, 0.2F, 0.2F);
                Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
                matrixStackIn.pop();
            }
        }
    }
}
