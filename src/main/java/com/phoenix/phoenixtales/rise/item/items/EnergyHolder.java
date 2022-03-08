package com.phoenix.phoenixtales.rise.item.items;

import com.phoenix.phoenixtales.core.creativetab.ItemTab;
import com.phoenix.phoenixtales.rise.RiseItem;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//should the energy transfer be handled in the Block class or this item class
//if the block has a special interface, the energy could just be transferred, knowing the block can handle energy
//how do you get the target block?
//what is smarter?

public class EnergyHolder extends RiseItem {

    private boolean autocharge;
    private int energy;

    public EnergyHolder() {
        super(new Properties().group(ItemTab.ITEM_GROUP).maxStackSize(1));
        this.autocharge = false;
        this.energy = 0;
    }


    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return MathHelper.hsvToRGB(50, 100, 100);
//        return MathHelper.rgb(255, 214, 0);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.getTag().getBoolean("autocharge")) {
            tooltip.add(new TranslationTextComponent("tooltip.phoenixtales.energyholder.autocharge.on"));
        }else {
            tooltip.add(new TranslationTextComponent("tooltip.phoenixtales.energyholder.autocharge.off"));
        }
        String energyString = this.energy + " kJ";
        tooltip.add(new StringTextComponent(energyString));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return false;
    }

    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt) {
        return super.updateItemStackNBT(nbt);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getTag().getBoolean("autocharge");
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }
}
