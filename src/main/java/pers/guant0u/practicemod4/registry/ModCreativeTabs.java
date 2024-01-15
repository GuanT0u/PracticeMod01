package pers.guant0u.practicemod4.registry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeModeTab PRACTICE_TAB = new CreativeModeTab("practicetab") {  // Creative Mode Tab -> 创造 模式 物品栏
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER_INGOT.get());  // 该创造物品栏的显示图标使用哪个物品
        }
    };
}
