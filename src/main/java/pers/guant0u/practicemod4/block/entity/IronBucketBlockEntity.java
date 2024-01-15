package pers.guant0u.practicemod4.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.lwjgl.system.CallbackI;
import pers.guant0u.practicemod4.block.IronBucketBlock;
import pers.guant0u.practicemod4.registry.ModBlockEntities;

public class IronBucketBlockEntity extends RandomizableContainerBlockEntity {

//    public static final int SIZE = 27;

    private NonNullList<ItemStack> items = NonNullList.withSize(54, ItemStack.EMPTY);  // 创建一个大小为54 且全部为空的列表

    private ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {  // 新建一个容器
        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState state) {  // 设置打开时的参数
            level.setBlock(pos, state.setValue(IronBucketBlock.OPEN, true), 3);
        }

        @Override
        protected void onClose(Level level, BlockPos pos, BlockState state) {  // 设置关闭时的参数
            level.setBlock(pos, state.setValue(IronBucketBlock.OPEN, false), 3);
        }

        @Override
        protected void openerCountChanged(Level p_155463_, BlockPos p_155464_, BlockState p_155465_, int p_155466_, int p_155467_) {

        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof ChestMenu){
                // 玩家.容器菜单 转换 chest菜单
                Container container = ((ChestMenu) player.containerMenu).getContainer();
                // 容器 容器 = ((chest菜单) 玩家.容器菜单).获取容器();
                return container == IronBucketBlockEntity.this;
                //  容器 == IronBucketBlockEntity.this;
            }
            else {
                return false;
            }
        }
    };

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {  // 保存物品
        super.saveAdditional(compoundTag);
        if (!this.trySaveLootTable(compoundTag)){  // 尝试保存战利品表
            ContainerHelper.saveAllItems(compoundTag, this.items);  // 保存全部物品
        }
    }

    @Override
    public void load(CompoundTag tag) {  // 加载
        super.load(tag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);  // 加载的范围?
        if (!this.trySaveLootTable(tag)){  // 尝试保存战利品表
            ContainerHelper.loadAllItems(tag, this.items);  // 加载全部物品
        }
    }

    public IronBucketBlockEntity(BlockPos p_155630_, BlockState p_155631_) {
        super(ModBlockEntities.IRON_BUCKET_BLOCK_ENTITY.get(), p_155630_, p_155631_);
    }

    @Override
    public void startOpen(Player player){  // 启用打开
        if (!this.remove && !player.isSpectator()){  // 旁观者无法打开?
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player){  // 关闭打开
        if (!this.remove && !player.isSpectator()){  // 旁观者无法打开?
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void recheckOpen(){  // 重新选中“打开”
        if (!this.remove){
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected Component getDefaultName() {  // 默认名称(打开容器时左上角的容器名)
        return new TextComponent("block.practicemod.iron_bucket_block");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {  // 新建菜单
        return ChestMenu.sixRows(p_58627_, p_58628_, this);  // ChestMenu.sixRows, 这里的sixRows是关键，意为六行，这里的参数是多少，游戏内的容器大小就是多少行.
    }

    @Override
    public int getContainerSize() {  // 容器大小
        return 54;
    }
}
