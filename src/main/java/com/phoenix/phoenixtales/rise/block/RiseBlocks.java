package com.phoenix.phoenixtales.rise.block;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.alloyfactory.AlloyFactory;
import com.phoenix.phoenixtales.rise.block.blocks.assembler.Assembler;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressFactory;
import com.phoenix.phoenixtales.rise.block.blocks.tank.TankBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

public class RiseBlocks {

    public static List<Block> blocks = new ArrayList<>();

    //TODO more blockstates, ore animated texture?
    //ore
    public static final Block ORE_VANADIUM = createBlock("vanadium_ore", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.GRAY).hardnessAndResistance(10f, 10f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
    public static final Block ORE_NICKEL = createBlock("nickel_ore", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.GRAY).hardnessAndResistance(5f, 6f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
    public static final Block ORE_KERNITE = createBlock("kernite_ore", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.LIGHT_GRAY).hardnessAndResistance(3f, 3f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
    public static final Block ORE_APATITE = createBlock("apatite_ore", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.LIGHT_GRAY).hardnessAndResistance(3f, 3f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));

    //metal blocks
    public static final Block BLOCK_VANADIUM = createBlock("vanadium_block", new Block(AbstractBlock.Properties.create((Material.IRON), MaterialColor.GRAY).hardnessAndResistance(10f, 10f).harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.METAL).setRequiresTool()));
    public static final Block BLOCK_NICKEL = createBlock("nickel_block", new Block(AbstractBlock.Properties.create((Material.IRON), MaterialColor.GRAY).hardnessAndResistance(5f, 6f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).setRequiresTool()));
    public static final Block BLOCK_INVAR = createBlock("invar_block", new Block(AbstractBlock.Properties.create((Material.IRON), MaterialColor.GRAY).hardnessAndResistance(5f, 6f).harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.METAL).setRequiresTool()));

    public static final Block VANADIUM_CHASSIS = createBlock("vanadium_chassis", new Block(AbstractBlock.Properties.create((Material.IRON), MaterialColor.LIGHT_GRAY).hardnessAndResistance(6f, 10f).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.METAL).setRequiresTool().notSolid()));

    //other
    public static final Block CERAMIC_BLOCK = createBlock("ceramic_block", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(7f, 10f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
    public static final Block BURNT_CERAMIC_BLOCK = createBlock("burnt_ceramic_block", new Block(AbstractBlock.Properties.create((Material.ROCK), MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(7f, 10f).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //machines and tech related blocks
    public static final Block ASSEMBLER = createBlock("assembler", new Assembler());
    public static final Block PRESS_FACTORY = createBlock("press_factory", new PressFactory());
    public static final Block ALLOY_FACTORY = createBlock("alloy_factory", new AlloyFactory());
    public static final Block TANK = createBlock("tank", new TankBlock());


//    public static final Block CHANGE_BLOCK = createBlock("change_block", new ChangeBlock());
//    public static final Block PRESS = createBlock("press_factory", new PressFactory());
//    public static final Block ENHANCING_FACTORY = createBlock("enhancing_factory", new EnhancingFactory());
//    public static final Block SIMPLE_HT_FACTORY = createBlock("simple_ht_factory", new SimpleHTFactory());
//    public static final Block SIMPLE_ASSEMBLER = createBlock("simple_assembler", new SimpleAssembler());
//    public static final Block SIMPLE_ALLOY_FACTORY = createBlock("simple_alloy_factory", new SimpleAlloy());


    private static Block createBlock(String id, Block block) {
        block.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, id));
        blocks.add(block);
        return block;
    }

}