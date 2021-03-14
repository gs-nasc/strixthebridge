package br.com.strixcloud.bridge.provider.impl;

import br.com.strixcloud.bridge.entities.arena.config.ArenaConfig;
import br.com.strixcloud.bridge.entities.arena.config.ArenaTeamConfig;
import br.com.strixcloud.bridge.provider.IArenaConfigProvider;
import br.com.strixcloud.lib.entities.util.ConfigFile;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.bukkit.Material;

@RequiredArgsConstructor
public class YamlArenaConfigProvider implements IArenaConfigProvider {

    private final ConfigFile config;

    @Override
    public ArenaTeamConfig getTeamConfig(String name) {
        var cs = config.getConfig()
                .getConfigurationSection(String.format("Arena.teams.%s", name));
        return new ArenaTeamConfig(
                cs.getString("prefix"),
                cs.getString("color"),
                Material.getMaterial(cs.getString("block.material"), true),
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
}
