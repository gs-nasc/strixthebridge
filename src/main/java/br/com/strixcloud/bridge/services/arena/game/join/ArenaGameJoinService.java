package br.com.strixcloud.bridge.services.arena.game.join;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.data.AccountsDAO;
import br.com.strixcloud.bridge.entities.arena.Arena;
import br.com.strixcloud.bridge.entities.arena.utils.ArenaStatus;
import br.com.strixcloud.bridge.services.account.create.AccountCreateController;
import br.com.strixcloud.bridge.task.VerifyPlayersTask;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.bukkit.GameMode;

@RequiredArgsConstructor
public class ArenaGameJoinService {

    private final Arena arena;

    public void execute(ArenaGameJoinDTO data) {
        var p = data.getPlayer();

        AccountCreateController.getInstance().handle(p);

        var acc = AccountsDAO.getInstance().get(p.getUniqueId().toString());

        p.getInventory().clear();
        if (arena.getStatus() == ArenaStatus.IN_PROGRESS || arena.getPlayers().size() >= arena.getConfig().getMaxPlayers()) {
            data.getPlayer().setGameMode(GameMode.SPECTATOR);
            return;
        }

        if (arena.getPrimaryTeam().getPlayers().size() >= arena.getConfig().getMaxPlayers())
            arena.getSecondaryTeam().getPlayers().add(acc);
        else
            arena.getPrimaryTeam().getPlayers().add(acc);

        data.getPlayer().setGameMode(GameMode.ADVENTURE);
        p.teleport(arena.getConfig().getLobbyLocation());

        new VerifyPlayersTask(arena).runTaskTimer(StrixTheBridge.getInstance(), 0L, 20L);
    }

}
