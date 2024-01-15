package pers.guant0u.practicemod4.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTires {
    public static final ForgeTier SILVER = new ForgeTier(2, 1400, 1.5F,
            2f, 22, BlockTags.NEEDS_IRON_TOOL,
            () -> Ingredient.of(ModItems.SILVER_INGOT.get()));
}
