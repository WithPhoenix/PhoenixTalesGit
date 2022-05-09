package com.phoenix.phoenixtales.rise.block.blocks.press;

import com.google.gson.JsonObject;
import com.phoenix.phoenixtales.rise.service.RiseRecipeTypes;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PressingRecipe implements IPressing {

    private final ResourceLocation id;
    private final Ingredient input;
    private final ItemStack output;
    private final int count;
    private final int processTime;
    private final int energy;

    public PressingRecipe(ResourceLocation id, Ingredient input, ItemStack output, int count, int processTime, int energy) {
        this.id = id;
        this.output = output;
        this.input = input;
        this.count = count;
        this.processTime = processTime;
        this.energy = energy;
    }


    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return input.test(inv.getStackInSlot(0));
    }

    public Ingredient getInput() {
        return input;
    }

    @Nonnull
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(getInput());
        return ingredients;
    }


    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return output;
    }

    @Nonnull
    @Override
    public ItemStack getRecipeOutput() {
        return output.copy();
    }

    public int getProcessingTime() {
        return this.processTime;
    }

    public int getCount() {
        return this.count;
    }

    public int neededEnergy() {
        return this.energy;
    }

    @Nonnull
    public ItemStack getIcon() {
        return new ItemStack(RiseBlocks.PRESS_FACTORY);
    }

    @Nonnull
    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RiseRecipeTypes.PRESSING_SERIALIZER;
    }

    public static class PressRecipeType implements IRecipeType<PressingRecipe> {
        @Override
        public String toString() {
            return PressingRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PressingRecipe> {

        @Override
        public PressingRecipe read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
            Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
            int count = JSONUtils.getInt(json, "count", 1);
            int processTime = JSONUtils.getInt(json, "processTime", 300);
            int energy = JSONUtils.getInt(json, "energy", 500);

            return new PressingRecipe(recipeId, input, output, count, processTime, energy);
        }

        @Nullable
        @Override
        public PressingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient input = Ingredient.read(buffer);
            ItemStack output = buffer.readItemStack();
            int count = buffer.readVarInt();
            int processTime = buffer.readVarInt();
            int energy = buffer.readVarInt();
            return new PressingRecipe(recipeId, input, output, count, processTime, energy);
        }

        @Override
        public void write(PacketBuffer buffer, PressingRecipe recipe) {
            for (Ingredient i : recipe.getIngredients()) {
                i.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
            buffer.writeInt(recipe.count);
            buffer.writeVarInt(recipe.processTime);
            buffer.writeVarInt(recipe.energy);
        }
    }
}
