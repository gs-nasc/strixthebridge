package br.com.strixcloud.bridge.services.arena.game.point;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.Getter;
import org.bukkit.event.player.PlayerMoveEvent;

public class ArenaGamePointController {

    @Getter
    private static final ArenaGamePointController instance = new ArenaGamePointController();

    private final ArenaGamePointService service;

    public ArenaGamePointController() {
        service = new ArenaGamePointService(StrixTheBridge.getInstance().getArena());
    }

    public void handle(PlayerMoveEvent e) {
        service.execute(new ArenaGamePointDTO(e.getPlayer()));
    }

}
