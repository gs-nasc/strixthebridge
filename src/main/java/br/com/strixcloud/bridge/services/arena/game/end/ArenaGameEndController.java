package br.com.strixcloud.bridge.services.arena.game.end;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.entities.arena.ArenaTeam;
import lombok.Getter;

public class ArenaGameEndController {

    @Getter
    private static final ArenaGameEndController instance = new ArenaGameEndController();

    private final ArenaGameEndService service;

    public ArenaGameEndController() {
        service = new ArenaGameEndService(StrixTheBridge.getInstance().getArena());
    }

    public void handle(ArenaTeam winner, ArenaTeam looser) {
        service.execute(new ArenaGameEndDTO(winner, looser));
    }
    
}
