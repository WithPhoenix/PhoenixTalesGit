package com.phoenix.phoenixtales.origins.entity.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class WraithEntity extends MonsterEntity {
    private static final DataParameter<Boolean> RAGED = EntityDataManager.createKey(WraithEntity.class, DataSerializers.BOOLEAN);

    public WraithEntity(EntityType<? extends WraithEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.0F;
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.experienceValue = 3;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
//        this.goalSelector.addGoal(1, new WraithEntity.RagedAttackGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 3.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 2.0D));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.addGoal(9, new LookAtGoal(this, LivingEntity.class, 10.0F));
        this.goalSelector.addGoal(10, new RandomWalkingGoal(this, 0.2D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, false, false, (target) -> !(target instanceof WraithEntity)));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.MAX_HEALTH, 60.0D).createMutableAttribute(Attributes.ATTACK_DAMAGE, 16.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3F).createMutableAttribute(Attributes.FOLLOW_RANGE, 80.0D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(RAGED, false);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return super.getAmbientSound();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_LAVA_EXTINGUISH;
    }

    @Override
    protected boolean isDespawnPeaceful() {
        return false;
    }

    @Override
    public void livingTick() {
        super.livingTick();
    }

    private boolean isRaged() {
        return (this.dataManager.get(RAGED));
    }

    private void setRaged(boolean raged) {
        this.dataManager.set(RAGED, raged);
    }

    static class RagedAttackGoal extends Goal {
        private final WraithEntity wraith;

        public RagedAttackGoal(WraithEntity wraithIn) {
            this.wraith = wraithIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean shouldExecute() {
            return false;
        }
    }

}
