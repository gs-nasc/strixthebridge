package br.com.strixcloud.bridge.bukkit.listener;

import br.com.strixcloud.bridge.services.arena.player.damage.ArenaPlayerDamageController;
import br.com.strixcloud.bridge.services.arena.player.pvp.ArenaPlayerPVPController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerPvPListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            ArenaPlayerPVPController.getInstance().handle(event);
        }
    }

}
