package br.com.strixcloud.bridge.services.arena.config.save;

import br.com.strixcloud.bridge.provider.IArenaConfigProvider;
import br.com.strixcloud.bridge.provider.IWorldProvider;
import br.com.strixcloud.lib.entities.util.DateDuration;
import br.com.strixcloud.lib.util.log.IStrixLogger;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArenaConfigSaveService {

    private final IStrixLogger logger;
    private final IWorldProvider worldProvider;
    private final IArenaConfigProvider configProvider;

    public void execute(ArenaConfigSaveDTO data) {
        DateDuration duration = new DateDuration();
        configProvider.saveStorage(data.getArena().getConfig());

        worldProvider.load(data.getArena().getConfig().getTopLocation(),
                data.getArena().getConfig().getDownLocation());

        long ms = duration.calculate();
        logger.info(String.format("Successfully saved config & loaded arena world data (%s ms)", ms));
    }

}
