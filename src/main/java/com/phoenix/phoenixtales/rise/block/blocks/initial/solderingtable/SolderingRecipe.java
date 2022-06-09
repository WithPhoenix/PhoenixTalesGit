package com.phoenix.phoenixtales.rise.block.blocks.initial.solderingtable;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.phoenix.phoenixtales.rise.block.RiseBlocks;
import com.phoenix.phoenixtales.rise.service.RiseRecipeTypes;
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

import javax.annotation.Nullable;

public class SolderingRecipe implements ISoldering {
    private final ResourceLocation id;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack output;
    private final int time;
    private final float chanceToFail;

    public SolderingRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, ItemStack output, int time, float chanceToFail) {
        this.id = id;
        this.ingredients = ingredients;
        this.output = output;
        this.time = time;
        this.chanceToFail = chanceToFail;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        for (int i = 0; i < 4; i++) {
            if (!(this.ingredients.get(i).test(inv.getStackInSlot(i)))) return false;
        }
//        boolean[] b = {false, false, false, false};
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; i < 4; i++) {
//                if (this.ingredients.get(i).test(inv.getStackInSlot(j))) {
//                    b[i] = true;
//                }
//            }
//        }
//        for (boolean b1 : b) {
//            if (!b1) return false;
//        }
        return true;
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

    public int getTime() {
        return time;
    }

    public float getChanceToFail() {
        return chanceToFail;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(RiseBlocks.SOLDERING_TABLE);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RiseRecipeTypes.SOLDERING_SERIALIZER;
    }

    public static class SolderingRecipeType implements IRecipeType<SolderingRecipe> {
        @Override
        public String toString() {
            return SolderingRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SolderingRecipe> {

        @Override
        public SolderingRecipe read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);
            int time = JSONUtils.getInt(json, "time", 200);
            float chanceToFail = JSONUtils.getFloat(json, "chanceToFail", 0.15f);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.deserialize(ingredients.get(i)));
            }

            return new SolderingRecipe(recipeId, inputs, output, time, chanceToFail);
        }

        @Nullable
        @Override
        public SolderingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.read(buffer));
            }
            ItemStack output = buffer.readItemStack();
            int time = buffer.readVarInt();
            float chanceToFail = buffer.readFloat();
            return new SolderingRecipe(recipeId, inputs, output, time, chanceToFail);
        }

        @Override
        public void write(PacketBuffer buffer, SolderingRecipe recipe) {
            for (Ingredient i : recipe.getIngredients()) {
                i.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
            buffer.writeVarInt(recipe.time);
            buffer.writeFloat(recipe.chanceToFail);
        }
    }
}
