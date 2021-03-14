package br.com.strixcloud.bridge.entities.arena;

import br.com.strixcloud.bridge.entities.arena.config.ArenaConfig;
import lombok.Data;

@Data
public class Arena {

    private ArenaConfig config;

    private ArenaTeam primaryTeam;
    private ArenaTeam secondaryTeam;

}
