package br.com.strixcloud.bridge.entities.arena;

import br.com.strixcloud.bridge.entities.arena.config.ArenaTeamConfig;
import br.com.strixcloud.bridge.entities.player.PlayerAccount;
import lombok.Data;

@Data
public class ArenaTeam {

    private ArenaTeamConfig config;

    private int points;
    private PlayerAccount[] accounts;

}
