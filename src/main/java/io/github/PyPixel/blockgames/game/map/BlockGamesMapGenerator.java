package io.github.PyPixel.blockgames.game.map;

import xyz.nucleoid.plasmid.map.template.MapTemplate;
import xyz.nucleoid.plasmid.util.BlockBounds;
import net.minecraft.block.Blocks;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import io.github.PyPixel.blockgames.game.BlockGamesConfig;

import java.util.concurrent.CompletableFuture;

public class BlockGamesMapGenerator {

    private final BlockGamesMapConfig config;

    public BlockGamesMapGenerator(BlockGamesMapConfig config) {
        this.config = config;
    }

    public BlockGamesMap build() {
        MapTemplate template = MapTemplate.createEmpty();
        BlockGamesMap map = new BlockGamesMap(template, this.config);

        this.buildSpawn(template);
        map.spawn = new BlockPos(0,65,0);

        return map;
    }

    private void buildSpawn(MapTemplate builder) {
        BlockPos min = new BlockPos(-5, 64, -5);
        BlockPos max = new BlockPos(5, 64, 5);

        for (BlockPos pos : BlockPos.iterate(min, max)) {
            builder.setBlockState(pos, this.config.spawnBlock);
        }
    }
}
