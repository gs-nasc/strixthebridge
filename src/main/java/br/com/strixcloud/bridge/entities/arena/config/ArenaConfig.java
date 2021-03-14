package br.com.strixcloud.bridge.entities.arena.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;

import java.util.List;

@Data @RequiredArgsConstructor
public class ArenaConfig {

    private final int maxPlayers;
    private final int minPlayers;

    private final int winingScore;

    private final int pitRadius;
    private final int baseRadius;

    private final int respawnDelay;
    private final int arrowDelay;

    private final ArenaTeamConfig primaryTeamConfig;
    private final ArenaTeamConfig secondaryTeamConfig;

    /*
        Storage
     */

    private Location lobbyLocation;

    private Location topLocation;
    private Location downLocation;

    private List<Location> spawns;

}
