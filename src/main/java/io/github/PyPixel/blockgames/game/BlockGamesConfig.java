package io.github.PyPixel.blockgames.game;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import xyz.nucleoid.plasmid.game.config.PlayerConfig;
import io.github.PyPixel.blockgames.game.map.BlockGamesMapConfig;

public class BlockGamesConfig {
    public static final Codec<BlockGamesConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            PlayerConfig.CODEC.fieldOf("players").forGetter(config -> config.playerConfig),
            BlockGamesMapConfig.CODEC.fieldOf("map").forGetter(config -> config.mapConfig),
            Codec.INT.fieldOf("time_limit_secs").forGetter(config -> config.timeLimitSecs)
    ).apply(instance, BlockGamesConfig::new));

    public final PlayerConfig playerConfig;
    public final BlockGamesMapConfig mapConfig;
    public final int timeLimitSecs;

    public BlockGamesConfig(PlayerConfig players, BlockGamesMapConfig mapConfig, int timeLimitSecs) {
        this.playerConfig = players;
        this.mapConfig = mapConfig;
        this.timeLimitSecs = timeLimitSecs;
    }
}
