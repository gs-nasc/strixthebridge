package br.com.strixcloud.bridge.services.arena.player.damage;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.data.AccountsDAO;
import br.com.strixcloud.bridge.services.arena.player.respawn.ArenaPlayerRespawnController;
import lombok.var;
import org.bukkit.entity.Player;

public class ArenaPlayerDamageService {

    public void execute(ArenaPlayerDamageDTO data) {
        var event = data.getEvent();
        var p = (Player) data.getEvent().getEntity();
        switch (event.getCause()) {
            case FALL: {
                event.setCancelled(true);
                return;
            }
            case VOID: {
                event.setCancelled(true);
                var arena = StrixTheBridge.getInstance().getArena();
                var acc = AccountsDAO.getInstance().get(p.getUniqueId().toString());
                var statistics = acc.getStatistics();
                if (arena.isPlaying(p)) {
                    statistics.setDeaths(statistics.getDeaths() + 1);
                    acc.setUpdate(true);
                    ArenaPlayerRespawnController.getInstance().handle(p);
                    return;
                }
                data.getEvent().getEntity().teleport(arena.getConfig().getLobbyLocation());
            }
        }
    }

}
