package br.com.strixcloud.bridge.services.arena.world.reset;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.provider.IWorldProvider;
import br.com.strixcloud.lib.entities.util.DateDuration;
import lombok.AllArgsConstructor;
import lombok.var;

@AllArgsConstructor
public class ArenaWorldResetService {

    private final IWorldProvider worldProvider;

    public void execute() {
        DateDuration duration = new DateDuration();
        worldProvider.resetBlocks();
        long ms = duration.calculate();
        var logger = StrixTheBridge.getInstance().getSLogger();
        logger.info(String.format("Successfully reseated arena region (%s ms)", ms));
    }

}
