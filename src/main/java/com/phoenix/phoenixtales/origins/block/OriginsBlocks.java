package com.phoenix.phoenixtales.origins.block;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.block.blocks.Ash;
import com.phoenix.phoenixtales.origins.block.blocks.Crystal;
import com.phoenix.phoenixtales.origins.block.blocks.decoportal.DecoPortal;
import com.phoenix.phoenixtales.origins.block.blocks.portal.OriginsPortal;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

public class OriginsBlocks {

    public static List<Block> blocks = new ArrayList<>();

    public static final Block PORTAL = createBlock("portal", new OriginsPortal(AbstractBlock.Properties.create(Material.PORTAL, MaterialColor.BLACK).doesNotBlockMovement().setLightLevel((state) -> {
        return 15;
    }).hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
    public static final Block DECO_PORTAL = createBlock("deco_portal", new DecoPortal(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).setLightLevel((state) -> {
        return 15;
    }).hardnessAndResistance(50.0F, 1200.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.LODESTONE).setRequiresTool()));
    //block in dimension
    public static final Block SMOULDERING_STONE = createBlock("smouldering_stone", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(10f, 10f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
    public static final Block ASH = createBlock("ash", new Ash());
    public static final Block CRYSTAL = createBlock("crystal", new Crystal());

    //wood
    //TODO animate the wood a bit and change texture of sapling and leaves
    public static final Block HUO_LOG = createBlock("huo_log", new RotatedPillarBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).setLightLevel(BlockState -> 5).harvestTool(ToolType.AXE)));
    public static final Block HUO_PLANKS = createBlock("huo_planks", new Block(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).setLightLevel(BlockState -> 5).harvestTool(ToolType.AXE)));
    @SuppressWarnings("deprecation")
    public static final Block HUO_STAIRS = createBlock("huo_stairs", new StairsBlock(HUO_PLANKS.getDefaultState(), AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).harvestTool(ToolType.AXE).setLightLevel(BlockState -> 5).harvestLevel(0).hardnessAndResistance(6f, 12f)));
    public static final Block HUO_SLAB = createBlock("huo_slab", new SlabBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).setLightLevel(BlockState -> 5).harvestTool(ToolType.AXE)));
    public static final Block HUO_DOOR = createBlock("huo_door", new DoorBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).setLightLevel(BlockState -> 5).harvestTool(ToolType.AXE).notSolid()));
    public static final Block HUO_TRAPDOOR = createBlock("huo_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).setLightLevel(BlockState -> 5).harvestTool(ToolType.AXE).notSolid()));
    public static final Block HUO_LEAVES = createBlock("huo_leaves", new LeavesBlock(AbstractBlock.Properties.create((Material.LEAVES), MaterialColor.RED_TERRACOTTA).sound(SoundType.PLANT).hardnessAndResistance(1f, 2f).setLightLevel(BlockState -> 5).tickRandomly().notSolid()));

    //needs to be last to be next to the other ores
    public static final Block ORE_SULFUR_OVERWORLD = createBlock("sulfur_ore", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.GRAY).hardnessAndResistance(5f, 5f).harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool()));

    //TODO add to saplings tag
    //    public static final Block HUO_SAPLING = createBlock("huo_sapling", new SaplingBlock(new HuoTree(), AbstractBlock.Properties.create(Material.PLANTS).sound(SoundType.PLANT).doesNotBlockMovement().zeroHardnessAndResistance().tickRandomly().notSolid().setLightLevel(BlockState -> 10)));


    private static Block createBlock(String id, Block block) {
        block.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        blocks.add(block);
        return block;
    }

}
