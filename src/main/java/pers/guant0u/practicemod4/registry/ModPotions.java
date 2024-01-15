package pers.guant0u.practicemod4.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pers.guant0u.practicemod4.PracticeMod;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, PracticeMod.MODID);

    public static final RegistryObject<Potion> LIQUOR_POT = POTIONS.register("liquor_pot",
            () -> new Potion("liquor",
                    new MobEffectInstance(MobEffects.CONFUSION, 2400, 0),
                    new MobEffectInstance(MobEffects.DAMAGE_BOOST, 240, 1),
                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 240, 1)));
    public static final RegistryObject<Potion> LONG_LIQUOR = POTIONS.register("long_liquor",
            () -> new Potion("liquor",
                    new MobEffectInstance(MobEffects.CONFUSION, 2400, 0),
                    new MobEffectInstance(MobEffects.DAMAGE_BOOST, 400, 1),
                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 1)));

//    public static final RegistryObject<Potion> STRONG_LIQUOR = POTIONS.register("long_liquor",
//            () -> new Potion("liquor",
//                    new MobEffectInstance(MobEffects.CONFUSION, 2400, 0),
//                    new MobEffectInstance(MobEffects.DAMAGE_BOOST, 400, 1),
//                    new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 1)));

    public static void init()
    {
        ModPotionBrewing.modAddMix(Potions.AWKWARD, ModItems.FERMENTED_WHEAT.get(), LIQUOR_POT.get());
        ModPotionBrewing.modAddMix(LIQUOR_POT.get(), Items.REDSTONE, LONG_LIQUOR.get());
//        ModPotionBrewing.modAddMix(LIQUOR_POT.get(), Items.GLOWSTONE_DUST, STRONG_LIQUOR.get());
    }
}
