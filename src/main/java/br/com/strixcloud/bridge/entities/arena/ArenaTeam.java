package br.com.strixcloud.bridge.entities.arena;

import br.com.strixcloud.bridge.entities.arena.config.ArenaTeamConfig;
import br.com.strixcloud.bridge.entities.player.PlayerAccount;
import lombok.Data;
import org.bukkit.entity.Player;

import java.util.List;

@Data
public class ArenaTeam {

    private ArenaTeamConfig config;

    private int points;
    private List<PlayerAccount> players;

    public boolean isMember(Player p) {
        return players.stream().anyMatch(acc -> acc.getUuid().equals(p.getUniqueId().toString()));
    }

    public void reset() {
        players.clear();
        points = 0;
    }

}
