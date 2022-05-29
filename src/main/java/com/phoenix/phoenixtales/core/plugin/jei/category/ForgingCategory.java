package com.phoenix.phoenixtales.core.plugin.jei.category;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.block.blocks.initial.engineersanvil.ForgingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class ForgingCategory implements IRecipeCategory<ForgingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(PhoenixTales.MOD_ID, ".forge");
    public static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/jei/forge.png");
    private IDrawable background;
    private IDrawable icon;

    public ForgingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 63, 64);
        this.icon = helper.createDrawableIngredient(new ItemStack(RiseBlocks.ENGINEERS_ANVIL));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends ForgingRecipe> getRecipeClass() {
        return ForgingRecipe.class;
    }

    @Override
    public String getTitle() {
        return new TranslationTextComponent("category." + PhoenixTales.MOD_ID + ".forge").getString();
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
    public void setIngredients(ForgingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredientsForJei());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ForgingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup group = recipeLayout.getItemStacks();
        group.init(0, true, 5, 25);
        group.init(1, true, 5, 43);
        group.init(2, true, 23, 4);

        group.init(3, false, 41, 25);
        group.set(ingredients);
    }
}
