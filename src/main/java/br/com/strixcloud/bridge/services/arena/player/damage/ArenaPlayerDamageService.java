package br.com.strixcloud.bridge.services.arena.player.damage;

import org.bukkit.event.entity.EntityDamageEvent;

public class ArenaPlayerDamageService {

    public void execute(ArenaPlayerDamageDTO data) {
        if (data.getEvent().getCause() == EntityDamageEvent.DamageCause.FALL) {
            data.getEvent().setCancelled(true);
        }
    }

}
