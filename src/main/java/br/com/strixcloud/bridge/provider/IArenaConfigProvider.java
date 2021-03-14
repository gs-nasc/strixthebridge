package br.com.strixcloud.bridge.provider;

import br.com.strixcloud.bridge.entities.arena.config.ArenaConfig;
import br.com.strixcloud.bridge.entities.arena.config.ArenaTeamConfig;

public interface IArenaConfigProvider {

    ArenaTeamConfig getTeamConfig(String name);

    ArenaConfig getArenaConfig();

}
