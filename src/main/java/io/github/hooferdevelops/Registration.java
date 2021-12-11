package io.github.hooferdevelops;

import io.github.hooferdevelops.animals.FAKECOW;
import io.github.hooferdevelops.blocks.custom.ChemistryIncubator;
import io.github.hooferdevelops.blocks.custom.ChemistryIncubatorEntity;
import io.github.hooferdevelops.items.custom.GlassChemistryBottle;
import io.github.hooferdevelops.items.custom.MobEssenceItem;
import io.github.hooferdevelops.items.custom.RadioactiveGlassChemistryBottle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static io.github.hooferdevelops.chemicalevolution.ChemicalEvolution.MODID;

public class Registration {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    private static final DeferredRegister<EntityType<?>> ANIMALS = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);

    public static void init(){
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ANIMALS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<EntityType<FAKECOW>> COW_FAKE = ANIMALS.register(
            "fake_cow",
            () -> EntityType.Builder.of(FAKECOW::new, MobCategory.CREATURE).build(new ResourceLocation(MODID, "fake_cow").toString())
    );

    // ITEMS

    public static final RegistryObject<Item> GLASS_CHEMISTRY_BOTTLE = ITEMS.register(
            "glasschemistrybottle",
            () -> new GlassChemistryBottle(
                    new Item.Properties().tab(CreativeModeTab.TAB_BREWING)
            )
    );

    public static final RegistryObject<Item> RADIOACTIVE_GLASS_CHEMISTRY_BOTTLE = ITEMS.register(
            "radioactiveglasschemistrybottle",
            () -> new RadioactiveGlassChemistryBottle(
                    new Item.Properties().stacksTo(1).craftRemainder(GLASS_CHEMISTRY_BOTTLE.get()).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.POISON, 1200, 1), 1.0F).effect(new MobEffectInstance(MobEffects.HUNGER, 300, 2), 1.0F).effect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1.0F).build()).tab(CreativeModeTab.TAB_BREWING)
            )
    );

    public static final RegistryObject<Item> CURED_GLASS_CHEMISTRY_BOTTLE = ITEMS.register(
            "curedglasschemistrybottle",
            () -> new RadioactiveGlassChemistryBottle(
                    new Item.Properties().stacksTo(1).craftRemainder(GLASS_CHEMISTRY_BOTTLE.get()).food((new FoodProperties.Builder()).nutrition(4).saturationMod(3F).effect(new MobEffectInstance(MobEffects.CONFUSION, 15, 0), 1.0F).build()).tab(CreativeModeTab.TAB_BREWING)
            )
    );


    public static final RegistryObject<Item> MOB_ESSENCE = ITEMS.register(
            "mobessence",
            () -> new MobEssenceItem(
                    new Item.Properties().stacksTo(16).tab(CreativeModeTab.TAB_BREWING)
            )
    );

    // BLOCKS

    public static final RegistryObject<Block> CHEMISTRY_INCUBATOR = BLOCKS.register(
            "chemicalincubator",
            () -> new ChemistryIncubator(
                    BaseEntityBlock.Properties.of(Material.STONE).strength(6f).sound(SoundType.STONE).noOcclusion()
            )
    );

    public static final RegistryObject<BlockEntityType<ChemistryIncubatorEntity>> CHEMISTRY_INCUBATOR_ENTITY =
            BLOCK_ENTITIES.register(
            "chemistryincubatortile",
            () -> BlockEntityType.Builder.of(
                    ChemistryIncubatorEntity::new,
                    CHEMISTRY_INCUBATOR.get()
            ).build(null)
    );

    public static final RegistryObject<Item> CHEMISTRY_INCUBATOR_ITEM = ITEMS.register(
            "chemicalincubator",
            () -> new BlockItem(
                CHEMISTRY_INCUBATOR.get(),
                new Item.Properties().tab(CreativeModeTab.TAB_BREWING)
            )
    );



}
