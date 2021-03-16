package br.com.strixcloud.bridge.services.arena.game.end;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.bukkit.reflection.TitleMessage;
import br.com.strixcloud.bridge.entities.arena.Arena;
import br.com.strixcloud.bridge.entities.arena.utils.ArenaStatus;
import br.com.strixcloud.bridge.services.arena.world.reset.ArenaWorldResetController;
import lombok.AllArgsConstructor;
import lombok.var;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitRunnable;

@AllArgsConstructor
public class ArenaGameEndService {

    private final Arena arena;

    public void execute(ArenaGameEndDTO data) {
        var title = new TitleMessage("&5&lThe Bridge", String.format("&dA equipe %s &dganhou!", data.getWinner().getConfig().getPrefix()));
        title.send();

        Bukkit.getOnlinePlayers().forEach(p -> {
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(arena.getConfig().getLobbyLocation());
        });
        arena.setStatus(ArenaStatus.RESETTING);

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> {
                    p.kickPlayer("§8[§5§lBRIDGE§8] §dA partida acabou, obrigado por participar.");
                });
                arena.reset();
                ArenaWorldResetController.getInstance().handle();
            }
        }.runTaskLater(StrixTheBridge.getInstance(), 100L);
    }

}
