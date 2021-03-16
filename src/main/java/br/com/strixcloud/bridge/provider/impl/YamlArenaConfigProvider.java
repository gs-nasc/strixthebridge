package br.com.strixcloud.bridge.provider.impl;

import br.com.strixcloud.bridge.entities.arena.config.ArenaConfig;
import br.com.strixcloud.bridge.entities.arena.config.ArenaTeamConfig;
import br.com.strixcloud.bridge.provider.IArenaConfigProvider;
import br.com.strixcloud.lib.entities.util.ConfigFile;
import br.com.strixcloud.lib.util.serializer.impl.LocationSerializer;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.bukkit.Location;
import org.bukkit.Material;

@RequiredArgsConstructor
public class YamlArenaConfigProvider implements IArenaConfigProvider {

    private final ConfigFile config;
    private final ConfigFile storage;

    @Override
    public ArenaTeamConfig getTeamConfig(String name) {
        var cs = config.getConfig()
                .getConfigurationSection(String.format("Arena.teams.%s", name));
        return new ArenaTeamConfig(
                cs.getString("prefix"),
                cs.getString("color"),
                Material.getMaterial(cs.getString("block.material")),
                (short) cs.getInt("block.data"));
    }

    @Override
    public ArenaConfig getArenaConfig() {
        var cs = config.getConfig().getConfigurationSection("Arena");

        var primary = getTeamConfig("primary");
        var secondary = getTeamConfig("secondary");

        return new ArenaConfig(cs.getInt("players.max"),
                cs.getInt("players.min"),
                cs.getInt("wining-score"),
                cs.getInt("radius.pit"),
                cs.getInt("radius.base"),
                cs.getInt("delay.respawn"),
                cs.getInt("delay.arrow"),
                primary,
                secondary);
    }

    @Override
    public void saveStorage(ArenaConfig config) {
        var serializer = LocationSerializer.getInstance();

        var topLocation = serializer.serialize(config.getTopLocation());
        var downLocation = serializer.serialize(config.getDownLocation());
        var lobbyLocation = serializer.serialize(config.getLobbyLocation());

        var primaryPit = serializer.serialize(config.getPrimaryTeamConfig().getPit());
        var primarySpawn = serializer.serialize(config.getPrimaryTeamConfig().getSpawn());

        var secondaryPit = serializer.serialize(config.getSecondaryTeamConfig().getPit());
        var secondarySpawn = serializer.serialize(config.getSecondaryTeamConfig().getSpawn());

        storage.getConfig().set("Arena.top", topLocation);
        storage.getConfig().set("Arena.down", downLocation);
        storage.getConfig().set("Arena.lobby", lobbyLocation);
        storage.getConfig().set("Arena.primary.pit", primaryPit);
        storage.getConfig().set("Arena.primary.spawn", primarySpawn);
        storage.getConfig().set("Arena.secondary.pit", secondaryPit);
        storage.getConfig().set("Arena.secondary.spawn", secondarySpawn);

        storage.saveConfig();
    }

    @Override
    public Location getStoredLocation(String loc) {
        var s = storage.getConfig().getString(String.format("Arena.%s", loc));
        return LocationSerializer.getInstance().deserialize(s);
    }

    @Override
    public boolean hasStorage() {
        return storage.getConfig().contains("Arena");
    }
}
