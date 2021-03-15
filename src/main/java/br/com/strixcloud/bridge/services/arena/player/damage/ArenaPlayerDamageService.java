package br.com.strixcloud.bridge.services.arena.player.damage;

import lombok.var;

public class ArenaPlayerDamageService {

    public void execute(ArenaPlayerDamageDTO data) {
        var event = data.getEvent();
        switch (event.getCause()) {
            case FALL: {
                event.setCancelled(true);
                return;
            }
            case VOID: {
                event.setCancelled(true);
            }
        }
    }

}
