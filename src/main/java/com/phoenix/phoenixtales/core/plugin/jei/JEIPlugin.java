package com.phoenix.phoenixtales.core.plugin.jei;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.core.plugin.jei.category.AssemblingCategory;
import com.phoenix.phoenixtales.core.plugin.jei.category.ForgingCategory;
import com.phoenix.phoenixtales.core.plugin.jei.category.PressingCategory;
import com.phoenix.phoenixtales.core.plugin.jei.category.SolderingCategory;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.block.blocks.assembler.AssemblingRecipe;
import com.phoenix.phoenixtales.rise.block.blocks.initial.engineersanvil.ForgingRecipe;
import com.phoenix.phoenixtales.rise.block.blocks.initial.solderingtable.SolderingRecipe;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressingRecipe;
import com.phoenix.phoenixtales.rise.service.RiseRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class JEIPlugin implements IModPlugin {


    //TODO: how do i show, that more than 1 item is an output

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(PhoenixTales.MOD_ID, "jei");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager manager = Objects.requireNonNull(Minecraft.getInstance().world).getRecipeManager();
        registration.addRecipes(manager.getRecipesForType(RiseRecipeTypes.PRESS_RECIPE).stream().filter(r -> r instanceof PressingRecipe).collect(Collectors.toList()), PressingCategory.UID);
        registration.addRecipes(manager.getRecipesForType(RiseRecipeTypes.ASSEMBLING_RECIPE).stream().filter(r -> r instanceof AssemblingRecipe).collect(Collectors.toList()), AssemblingCategory.UID);
        registration.addRecipes(manager.getRecipesForType(RiseRecipeTypes.FORGING_RECIPE).stream().filter(r -> r instanceof ForgingRecipe).collect(Collectors.toList()), ForgingCategory.UID);
        registration.addRecipes(manager.getRecipesForType(RiseRecipeTypes.SOLDERING_RECIPE).stream().filter(r -> r instanceof SolderingRecipe).collect(Collectors.toList()), SolderingCategory.UID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(
                new AssemblingCategory(helper),
                new PressingCategory(helper),
                new ForgingCategory(helper),
                new SolderingCategory(helper)
        );
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(RiseBlocks.ASSEMBLER), AssemblingCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(RiseBlocks.PRESS_FACTORY), PressingCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(RiseBlocks.ENGINEERS_ANVIL), ForgingCategory.UID);
        registration.addRecipeCatalyst(new ItemStack(RiseBlocks.SOLDERING_TABLE), SolderingCategory.UID);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(new PressingCategory(registration.getJeiHelpers().getGuiHelper()));
//        registration.addRecipeTransferHandler(AssemblerContainer.class, AssemblingCategory.UID, 0, 4, 5, 36);
//        registration.addRecipeTransferHandler(PressContainer.class, PressingCategory.UID, 0, 1, 2, 36);
    }

}
