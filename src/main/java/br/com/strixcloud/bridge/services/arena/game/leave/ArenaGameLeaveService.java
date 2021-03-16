package br.com.strixcloud.bridge.services.arena.game.leave;

import br.com.strixcloud.bridge.entities.arena.Arena;
import br.com.strixcloud.bridge.services.account.create.AccountCreateController;
import br.com.strixcloud.bridge.services.arena.game.end.ArenaGameEndController;
import lombok.RequiredArgsConstructor;
import lombok.var;

@RequiredArgsConstructor
public class ArenaGameLeaveService {

    private final Arena arena;

    public void execute(ArenaGameLeaveDTO data) {
        var p = data.getPlayer();

        AccountCreateController.getInstance().handle(p);

        if (arena.isPlaying(p)) {

            var winner = arena.getEnemyTeam(p);
            var looser = arena.getTeam(p);

            ArenaGameEndController.getInstance().handle(winner, looser);
        }
    }

}
