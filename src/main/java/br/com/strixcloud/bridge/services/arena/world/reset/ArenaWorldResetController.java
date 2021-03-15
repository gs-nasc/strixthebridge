package br.com.strixcloud.bridge.services.arena.world.reset;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.Getter;
import lombok.var;

public class ArenaWorldResetController {

    @Getter
    private static final ArenaWorldResetController instance = new ArenaWorldResetController();

    private final ArenaWorldResetService service;

    public ArenaWorldResetController() {
        var logger = StrixTheBridge.getInstance().getSLogger();
        var worldProvider = StrixTheBridge.getInstance().getWorldProvider();
        service = new ArenaWorldResetService(worldProvider);
    }

    public void handle() {
        service.execute();
    }

}
