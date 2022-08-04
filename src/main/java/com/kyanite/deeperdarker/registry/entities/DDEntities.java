package com.kyanite.deeperdarker.registry.entities;

import com.kyanite.deeperdarker.DeeperAndDarker;
import com.kyanite.deeperdarker.registry.entities.custom.SculkLeechEntity;
import com.kyanite.deeperdarker.registry.entities.custom.SculkSnapperEntity;
import com.kyanite.deeperdarker.registry.entities.custom.SculkWormEntity;
import com.kyanite.deeperdarker.registry.entities.custom.ShatteredEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DDEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DeeperAndDarker.MOD_ID);

    public static final RegistryObject<EntityType<SculkWormEntity>> SCULK_WORM = ENTITY_TYPES.register("shriek_worm", () -> EntityType.Builder.of(SculkWormEntity::new, MobCategory.MONSTER).sized(1.5f, 4.5F).clientTrackingRange(10).build(new ResourceLocation(DeeperAndDarker.MOD_ID, "shriek_worm").toString()));
    public static final RegistryObject<EntityType<SculkSnapperEntity>> SCULK_SNAPPER = ENTITY_TYPES.register("sculk_snapper", () -> EntityType.Builder.of(SculkSnapperEntity::new, MobCategory.MONSTER).sized(1, 1).build(new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_snapper").toString()));
    public static final RegistryObject<EntityType<SculkLeechEntity>> SCULK_LEECH = ENTITY_TYPES.register("sculk_leech", () -> EntityType.Builder.of(SculkLeechEntity::new, MobCategory.MONSTER).sized(0.f, 0.3f).build(new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_leech").toString()));
    public static final RegistryObject<EntityType<ShatteredEntity>> SHATTERED = ENTITY_TYPES.register("shattered", () -> EntityType.Builder.of(ShatteredEntity::new, MobCategory.MONSTER).sized(0.85f, 0.9f).build(new ResourceLocation(DeeperAndDarker.MOD_ID, "shattered").toString()));
}