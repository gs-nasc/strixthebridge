package br.com.strixcloud.bridge.entities.arena.config;

import lombok.Data;
import org.bukkit.Location;

@Data
public class ArenaConfig {

    private final int maxPlayers;
    private final int minPlayers;

    private final int winingScore;

    private final int respawnDelay;
    private final int arrowDelay;

    /*
        Storage
     */

    private Location lobbyLocation;

    private Location topLocation;
    private Location downLocation;

    private Location[] spawns;

}
