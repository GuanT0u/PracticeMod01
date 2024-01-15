package pers.guant0u.practicemod4.registry;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pers.guant0u.practicemod4.PracticeMod;
import pers.guant0u.practicemod4.block.entity.IronBucketBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, PracticeMod.MODID);
    public static final RegistryObject<BlockEntityType<?>> IRON_BUCKET_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("iron_bucket", () ->
                    BlockEntityType.Builder.of(IronBucketBlockEntity::new,
                            ModBlocks.IRON_BUCKET_BLOCK.get()).build(null));
}
