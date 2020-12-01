package io.github.PyPixel.blockgames.game.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import xyz.nucleoid.plasmid.map.template.MapTemplate;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class BlockGamesMapConfig {
    public static final Codec<BlockGamesMapConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockState.CODEC.fieldOf("spawn_block").forGetter(map -> map.spawnBlock)
    ).apply(instance, BlockGamesMapConfig::new));

    public final BlockState spawnBlock;

    public BlockGamesMapConfig(BlockState spawnBlock) {
        this.spawnBlock = spawnBlock;
    }
}
