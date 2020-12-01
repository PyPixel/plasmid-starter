package io.github.PyPixel.blockgames;

import net.fabricmc.api.ModInitializer;
import xyz.nucleoid.plasmid.game.GameType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.github.PyPixel.blockgames.game.BlockGamesConfig;
import io.github.PyPixel.blockgames.game.BlockGamesWaiting;

public class BlockGames implements ModInitializer {

    public static final String ID = "blockgames";
    public static final Logger LOGGER = LogManager.getLogger(ID);

    public static final GameType<BlockGamesConfig> TYPE = GameType.register(
            new Identifier(ID, "blockgames"),
            BlockGamesWaiting::open,
            BlockGamesConfig.CODEC
    );

    @Override
    public void onInitialize() {}
}
