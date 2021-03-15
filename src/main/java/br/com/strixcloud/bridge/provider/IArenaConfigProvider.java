package br.com.strixcloud.bridge.provider;

import br.com.strixcloud.bridge.entities.arena.config.ArenaConfig;
import br.com.strixcloud.bridge.entities.arena.config.ArenaTeamConfig;
import org.bukkit.Location;

public interface IArenaConfigProvider {

    ArenaTeamConfig getTeamConfig(String name);

    ArenaConfig getArenaConfig();

    void saveStorage(ArenaConfig config);

    Location getStoredLocation(String loc);

    boolean hasStorage();

}
