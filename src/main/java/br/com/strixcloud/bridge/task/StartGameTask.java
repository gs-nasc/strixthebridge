package br.com.strixcloud.bridge.task;

import br.com.strixcloud.bridge.bukkit.reflection.TitleMessage;
import br.com.strixcloud.bridge.entities.arena.Arena;
import br.com.strixcloud.bridge.entities.arena.ArenaCage;
import br.com.strixcloud.bridge.entities.arena.utils.ArenaStatus;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

@RequiredArgsConstructor
public class StartGameTask extends BukkitRunnable {

    private final List<ArenaCage> cages;
    private final Arena arena;
    private int i;

    public void run() {
        var currentPlayers = arena.getPlayers();
        if (currentPlayers.size() >= arena.getConfig().getMinPlayers()) {
            i++;
            var title = new TitleMessage("&5&lThe Bridge", String.format("&fLiberando em &d&l%s", 6 - i));
            title.send();
            if (i >= 6) {
                arena.setStatus(ArenaStatus.IN_PROGRESS);
                cages.forEach(ArenaCage::delete);
                this.cancel();
            }
        } else {
            var title = new TitleMessage("&5&lThe Bridge", String.format("&cUm player saiu da partida, cancelando..."));
            title.send();
            this.cancel();
        }
    }

}
