package pers.guant0u.practicemod4.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pers.guant0u.practicemod4.PracticeMod;
import pers.guant0u.practicemod4.block.IronBucketBlock;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PracticeMod.MODID);

    public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS.register("silver_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> IRON_BUCKET_BLOCK = BLOCKS.register("iron_bucket_block",
            () -> new IronBucketBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
}
