package com.phoenix.phoenixtales.rise.block.blocks.assembler;

import com.phoenix.phoenixtales.core.PhoenixTales;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public interface IAssembling extends IRecipe<IInventory> {
    ResourceLocation TYPE_ID = new ResourceLocation(PhoenixTales.MOD_ID, "assembling");

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
