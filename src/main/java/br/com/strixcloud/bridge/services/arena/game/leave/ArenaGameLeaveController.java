package br.com.strixcloud.bridge.services.arena.game.leave;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.Getter;
import org.bukkit.event.player.PlayerQuitEvent;

public class ArenaGameLeaveController {

    @Getter
    private static final ArenaGameLeaveController instance = new ArenaGameLeaveController();

    private final ArenaGameLeaveService service;

    public ArenaGameLeaveController() {
        service = new ArenaGameLeaveService(StrixTheBridge.getInstance().getArena());
    }

    public void handle(PlayerQuitEvent e) {
        service.execute(new ArenaGameLeaveDTO(e.getPlayer()));
    }

}
