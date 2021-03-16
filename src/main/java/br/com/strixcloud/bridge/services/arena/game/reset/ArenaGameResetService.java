package br.com.strixcloud.bridge.services.arena.game.reset;

import br.com.strixcloud.bridge.entities.arena.Arena;
import br.com.strixcloud.bridge.entities.arena.utils.ArenaStatus;
import br.com.strixcloud.bridge.services.arena.game.start.ArenaGameStartController;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArenaGameResetService {

    private final Arena arena;

    public void execute() {
        ArenaGameStartController.getInstance().handle();
        arena.setStatus(ArenaStatus.IN_PROGRESS);
    }

}
