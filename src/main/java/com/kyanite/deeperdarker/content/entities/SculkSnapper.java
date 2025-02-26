package com.kyanite.deeperdarker.content.entities;

import com.kyanite.deeperdarker.content.DDSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("NullableProblems")
public class SculkSnapper extends TamableAnimal {
    private static final EntityDataAccessor<Integer> SNIFF_COUNTER = SynchedEntityData.defineId(SculkSnapper.class, EntityDataSerializers.INT);
    public final AnimationState idleState = new AnimationState();
    public final AnimationState attackState = new AnimationState();
    public final AnimationState sniffState = new AnimationState();
    public final AnimationState digState = new AnimationState();
    public final AnimationState emergeState = new AnimationState();
    private int idleTimeout;
    private BlockPos targetPos;

    public SculkSnapper(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.1, true));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 0.9, 12, 2, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.5));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 7));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean isInvulnerableTo(DamageSource pSource) {
        return (this.hasPose(Pose.DIGGING) || this.hasPose(Pose.EMERGING)) && !pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY) || super.isInvulnerableTo(pSource);
    }

    public static AttributeSupplier createAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 12).add(Attributes.ATTACK_DAMAGE, 3).add(Attributes.MOVEMENT_SPEED, 0.3).build();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return DDSounds.SNAPPER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return DDSounds.SNAPPER_HURT.get();
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        this.level().broadcastEntityEvent(this, (byte) 4);
        this.playSound(DDSounds.SNAPPER_BITE.get());
        return super.doHurtTarget(pEntity);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SNIFF_COUNTER, getRandom().nextInt(180, 400));
    }

    @Override
    public void tick() {
        super.tick();

        if(level().isClientSide()) {
            if(this.idleTimeout <= 0) {
                this.idleTimeout = this.random.nextInt(40, 120);
                this.idleState.start(this.tickCount);
            } else {
                this.idleTimeout--;
            }

            if(!this.isTame()) {
                this.entityData.set(SNIFF_COUNTER, this.entityData.get(SNIFF_COUNTER) - 1);
                if(this.entityData.get(SNIFF_COUNTER) % 20 == 0) System.out.println("sniff == " + this.entityData.get(SNIFF_COUNTER) / 20);

                if(this.entityData.get(SNIFF_COUNTER) == 0) {
                    System.out.println("sniffing");
                    playSound(DDSounds.SNAPPER_SNIFF.get());
                    this.idleState.stop();
                    this.sniffState.start(this.tickCount);
                }

                if(this.entityData.get(SNIFF_COUNTER) < -31) {
                    this.entityData.set(SNIFF_COUNTER, getRandom().nextInt(180, 400));
                    if(findTarget()) {
                        System.out.println(targetPos);
                        this.digState.start(this.tickCount);
                    }
                }
            }
        }
    }

    private boolean findTarget() {
        Player target = level().getNearestPlayer(this, 30);
        if(target == null || target.isDeadOrDying() || target.isCreative()) {
            return false;
        }

        setTarget(target);
        Vec3 lookAngle = getTarget().getLookAngle();
        this.targetPos = new BlockPos((int) (lookAngle.x * 2.5 + getTarget().position().x), (int) (lookAngle.y * 2.5 + getTarget().position().y), (int) (lookAngle.z * 2.5 + getTarget().position().z));
        return true;
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if(pId == 4) {
            this.idleState.stop();
            this.attackState.start(this.tickCount);
        } else {
            super.handleEntityEvent(pId);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }
}
