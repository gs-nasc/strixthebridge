package br.com.strixcloud.bridge.services.arena.game.start;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.Getter;

public class ArenaGameStartController {

    @Getter
    private static final ArenaGameStartController instance = new ArenaGameStartController();

    private final ArenaGameStartService service;

    public ArenaGameStartController() {
        service = new ArenaGameStartService(StrixTheBridge.getInstance().getArena());
    }

    public void handle() {
        service.execute();
    }
    
}
