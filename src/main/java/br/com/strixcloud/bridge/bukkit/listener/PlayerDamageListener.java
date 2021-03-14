package br.com.strixcloud.bridge.bukkit.listener;

import br.com.strixcloud.bridge.services.arena.player.damage.ArenaPlayerDamageController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            ArenaPlayerDamageController.getInstance().handle(event);
        }
    }

}
