package br.com.strixcloud.bridge.services.arena.config.save;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.Getter;
import lombok.var;

public class ArenaConfigSaveController {

    @Getter
    private static final ArenaConfigSaveController instance = new ArenaConfigSaveController();

    private final ArenaConfigSaveService service;

    public ArenaConfigSaveController() {
        var configProvider = StrixTheBridge.getInstance().getArenaConfigProvider();
        var logger = StrixTheBridge.getInstance().getSLogger();
        var worldProvider = StrixTheBridge.getInstance().getWorldProvider();
        service = new ArenaConfigSaveService(logger, worldProvider, configProvider);
    }

    public void handle() {
        service.execute(new ArenaConfigSaveDTO(StrixTheBridge.getInstance().getArena()));
    }

}
