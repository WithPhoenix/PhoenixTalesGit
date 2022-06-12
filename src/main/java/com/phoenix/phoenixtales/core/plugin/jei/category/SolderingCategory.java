package com.phoenix.phoenixtales.core.plugin.jei.category;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.block.blocks.initial.solderingtable.SolderingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

@SuppressWarnings("deprecation")
public class SolderingCategory implements IRecipeCategory<SolderingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(PhoenixTales.MOD_ID, ".soldering");
    public static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/jei/soldering.png");
    private IDrawable background;
    private IDrawable icon;

    public SolderingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 114, 45);
        this.icon = helper.createDrawableIngredient(new ItemStack(RiseBlocks.SOLDERING_TABLE));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends SolderingRecipe> getRecipeClass() {
        return SolderingRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("category." + PhoenixTales.MOD_ID + ".soldering").getString();
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
    public void setIngredients(SolderingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredientsForJei());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SolderingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup group = recipeLayout.getItemStacks();
        group.init(0, true, 5, 14);
        group.init(1, true, 23, 5);
        group.init(2, true, 41, 5);
        group.init(3, true, 59, 14);
        group.init(4, true, 32, 23);

        group.init(5, false, 92, 19);
        group.set(ingredients);
    }

    @Override
    public void draw(SolderingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        float ctf = 1 - recipe.getChanceToFail();
        float ctfPercent = Math.round(ctf * 100);
        String string = String.valueOf(ctfPercent) + "%";
        Minecraft minecraft = Minecraft.getInstance();
        FontRenderer fontRenderer = minecraft.fontRenderer;
        int stringWidth = fontRenderer.getStringWidth(string);
        fontRenderer.drawString(matrixStack, string, background.getWidth() - stringWidth, 0, 0xFF808080);

        int clicks = recipe.getTime();
        int clicksSeconds = clicks / 20;
        String s = String.valueOf(clicksSeconds) + " sec";
        int width = fontRenderer.getStringWidth(s);
        fontRenderer.drawString(matrixStack, s, background.getWidth() - stringWidth, 10, 0xFF808080);
    }
}
