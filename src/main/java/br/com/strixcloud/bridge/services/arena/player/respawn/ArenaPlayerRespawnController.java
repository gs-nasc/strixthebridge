package br.com.strixcloud.bridge.services.arena.player.respawn;

import lombok.Getter;
import org.bukkit.entity.Player;

public class ArenaPlayerRespawnController {

    @Getter
    private static final ArenaPlayerRespawnController instance = new ArenaPlayerRespawnController();

    private final ArenaPlayerRespawnService service;

    public ArenaPlayerRespawnController() {
        service = new ArenaPlayerRespawnService();
    }

    public void handle(Player p) {
        service.execute(new ArenaPlayerRespawnDTO(p));
    }
    
}
