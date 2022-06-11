package com.phoenix.phoenixtales.core.plugin.jei.category;

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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class SolderingCategory implements IRecipeCategory<SolderingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(PhoenixTales.MOD_ID, ".soldering");
    public static final ResourceLocation TEXTURE = new ResourceLocation(PhoenixTales.MOD_ID, "textures/jei/soldering.png");
    private IDrawable background;
    private IDrawable icon;

    public SolderingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 110, 43);
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
        group.init(0, true, 3, 12);
        group.init(1, true, 21, 3);
        group.init(2, true, 39, 3);
        group.init(3, true, 57, 12);
        group.init(4, true, 30, 21);

        group.init(5, false, 90, 17);
        group.set(ingredients);
    }
}
