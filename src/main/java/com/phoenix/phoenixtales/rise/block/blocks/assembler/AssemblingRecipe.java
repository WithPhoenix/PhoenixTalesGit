package com.phoenix.phoenixtales.rise.block.blocks.assembler;

import com.google.gson.JsonArray;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class AssemblingRecipe implements IAssembling {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> ingredients;
    private final int processTime;
    private final int count;
    private final int energy;

    public AssemblingRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> ingredients, int processTime, int count, int energy) {
        this.id = id;
        this.output = output;
        this.ingredients = ingredients;
        this.processTime = processTime;
        this.count = count;
        this.energy = energy;
    }

    @Override
    public boolean matches(IInventory inv, @NotNull World worldIn) {
        return ingredients.get(0).test(inv.getStackInSlot(0)) &&
                ingredients.get(1).test(inv.getStackInSlot(1)) &&
                ingredients.get(2).test(inv.getStackInSlot(2)) &&
                ingredients.get(3).test(inv.getStackInSlot(3)) &&
                ingredients.get(4).test(inv.getStackInSlot(4));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return output;
    }

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

    public ItemStack getIcon() {
        return new ItemStack(RiseBlocks.ASSEMBLER);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RiseRecipeTypes.ASSEMBLING_SERIALIZER;
    }

    public static class AssemblingRecipeType implements IRecipeType<AssemblingRecipe> {
        @Override
        public String toString() {
            return AssemblingRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AssemblingRecipe> {

        @Override
        public AssemblingRecipe read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(5, Ingredient.EMPTY);
            int processTime = JSONUtils.getInt(json, "processTime", 300);
            int count = JSONUtils.getInt(json, "count", 1);
            int energy = JSONUtils.getInt(json, "energy", 500);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.deserialize(ingredients.get(i)));
            }

            return new AssemblingRecipe(recipeId, output, inputs, processTime, count, energy);
        }

        @Nullable
        @Override
        public AssemblingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(5, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.read(buffer));
            }
            ItemStack output = buffer.readItemStack();
            int processTime = buffer.readVarInt();
            int count = buffer.readVarInt();
            int energy = buffer.readVarInt();
            return new AssemblingRecipe(recipeId, output, inputs, processTime, count, energy);
        }

        @Override
        public void write(PacketBuffer buffer, AssemblingRecipe recipe) {
            for (Ingredient i : recipe.getIngredients()) {
                i.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
            buffer.writeVarInt(recipe.processTime);
            buffer.writeInt(recipe.count);
            buffer.writeVarInt(recipe.energy);
        }
    }
}
