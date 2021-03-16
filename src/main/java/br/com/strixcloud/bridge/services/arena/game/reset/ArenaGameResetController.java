package br.com.strixcloud.bridge.services.arena.game.reset;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.Getter;

public class ArenaGameResetController {

    @Getter
    private static final ArenaGameResetController instance = new ArenaGameResetController();

    private final ArenaGameResetService service;

    public ArenaGameResetController() {
        service = new ArenaGameResetService(StrixTheBridge.getInstance().getArena());
    }

    public void handle() {
        service.execute();
    }
    
}
