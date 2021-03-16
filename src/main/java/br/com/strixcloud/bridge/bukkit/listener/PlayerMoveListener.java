package br.com.strixcloud.bridge.bukkit.listener;

import br.com.strixcloud.bridge.services.arena.game.point.ArenaGamePointController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onQuit(PlayerMoveEvent event) {
        ArenaGamePointController.getInstance().handle(event);
    }

}
