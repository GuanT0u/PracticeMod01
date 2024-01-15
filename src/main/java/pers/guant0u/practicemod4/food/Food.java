package pers.guant0u.practicemod4.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class Food {
    public static final FoodProperties LIQUOR = (new FoodProperties.Builder()).nutrition(2).saturationMod(1.0F)
            .effect(new MobEffectInstance(MobEffects.CONFUSION, 2400, 0), 0.3F)  // 反胃
            // new MobEffectInstance(MobEffects.CONFUSION, 2400, 0), 0.3F
            // 新 生物体效果(实体效果.反胃, 2400, 0), 0.3F  2400为tick （每 tick 大约相当于 1/20 秒），0为效果等级 0是默认，0.3F为实体获得效果的概率 这里是30% 1F为100%
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 800, 1), 1.0F)  // 伤害提升
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 800, 1), 1.0F).alwaysEat().build();  // 伤害减免; .alwaysEat()任何情况下都可以食用
}
