package br.com.strixcloud.bridge.services.arena.player.prepare;

import br.com.strixcloud.bridge.StrixTheBridge;
import lombok.Getter;
import lombok.var;
import org.bukkit.entity.Player;

public class ArenaPlayerPrepareController {

    @Getter
    private static final ArenaPlayerPrepareController instance = new ArenaPlayerPrepareController();

    private final ArenaPlayerPrepareService service;

    public ArenaPlayerPrepareController() {
        var arena = StrixTheBridge.getInstance().getArena();
        service = new ArenaPlayerPrepareService(arena);
    }

    public void handle(Player p) {
        service.execute(new ArenaPlayerPrepareDTO(p));
    }
    
}
