package com.phoenix.phoenixtales.rise.block.blocks.alloyfactory;

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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AlloyingRecipe implements IAlloying {

    //TODO custom count für die verschiedenen inputs, da legierungen verschiedene anteile haben

    private final ResourceLocation id;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;
    private final int count;
    private final int processTime;

    public AlloyingRecipe(ResourceLocation id, NonNullList<Ingredient> inputs, ItemStack output, int count, int processTime) {
        this.id = id;
        this.output = output;
        this.inputs = inputs;
        this.count = count;
        this.processTime = processTime;
    }


    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return inputs.get(0).test(inv.getStackInSlot(0)) && inputs.get(1).test(inv.getStackInSlot(1));
    }

    @Nonnull
    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputs;
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

    public int getProcessTime() {
        return this.processTime;
    }

    public int getCount() {
        return this.count;
    }

    @Nonnull
    public ItemStack getIcon() {
        return new ItemStack(RiseBlocks.ALLOY_FACTORY);
    }

    @Nonnull
    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RiseRecipeTypes.ALLOYING_SERIALIZER;
    }

    public static class AlloyingRecipeType implements IRecipeType<AlloyingRecipe> {
        @Override
        public String toString() {
            return AlloyingRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AlloyingRecipe> {

        @Override
        public AlloyingRecipe read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
            int count = JSONUtils.getInt(json, "count", 1);
            int processTime = JSONUtils.getInt(json, "processTime", 300);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.deserialize(ingredients.get(i)));
            }

            return new AlloyingRecipe(recipeId, inputs, output, count, processTime);
        }

        @Nullable
        @Override
        public AlloyingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.read(buffer));
            }
            ItemStack output = buffer.readItemStack();
            int count = buffer.readVarInt();
            int processTime = buffer.readVarInt();
            return new AlloyingRecipe(recipeId, inputs, output, count, processTime);
        }

        @Override
        public void write(PacketBuffer buffer, AlloyingRecipe recipe) {
            for (Ingredient i : recipe.getIngredients()) {
                i.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
            buffer.writeInt(recipe.count);
            buffer.writeVarInt(recipe.processTime);
        }
    }
}
