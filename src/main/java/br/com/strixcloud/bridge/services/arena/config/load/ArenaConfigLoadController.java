package br.com.strixcloud.bridge.services.arena.config.load;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.entities.arena.Arena;
import lombok.Getter;
import lombok.var;

public class ArenaConfigLoadController {

    @Getter
    private static final ArenaConfigLoadController instance = new ArenaConfigLoadController();

    private final ArenaConfigLoadService service;

    public ArenaConfigLoadController() {
        var configProvider = StrixTheBridge.getInstance().getArenaConfigProvider();
        var logger = StrixTheBridge.getInstance().getSLogger();
        var worldProvider = StrixTheBridge.getInstance().getWorldProvider();
        service = new ArenaConfigLoadService(logger, configProvider, worldProvider);
    }

    public Arena handle() {
        return service.execute();
    }

}
