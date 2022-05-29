package com.phoenix.phoenixtales.core.plugin.jei.category;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.block.blocks.assembler.AssemblingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class AssemblingCategory implements IRecipeCategory<AssemblingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(PhoenixTales.MOD_ID, ".assemble");
    public static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/jei/assembler.png");
    private IDrawable background;
    private IDrawable icon;
//    private IDrawableStatic check_mark;

    public AssemblingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 121, 74);
        this.icon = helper.createDrawableIngredient(new ItemStack(RiseBlocks.ASSEMBLER));
//        this.check_mark = helper.createDrawable(TEXTURE, 176, 0, 13, 17);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends AssemblingRecipe> getRecipeClass() {
        return AssemblingRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("category."+PhoenixTales.MOD_ID+".assemble").getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(AssemblingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, AssemblingRecipe recipe, IIngredients ingredients) {
        //inputs
        recipeLayout.getItemStacks().init(0, true, 31, 20);
        recipeLayout.getItemStacks().init(1, true, 5, 5);
        recipeLayout.getItemStacks().init(2, true, 57, 5);
        recipeLayout.getItemStacks().init(3, true, 5, 42);
        recipeLayout.getItemStacks().init(4, true, 57, 42);
        //output
        recipeLayout.getItemStacks().init(5, false, 31, 51);
        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public void draw(AssemblingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        drawTime(recipe, matrixStack, 5);
        drawEnergy(recipe, matrixStack, 55, "approx.");
    }

    protected void drawTime(AssemblingRecipe recipe, MatrixStack matrixStack, int y) {
        int cookTime = recipe.getProcessingTime();
        if (cookTime > 0) {
            int cookTimeSeconds = cookTime / 20;
            String timeString = String.valueOf(cookTimeSeconds) + " sec";
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.fontRenderer;
            int stringWidth = fontRenderer.getStringWidth(timeString);
            fontRenderer.drawString(matrixStack, timeString, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }


    private void drawEnergy(AssemblingRecipe recipe, MatrixStack stack, int y, String approx) {
        int energy = recipe.neededEnergy();
        if (energy > 0) {
            String energyString = String.valueOf(energy) + " J";
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.fontRenderer;
            int stringWidth = fontRenderer.getStringWidth(energyString);
            int approxWidth = fontRenderer.getStringWidth(approx);
            fontRenderer.drawString(stack, approx, background.getWidth() - approxWidth, y - 10, 0xFF808080);
            fontRenderer.drawString(stack, energyString, background.getWidth() - stringWidth, y, 0xFF808080);
        }
    }
}
