package com.phoenix.phoenixtales.core.plugin.jei.category;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressContainer;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked"})
public class PressingCategory implements IRecipeCategory<PressingRecipe>, IRecipeTransferInfo<PressContainer> {
    private static final int inputSlot = 0;
    private static final int outputSlot = 1;

    public static final ResourceLocation UID = new ResourceLocation(PhoenixTales.MOD_ID, ".press");
    public static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/jei/press.png");
    private IDrawable background;
    private IDrawable icon;


    public PressingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 69, 55);
        this.icon = helper.createDrawableIngredient(new ItemStack(RiseBlocks.PRESS_FACTORY));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends PressingRecipe> getRecipeClass() {
        return PressingRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("category."+PhoenixTales.MOD_ID+".press").getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

//    protected IDrawableAnimated getArrow(PressingRecipe recipe) {
//        int cookTime = recipe.getProcessingTime();
//        if (cookTime <= 0) {
//            cookTime = regularProcessingTime;
//        }
//        return this.cachedArrows.getUnchecked(cookTime);
//    }

    @Override
    public void draw(PressingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        drawTime(recipe, matrixStack, 5);
        drawEnergy(recipe, matrixStack, 40, "approx.");
    }

    protected void drawTime(PressingRecipe recipe, MatrixStack matrixStack, int y) {
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

    private void drawEnergy(PressingRecipe recipe, MatrixStack stack, int y, String approx) {
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

    @Override
    public void setIngredients(PressingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutputForJei());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, PressingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        //input
        recipeLayout.getItemStacks().init(inputSlot, true, 5, 5);
        //output
        recipeLayout.getItemStacks().init(outputSlot, false, 5, 33);
        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public Class getContainerClass() {
        return PressContainer.class;
    }

    @Override
    public ResourceLocation getRecipeCategoryUid() {
        return UID;
    }

    @Override
    public boolean canHandle(PressContainer container) {
        return false;
    }

    @Override
    public List<Slot> getRecipeSlots(PressContainer container) {
        return null;
    }

    @Override
    public List<Slot> getInventorySlots(PressContainer container) {
        List<Slot> list = new ArrayList<>();
//        list.add();
        return null;
    }
}
