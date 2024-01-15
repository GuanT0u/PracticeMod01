package pers.guant0u.practicemod4.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pers.guant0u.practicemod4.PracticeMod;
import pers.guant0u.practicemod4.block.IronBucketBlock;
import pers.guant0u.practicemod4.food.Food;
import pers.guant0u.practicemod4.items.MagicTeleportationStone;

public class ModItems{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PracticeMod.MODID);

    public static final RegistryObject<Item> MAGIC_STICK = ITEMS.register("magic_stick",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.PRACTICE_TAB)));

    //public static final RegistryObject<Item> MAGIC_STICK = ITEMS.register("magic_stick",
    //public 静态的 固定的(在后面的代码中不可调) 注册项目<物品> 物品名 = 物品.注册("物品ID",

    //        () -> new Item(new Item.Properties().tab(ModCreativeTabs.PRACTICE_TAB)));
    //        () -> 新 物品(新 物品.属性().物品栏(模组的创造物品栏.教程_物品栏)

    //        在原版中 .tab() 括号中应该是 CreativeModeTabs.TAB_XXX 其中 CreativeModeTabs 意为"创造 模式 物品栏", 在CreativeModeTabs.的 "."后面的内容代表着他是哪个物品栏的, 原版来讲TAB_MISC也就是杂项
    //        以原版的写法来讲. 假如我需要放到工具里: .tab(CreativeModeTabs.TAB_TOOLS), 后面我自己改为 .tab(ModCreativeTabs.PRACTICE_TAB) 是因为我自己注册了一个物品栏, 内容解析请参考 21 lines 的内容
    public static final RegistryObject<Item> MAGIC_TELEPORTATION_STONE = ITEMS.register("magic_teleportation_stone",
            () -> new MagicTeleportationStone(new Item.Properties().tab(ModCreativeTabs.PRACTICE_TAB)));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.PRACTICE_TAB)));
    public static final RegistryObject<Item> FERMENTED_WHEAT = ITEMS.register("fermented_wheat",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.PRACTICE_TAB)));

//    public static final RegistryObject<Item> LIQUOR = ITEMS.register("liquor",
//            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()).tab(ModCreativeTabs.PRACTICE_TAB)));
//    public static final RegistryObject<Item> LIQUOR = ITEMS.register("liquor",
//        () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).tab(ModCreativeTabs.PRACTICE_TAB).food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> LIQUOR = ITEMS.register("liquor",
            () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).tab(ModCreativeTabs.PRACTICE_TAB).food(Food.LIQUOR)));// 白酒

    public static final RegistryObject<Item> BLUE_BLADE = ITEMS.register("blue_blade",
            () -> new SwordItem(Tiers.IRON, 2, 1.5F, new Item.Properties().tab(ModCreativeTabs.PRACTICE_TAB)));

    // 这里有别与其他代码的地方在于 BlockItem(ModBlocks.SILVER_BLOCK.get()) 因为他是一个方块物品(注意! 这里指的并不是"方块"), 所以会有别于其他注册内容
    //                    意为: 方块物品(注册方块类的类名.方块.获取())
    public static final RegistryObject<Item> RAW_SILVER_BLOCK = ITEMS.register("silver_block",
            () -> new BlockItem(ModBlocks.SILVER_BLOCK.get(), new Item.Properties().tab(ModCreativeTabs.PRACTICE_TAB)));

    public static final RegistryObject<Item> RAW_IRON_BUCKET_BLOCK = ITEMS.register("iron_bucket_block",
            () -> new BlockItem(ModBlocks.IRON_BUCKET_BLOCK.get(), new Item.Properties().tab(ModCreativeTabs.PRACTICE_TAB)));
}
