package br.com.strixcloud.bridge.services.arena.player.pvp;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class ArenaPlayerPVPController {

    @Getter
    private static final ArenaPlayerPVPController instance = new ArenaPlayerPVPController();

    private final ArenaPlayerPVPService service;

    public ArenaPlayerPVPController() {
        service = new ArenaPlayerPVPService();
    }

    public void handle(EntityDamageByEntityEvent e) {
        service.execute(new ArenaPlayerPVPDTO((Player) e.getDamager(), (Player) e.getEntity(), e.getFinalDamage()));
    }
}
