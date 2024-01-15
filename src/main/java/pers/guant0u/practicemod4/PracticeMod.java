package pers.guant0u.practicemod4;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import pers.guant0u.practicemod4.registry.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import pers.guant0u.practicemod4.registry.ModItems;

import java.util.stream.Collectors;

import static net.minecraft.world.item.Items.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("practice_mod")
@Mod.EventBusSubscriber
public class PracticeMod
{
    public static final String MODID = "practicemod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public PracticeMod()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

//        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
//
//        modEventBus.addListener(this::commonStep);

        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModPotions.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlockEntities.BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

//    private void commonStep(final FMLCommonSetupEvent event)
//    {
//        // some common setup code
//        LOGGER.info("HELLO FROM COMMON SETUP");
//        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
//    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("practice_mod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
    // 右键指定Item传送至准星坐标
    @SubscribeEvent
    public static void rightClickMagicStick(PlayerInteractEvent.RightClickItem event) {
        // PlayerInteractEvent.RightClickItem
        // 玩家互动事件.右键点击物品
        Player player = event.getPlayer();  // 获取玩家
        Level level = player.level;
        if (!level.isClientSide()) {  // 用于检查当前的 游戏世界 是否在 客户端 运行而不是 服务器端
            // 世界.是否在客户端运行
            // 返回true:客户端，返回false:服务端
            ItemStack itemStack = event.getItemStack();  // 获取所选的 物品栏
            // 物品栏 名 = 事件.获取物品栏
            Item item = itemStack.getItem();  // 获取所选的 物品栏 里面的 物品
            // 物品 名 = 物品栏.获取物品
            if (item == ModItems.MAGIC_STICK.get()) {  // 如果物品为钻石_剑
                HitResult hitResult = player.pick(7, 0, false);
                // 命中结果(?) 名 = 玩家.pick类(距离:7, 0, 液体墙(?):false)   <?1> 指的应该是瞄准的位置，<?2>指的应该是瞄准液体时，是否穿透，穿透即为false
                Vec3 location = hitResult.getLocation();  // 获取 命中结果 的坐标
                // Vec3方法 名 = 命中结果.获取位置
                player.teleportTo(location.x, location.y, location.z);
                // player对象.传送至(location)
            }
        }
    }
}
