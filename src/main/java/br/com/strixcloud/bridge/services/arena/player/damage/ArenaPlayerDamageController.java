package br.com.strixcloud.bridge.services.arena.player.damage;

import lombok.Getter;
import org.bukkit.event.entity.EntityDamageEvent;

public class ArenaPlayerDamageController {

    @Getter
    private static final ArenaPlayerDamageController instance = new ArenaPlayerDamageController();

    private final ArenaPlayerDamageService service;

    public ArenaPlayerDamageController() {
        service = new ArenaPlayerDamageService();
    }

    public void handle(EntityDamageEvent e) {
        service.execute(new ArenaPlayerDamageDTO(e));
    }
}
