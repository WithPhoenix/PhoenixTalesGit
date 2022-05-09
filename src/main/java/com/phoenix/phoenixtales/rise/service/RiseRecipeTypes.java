package com.phoenix.phoenixtales.rise.service;

import com.phoenix.phoenixtales.core.PhoenixTales;
import com.phoenix.phoenixtales.rise.block.blocks.alloyfactory.AlloyingRecipe;
import com.phoenix.phoenixtales.rise.block.blocks.assembler.AssemblingRecipe;
import com.phoenix.phoenixtales.rise.block.blocks.press.PressingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class RiseRecipeTypes {

    public static List<IRecipeSerializer<?>> serializers = new ArrayList<>();

    public static final PressingRecipe.Serializer PRESSING_SERIALIZER = createRecipeSerializer("pressing", new PressingRecipe.Serializer());
    public static final AssemblingRecipe.Serializer ASSEMBLING_SERIALIZER = createRecipeSerializer("assembling", new AssemblingRecipe.Serializer());
    public static final AlloyingRecipe.Serializer ALLOYING_SERIALIZER = createRecipeSerializer("alloying", new AlloyingRecipe.Serializer());

    public static IRecipeType<PressingRecipe> PRESS_RECIPE = new PressingRecipe.PressRecipeType();
    public static IRecipeType<AssemblingRecipe> ASSEMBLING_RECIPE = new AssemblingRecipe.AssemblingRecipeType();
    public static IRecipeType<AlloyingRecipe> ALLOYING_RECIPE = new AlloyingRecipe.AlloyingRecipeType();

    public static <S extends IRecipeSerializer<T>, T extends IRecipe<?>> S createRecipeSerializer(String key, S recipeSerializer) {
        recipeSerializer.setRegistryName(new ResourceLocation(PhoenixTales.MOD_ID, key));
        serializers.add(recipeSerializer);
        return recipeSerializer;
    }

    public static void createPressRecipeTypes() {
        Registry.register(Registry.RECIPE_TYPE, PressingRecipe.TYPE_ID, PRESS_RECIPE);
        Registry.register(Registry.RECIPE_TYPE, AssemblingRecipe.TYPE_ID, ASSEMBLING_RECIPE);
        Registry.register(Registry.RECIPE_TYPE, AlloyingRecipe.TYPE_ID, ALLOYING_RECIPE);
    }
}
