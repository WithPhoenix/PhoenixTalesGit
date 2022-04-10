package com.phoenix.phoenixtales.origins.block;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.origins.block.blocks.*;
import com.phoenix.phoenixtales.origins.block.blocks.decoportal.DecoPortal;
import com.phoenix.phoenixtales.origins.block.blocks.portal.OriginsPortal;
import com.phoenix.phoenixtales.origins.world.tree.HuiTree;
import com.phoenix.phoenixtales.origins.world.tree.HuoTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
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

    public static final Block REALMSTONE = createBlock("realmstone", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(12f, 12f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));

    public static final Block SEARING_STONE = createBlock("searing_stone", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(7f, 10f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
    public static final Block SEARING_COBBLESTONE = createBlock("searing_cobblestone", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(8f, 10f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
    public static final Block SEARING_DIRT /*r 122 g 80 b 77*/ = createBlock("searing_dirt", new Block(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(0.6F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL)));
    public static final Block SEARING_GRASS_BLOCK = createBlock("searing_grass_block", new OriginsGrassBlock(AbstractBlock.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));

    public static final Block ASHEN_STONE = createBlock("ashen_stone", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.LIGHT_GRAY).hardnessAndResistance(7f, 10f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
    public static final Block ASHEN_COBBLESTONE = createBlock("ashen_cobblestone", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.LIGHT_GRAY).hardnessAndResistance(8f, 10f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
    public static final Block ASHEN_DIRT = createBlock("ashen_dirt", new Block(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.CLAY).hardnessAndResistance(0.6f).harvestTool(ToolType.SHOVEL).sound(SoundType.GROUND)));
    public static final Block ASHEN_GRASS_BLOCK = createBlock("ashen_grass_block", new OriginsGrassBlock(AbstractBlock.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));

    //plants
    public static final Block SEARING_GRASS = createBlock("searing_grass", new TallGrassBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final Block SEARING_FERN = createBlock("searing_fern", new TallGrassBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final Block TALL_SEARING_GRASS = createBlock("tall_searing_grass", new OriginsDoublePlant(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final Block LARGE_SEARING_FERN = createBlock("large_searing_fern", new OriginsDoublePlant(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));

    public static final Block ASHEN_GRASS = createBlock("ashen_grass", new TallGrassBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final Block ASHEN_FERN = createBlock("ashen_fern", new TallGrassBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final Block TALL_ASHEN_GRASS = createBlock("tall_ashen_grass", new OriginsDoublePlant(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final Block LARGE_ASHEN_FERN = createBlock("large_ashen_fern", new OriginsDoublePlant(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));

    //other ornaments
    public static final Block ASH = createBlock("ash", new Ash());
    public static final Block CRYSTAL = createBlock("crystal", new Crystal());

    //wood
    //TODO animate the wood a bit and change texture of sapling and leaves
    public static final Block HUO_LOG = createBlock("huo_log", new RotatedPillarBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).setLightLevel(BlockState -> 5).harvestTool(ToolType.AXE)));
    public static final Block HUO_PLANKS = createBlock("huo_planks", new Block(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).setLightLevel(BlockState -> 5).harvestTool(ToolType.AXE)));
    public static final Block HUO_STAIRS = createBlock("huo_stairs", new StairsBlock(HUO_PLANKS::getDefaultState, AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).harvestTool(ToolType.AXE).setLightLevel(BlockState -> 5).harvestLevel(0).hardnessAndResistance(6f, 12f)));
    public static final Block HUO_SLAB = createBlock("huo_slab", new SlabBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).setLightLevel(BlockState -> 5).harvestTool(ToolType.AXE)));
    public static final Block HUO_DOOR = createBlock("huo_door", new DoorBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).setLightLevel(BlockState -> 5).harvestTool(ToolType.AXE).notSolid()));
    public static final Block HUO_TRAPDOOR = createBlock("huo_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).setLightLevel(BlockState -> 5).harvestTool(ToolType.AXE).notSolid()));
    public static final Block HUO_LEAVES = createBlock("huo_leaves", new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().setLightLevel(BlockState -> 5).setSuffocates(OriginsBlocks::isntSolid).setBlocksVision(OriginsBlocks::isntSolid)));
    public static final Block HUO_SAPLING = createBlock("huo_sapling", new OriginsSapling(new HuoTree(), AbstractBlock.Properties.create(Material.PLANTS).sound(SoundType.PLANT).doesNotBlockMovement().zeroHardnessAndResistance().tickRandomly().notSolid().setLightLevel(BlockState -> 10)));

    public static final Block HUI_LOG = createBlock("hui_log", new RotatedPillarBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).harvestTool(ToolType.AXE)));
    public static final Block HUI_PLANKS = createBlock("hui_planks", new Block(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).harvestTool(ToolType.AXE)));
    public static final Block HUI_STAIRS = createBlock("hui_stairs", new StairsBlock(HUI_PLANKS::getDefaultState, AbstractBlock.Properties.create(Material.NETHER_WOOD, MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).hardnessAndResistance(6f, 12f)));
    public static final Block HUI_SLAB = createBlock("hui_slab", new SlabBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).harvestTool(ToolType.AXE)));
    public static final Block HUI_DOOR = createBlock("hui_door", new DoorBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).harvestTool(ToolType.AXE).notSolid()));
    public static final Block HUI_TRAPDOOR = createBlock("hui_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create((Material.NETHER_WOOD), MaterialColor.RED_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(6f, 12f).harvestLevel(0).harvestTool(ToolType.AXE).notSolid()));
    public static final Block HUI_LEAVES = createBlock("hui_leaves", new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().setLightLevel(BlockState -> 5).setSuffocates(OriginsBlocks::isntSolid).setBlocksVision(OriginsBlocks::isntSolid)));
    public static final Block HUI_SAPLING = createBlock("hui_sapling", new OriginsSapling(new HuiTree(), AbstractBlock.Properties.create(Material.PLANTS).sound(SoundType.PLANT).doesNotBlockMovement().zeroHardnessAndResistance().tickRandomly().notSolid().setLightLevel(BlockState -> 10)));

    public static final Block CHARRED_DEBRIS = createBlock("charred_debris", new DebrisBlock());

    //needs to be last to be next to the other ores
    public static final Block ORE_SULFUR_OVERWORLD = createBlock("sulfur_ore", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.GRAY).hardnessAndResistance(5f, 5f).harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool()));

    //TODO add to saplings tag


    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    private static Block createBlock(String id, Block block) {
        block.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        blocks.add(block);
        return block;
    }

}
