package com.phoenix.phoenixtales.rise.item.items;

import com.phoenix.phoenixtales.core.creativetab.ItemTab;
import com.phoenix.phoenixtales.rise.RiseItem;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//should the energy transfer be handled in the Block class or this item class
//if the block has a special interface, the energy could just be transferred, knowing the block can handle energy
//how do you get the target block?
//what is smarter?

public class EnergyHolder extends RiseItem {

    //should this be handed with  nbt instead of local variables
    //TODO how much energy will be transferred in a single click

    public EnergyHolder() {
        super(new Properties().group(ItemTab.ITEM_GROUP).maxStackSize(1));
        updateTags(this.getDefaultInstance(), 0d, 120d, false);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return this.getEnergy(stack) / this.getMaxEnergy(stack);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return MathHelper.hsvToRGB(50, 100, 100);
//        return MathHelper.rgb(255, 214, 0);
    }

//    @Override
//    public ActionResultType onItemUse(ItemUseContext context) {
//
//        return super.onItemUse(context);
//    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.isCrouching()) {
            playerIn.sendMessage(new StringTextComponent("fafdfsggs"), playerIn.getUniqueID());
            ItemStack stack = playerIn.getHeldItem(handIn).getStack();
            this.setEnergy(stack, 40d);
            boolean b = this.isAutoCharge(stack);
            this.setAutoCharge(stack, !isAutoCharge(stack));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (this.isAutoCharge(stack)) {
            tooltip.add(new TranslationTextComponent("tooltip.phoenixtales.energyholder.autocharge.on"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.phoenixtales.energyholder.autocharge.off"));
        }

        String energyString = this.getEnergy(stack) + " kJ/ " + this.getEnergy(stack) + " kJ";
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
        return this.isAutoCharge(stack);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }


    public void updateTags(ItemStack stack, double energy, double maxEnergy, boolean autocharge) {
        if (stack.getTag() != null) {
            stack.getTag().putDouble("energy", energy);
            stack.getTag().putDouble("maxenergy", maxEnergy);
            stack.getTag().putBoolean("autocharge", autocharge);
        }

    }

    public double getEnergy(ItemStack stack) {
        if (stack.getTag() != null) {
            return stack.getTag().getDouble("energy");
        }
        return 0d;
    }

    public void setEnergy(ItemStack stack, double energy) {
        if (stack.getTag() != null) {
            stack.getTag().putDouble("energy", energy);
        }
    }

    public double getMaxEnergy(ItemStack stack) {
        if (stack.getTag() != null) {
            return stack.getTag().getDouble("maxenergy");
        }
        return 0d;
    }

    public boolean isAutoCharge(ItemStack stack) {
        if (stack.getTag() != null) {
            return stack.getTag().getBoolean("autocharge");
        }
        return false;
    }

    public void setAutoCharge(ItemStack stack, boolean autocharge) {
        if (stack.getTag() != null) {
            stack.getTag().putBoolean("autocharge", autocharge);
        }
    }

}
