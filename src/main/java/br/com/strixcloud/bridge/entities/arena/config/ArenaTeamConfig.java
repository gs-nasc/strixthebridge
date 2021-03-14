package br.com.strixcloud.bridge.entities.arena.config;

import lombok.Data;
import lombok.var;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

@Data
public class ArenaTeamConfig {

    private final int playersSize;

    private final String prefix;
    private final String color;

    private final Material blockType;
    private final short data;

    public ArenaTeamConfig(ConfigurationSection cs) {
        playersSize = cs.getInt("players");
        prefix = cs.getString("prefix");
        color = cs.getString("color");
        var split = cs.getString("block").split(";");
        blockType = Material.getMaterial(split[0], true);
        this.data = Short.parseShort(split[1]);
    }

}
