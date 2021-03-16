package br.com.strixcloud.bridge.services.arena.player.respawn;

import br.com.strixcloud.bridge.services.arena.player.prepare.ArenaPlayerPrepareController;
import lombok.var;

public class ArenaPlayerRespawnService {

    public void execute(ArenaPlayerRespawnDTO data) {
        var p = data.getPlayer();

        ArenaPlayerPrepareController.getInstance().handle(p);
    }

}
