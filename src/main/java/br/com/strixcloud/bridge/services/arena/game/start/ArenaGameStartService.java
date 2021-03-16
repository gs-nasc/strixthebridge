package br.com.strixcloud.bridge.services.arena.game.start;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.entities.arena.Arena;
import br.com.strixcloud.bridge.entities.arena.ArenaCage;
import br.com.strixcloud.bridge.services.arena.player.prepare.ArenaPlayerPrepareController;
import br.com.strixcloud.bridge.task.StartGameTask;
import lombok.AllArgsConstructor;
import lombok.var;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.UUID;

@AllArgsConstructor
public class ArenaGameStartService {

    private final Arena arena;

    public void execute() {
        var currentPlayers = arena.getPlayers();
        if (currentPlayers.size() >= arena.getConfig().getMinPlayers()) {
            var cages = new ArrayList<ArenaCage>();
            for (var p : currentPlayers) {
                var bukkitP = Bukkit.getPlayer(UUID.fromString(p.getUuid()));
                ArenaPlayerPrepareController.getInstance().handle(bukkitP);
                var cage = new ArenaCage(arena.getTeam(bukkitP).getConfig().getSpawn());
                cage.build();
                cages.add(cage);
            }
            var pl = StrixTheBridge.getInstance();
            new StartGameTask(cages, arena).runTaskTimer(pl, 0L, 20L);
        }
    }

}
