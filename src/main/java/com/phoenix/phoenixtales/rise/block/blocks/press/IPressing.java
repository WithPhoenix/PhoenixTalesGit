package com.phoenix.phoenixtales.rise.block.blocks.press;

import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nonnull;

public interface IPressing extends IRecipe<IInventory> {
    ResourceLocation TYPE_ID = new ResourceLocation(PhoenixTales.MOD_ID, "pressing");

    @Nonnull
    @Override
    default IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean canFit(int width, int height) {
        return true;
    }

    @Override
    default boolean isDynamic() {
        return true;
    }
}