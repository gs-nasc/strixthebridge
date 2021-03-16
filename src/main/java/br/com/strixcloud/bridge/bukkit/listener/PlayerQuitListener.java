package br.com.strixcloud.bridge.bukkit.listener;

import br.com.strixcloud.bridge.services.arena.game.leave.ArenaGameLeaveController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        ArenaGameLeaveController.getInstance().handle(event);
    }

}
