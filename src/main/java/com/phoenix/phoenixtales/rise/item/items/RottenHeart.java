package com.phoenix.phoenixtales.rise.item.items;

import com.phoenix.phoenixtales.core.creativetab.TalesTabItem;
import com.phoenix.phoenixtales.fall.FallItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class RottenHeart extends FallItem {
    private final Random rand = new Random();


    public RottenHeart() {
        super(new Item.Properties().food(new Food.Builder().setAlwaysEdible()
                .effect(new EffectInstance(Effects.NAUSEA, 400, 4), 0.17F)
                .effect(new EffectInstance(Effects.POISON, 140, 1), 0.17F)
                .effect(new EffectInstance(Effects.HUNGER, 500, 1), 0.17F)
                .saturation(0.1F).hunger(1)
                .build()).group(TalesTabItem.TAB_ITEM));
    }

    @NotNull
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        int live_or_die = this.rand.nextInt(6) + 1;
        switch (live_or_die) {
            case 1:
                entityLiving.attackEntityFrom(DamageSource.STARVE, 3.0F);
                break;
            case 3:
                entityLiving.attackEntityFrom(DamageSource.STARVE, 9.0f);
                break;
            case 5:
                entityLiving.attackEntityFrom(DamageSource.STARVE, 19.0f);
                break;
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
//        if (Screen.hasControlDown()) {
//            tooltip.add(new TranslationTextComponent("tooltip.phoenixtech.rotten_heart_ctrl"));
//        } else {
        tooltip.add(new TranslationTextComponent("tooltip.phoenixtales.rotten_heart"));
//        }
        super.addInformation(stack, worldIn, tooltip, flagIn);


    }
}