package io.github.PyPixel.blockgames.game;

import net.minecraft.util.ActionResult;
import xyz.nucleoid.plasmid.game.*;
import xyz.nucleoid.plasmid.game.event.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import io.github.PyPixel.blockgames.game.map.BlockGamesMap;
import io.github.PyPixel.blockgames.game.map.BlockGamesMapGenerator;
import xyz.nucleoid.fantasy.BubbleWorldConfig;

public class BlockGamesWaiting {
    private final GameSpace gameSpace;
    private final BlockGamesMap map;
    private final BlockGamesConfig config;
    private final BlockGamesSpawnLogic spawnLogic;

    private BlockGamesWaiting(GameSpace gameSpace, BlockGamesMap map, BlockGamesConfig config) {
        this.gameSpace = gameSpace;
        this.map = map;
        this.config = config;
        this.spawnLogic = new BlockGamesSpawnLogic(gameSpace, map);
    }

    public static GameOpenProcedure open(GameOpenContext<BlockGamesConfig> context) {
        BlockGamesConfig config = context.getConfig();
        BlockGamesMapGenerator generator = new BlockGamesMapGenerator(config.mapConfig);
        BlockGamesMap map = generator.build();

        BubbleWorldConfig worldConfig = new BubbleWorldConfig()
                .setGenerator(map.asGenerator(context.getServer()))
                .setDefaultGameMode(GameMode.SPECTATOR);

        return context.createOpenProcedure(worldConfig, game -> {
            BlockGamesWaiting waiting = new BlockGamesWaiting(game.getSpace(), map, context.getConfig());

            GameWaitingLobby.applyTo(game, config.playerConfig);

            game.on(RequestStartListener.EVENT, waiting::requestStart);
            game.on(PlayerAddListener.EVENT, waiting::addPlayer);
            game.on(PlayerDeathListener.EVENT, waiting::onPlayerDeath);
        });
    }

    private StartResult requestStart() {
        BlockGamesActive.open(this.gameSpace, this.map, this.config);
        return StartResult.OK;
    }

    private void addPlayer(ServerPlayerEntity player) {
        this.spawnPlayer(player);
    }

    private ActionResult onPlayerDeath(ServerPlayerEntity player, DamageSource source) {
        player.setHealth(20.0f);
        this.spawnPlayer(player);
        return ActionResult.FAIL;
    }

    private void spawnPlayer(ServerPlayerEntity player) {
        this.spawnLogic.resetPlayer(player, GameMode.ADVENTURE);
        this.spawnLogic.spawnPlayer(player);
    }
}
