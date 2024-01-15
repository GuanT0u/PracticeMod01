package pers.guant0u.practicemod4.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MagicTeleportationStone extends Item {
    public MagicTeleportationStone(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand useHand) {  // 定义参数 Level level 后面这个没有大写的就是我们自己取的名字，我们也可以叫别的，例如后面的useHand
        if(level.isClientSide()) {
            return super.use(level, player, useHand);
        }
        ServerLevel serverLevel = level.getServer().getLevel(Level.OVERWORLD);  // 定义一个名为serverLevel的变量, 内容是获得服务器的level, level为OVERWORLD
        // 服务器世界 变量名 = 世界.获得服务器().获得世界(世界.主世界)
        ServerPlayer serverPlayer;  // = player instanceof ServerPlayer ? (ServerPlayer) player : null;
        if(player instanceof ServerPlayer) {
            // 它使用了Java中的instanceof运算符来检查变量player是否属于ServerPlayer类或其子类的实例。如果player是ServerPlayer类的实例，那么条件表达式(player instanceof ServerPlayer)的结果为true，就会执行if语句块中的代码；否则，执行else语句块中的代码。
            serverPlayer = (ServerPlayer) player;
            // 将player强制类型转换为ServerPlayer类型，并赋值给serverPlayer变量。这是因为在代码中需要使用ServerPlayer特有的方法和属性，而Player类可能没有这些特有的方法和属性。通过这种强制类型转换，就可以确保在后续代码中可以安全地调用ServerPlayer类的方法和属性。
        }
        else {
            serverPlayer = null;
            // 将serverPlayer设置为null。这是为了在player不是ServerPlayer类的实例时，避免出现空指针异常。通过将serverPlayer设置为null，可以在后续代码中进行空指针检查，以确保程序不会因为serverPlayer为null而导致异常。
        }
        BlockPos respawnPos = serverPlayer.getRespawnPosition();
        // 方块位置 变量名 = serverPlayer变量.获取重生位置();
        if (serverPlayer != null && serverLevel != null) {  // 如果 severPlayer 不等于 空 并且 serverLevel 不等于 空
            player.teleportTo(respawnPos.getX(),respawnPos.getY(),respawnPos.getZ());
            // 玩家.传送至(重生位置.获取X坐标(),后面一样)
        }
        return InteractionResultHolder.success(player.getItemInHand(useHand));  // super .use(level, player, useHand)
    }
}
