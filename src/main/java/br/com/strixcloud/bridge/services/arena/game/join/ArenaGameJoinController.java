package br.com.strixcloud.bridge.services.arena.game.join;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.Getter;
import org.bukkit.event.player.PlayerJoinEvent;

public class ArenaGameJoinController {

    @Getter
    private static final ArenaGameJoinController instance = new ArenaGameJoinController();

    private final ArenaGameJoinService service;

    public ArenaGameJoinController() {
        service = new ArenaGameJoinService(StrixTheBridge.getInstance().getArena());
    }

    public void handle(PlayerJoinEvent e) {
        service.execute(new ArenaGameJoinDTO(e.getPlayer()));
    }

}
