package br.com.strixcloud.bridge.task;

import br.com.strixcloud.bridge.bukkit.reflection.TitleMessage;
import br.com.strixcloud.bridge.entities.arena.Arena;
import br.com.strixcloud.bridge.services.arena.game.start.ArenaGameStartController;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class VerifyPlayersTask extends BukkitRunnable {

    private final Arena arena;
    private int i;

    public void run() {
        var currentPlayers = arena.getPlayers();
        if (currentPlayers.size() >= arena.getConfig().getMinPlayers()) {
            i++;
            var title = new TitleMessage("&5&lThe Bridge", String.format("&fIniciando em &d&l%s", 6 - i));
            title.send();
            if (i >= 6) {
                ArenaGameStartController.getInstance().handle();
                this.cancel();
            }
        } else {
            var title = new TitleMessage("&5&lThe Bridge", String.format("&cUm player saiu da partida, cancelando..."));
            title.send();
            this.cancel();
        }
    }
}
