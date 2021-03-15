package br.com.strixcloud.bridge.services.arena.config.load;

import br.com.strixcloud.bridge.entities.arena.Arena;
import br.com.strixcloud.bridge.provider.IArenaConfigProvider;
import br.com.strixcloud.bridge.provider.IWorldProvider;
import br.com.strixcloud.lib.util.log.IStrixLogger;
import lombok.AllArgsConstructor;
import lombok.var;

@AllArgsConstructor
public class ArenaConfigLoadService {

    private final IStrixLogger logger;
    private final IArenaConfigProvider configProvider;
    private final IWorldProvider worldProvider;

    public Arena execute() {
        var arena = new Arena();

        var config = configProvider.getArenaConfig();
        arena.setConfig(config);

        var primaryConfig = configProvider.getTeamConfig("primary");
        var secondaryConfig = configProvider.getTeamConfig("secondary");

        arena.getPrimaryTeam().setConfig(primaryConfig);
        arena.getSecondaryTeam().setConfig(secondaryConfig);

        if (configProvider.hasStorage()) {
            arena.getConfig().setLobbyLocation(configProvider.getStoredLocation("lobby"));
            arena.getConfig().setTopLocation(configProvider.getStoredLocation("top"));
            arena.getConfig().setDownLocation(configProvider.getStoredLocation("down"));

            arena.getPrimaryTeam().getConfig().setPit(configProvider.getStoredLocation("primary.pit"));
            arena.getPrimaryTeam().getConfig().setSpawn(configProvider.getStoredLocation("primary.spawn"));

            arena.getSecondaryTeam().getConfig().setPit(configProvider.getStoredLocation("secondary.pit"));
            arena.getSecondaryTeam().getConfig().setSpawn(configProvider.getStoredLocation("secondary.spawn"));

            worldProvider.load(arena.getConfig().getTopLocation(), arena.getConfig().getDownLocation());
        } else {
            logger.warn("The arena has not stored locations configuration");
        }

        return arena;
    }

}
