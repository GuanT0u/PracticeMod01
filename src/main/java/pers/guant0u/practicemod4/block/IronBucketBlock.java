package pers.guant0u.practicemod4.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pers.guant0u.practicemod4.block.entity.IronBucketBlockEntity;

import java.util.Random;

public class IronBucketBlock extends BaseEntityBlock{
    private static final DirectionProperty FACING = BlockStateProperties.FACING;  // 定义朝向属性
    // 私有的 静态的 固定的
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;  // 定义开关属性

    public IronBucketBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(OPEN, false));  // 设置19 lines 和 21 lines 的属性默认值
    }  // 这个.注册默认状态(this.获取状态定义().任何().设置值(朝向属性, 方向.北).设置值(开关属性, 否))

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (level.isClientSide()){  // 判断客户端
            return InteractionResult.SUCCESS;  // 交互结果.成功
        }
        else {
            BlockEntity blockEntity = level.getBlockEntity(pos);  // 世界.获得方块实体(坐标)
            if (blockEntity instanceof IronBucketBlockEntity){  // 装换一下
                player.openMenu((IronBucketBlockEntity)blockEntity);  // 玩家打开方块实体为IronBucketBlock的Entity
                player.awardStat(Stats.OPEN_BARREL);  // 状态.打开_桶
                PiglinAi.angerNearbyPiglins(player, true);  // 猪灵AI.惹怒附件的猪灵(玩家, 是)
                // 50 51 lines 打开桶时是否激怒附近的猪灵
            }
        }
            return InteractionResult.CONSUME;  // 交互结果.
    }

    @Override  // on Remove 移除
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state2, boolean p_60519_) {
        if (state.is(state2.getBlock())){  // 获取方块状态
            BlockEntity blockEntity = level.getBlockEntity(pos);  // 世界.获得方块实体(坐标)
            if (blockEntity instanceof Container container)  // 如果是容器
                Containers.dropContents(level, pos, container);  // 容器.丢弃内容
            level.updateNeighbourForOutputSignal(pos, this);  // 更新
        }
        super.onRemove(state, level, pos, state2, p_60519_);  // 继承类
    }

    @Override  // tick 事件刻
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        BlockEntity blockEntity = level.getBlockEntity(pos);  // 世界.获得方块实体(坐标)
        if (blockEntity instanceof IronBucketBlockEntity ironBucketBlockEntity){  // 转换类型
            ironBucketBlockEntity.recheckOpen();  // 重新选中打开
        }
    }

    @Override  // 获取渲染模型
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;  // 渲染模型.model
    }

    @Override  // 创建/添加 方块状态定义
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN);  // 添加我们上面定义好的值
    }

    @Nullable
    @Override  // 获取放置状态
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());  // 调放置方块时方块的朝向。这里调的是我们朝向的反方向
        // return this.默认方块状态().设置值(朝向, context.获取看的方向().获取反方向)
    }


    /*
     * 以下内容关于结构方块如何兼容的
     */


    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new IronBucketBlockEntity(pos, state);
    }


}

