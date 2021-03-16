package br.com.strixcloud.bridge.bukkit.listener;

import br.com.strixcloud.bridge.services.arena.game.join.ArenaGameJoinController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onDamage(PlayerJoinEvent event) {
        ArenaGameJoinController.getInstance().handle(event);
    }

}
