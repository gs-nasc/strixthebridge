package br.com.strixcloud.bridge.services.arena.game.point;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.bukkit.reflection.TitleMessage;
import br.com.strixcloud.bridge.entities.arena.Arena;
import br.com.strixcloud.bridge.entities.arena.utils.ArenaStatus;
import br.com.strixcloud.bridge.services.arena.game.end.ArenaGameEndController;
import br.com.strixcloud.bridge.services.arena.game.reset.ArenaGameResetController;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class ArenaGamePointService {

    private final Arena arena;

    public void execute(ArenaGamePointDTO data) {
        var p = data.getPlayer();

        if (arena.isPlaying(p) && arena.getStatus() == ArenaStatus.IN_PROGRESS) {
            var team = arena.getTeam(p);
            var enemy = arena.getEnemyTeam(p);

            if (enemy.getConfig().getPit().distanceSquared(p.getLocation()) <= Math.pow(arena.getConfig().getPitRadius(), 2)) {
                team.setPoints(team.getPoints() + 1);
                arena.setStatus(ArenaStatus.RESETTING);
                if (team.getPoints() >= arena.getConfig().getWiningScore()) {
                    ArenaGameEndController.getInstance().handle(team, enemy);
                    return;
                }
                var title = new TitleMessage("&5&lThe Bridge", String.format("&fA equipe %s &fmarcou &d1 &fponto!", team.getConfig().getPrefix()));
                title.send();
                p.setGameMode(GameMode.SPECTATOR);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        ArenaGameResetController.getInstance().handle();
                    }
                }.runTaskLater(StrixTheBridge.getInstance(), 40L);
            }
        }
    }

}
