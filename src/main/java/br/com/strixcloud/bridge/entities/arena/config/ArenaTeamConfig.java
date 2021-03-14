package br.com.strixcloud.bridge.entities.arena.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Material;

@Data @AllArgsConstructor
public class ArenaTeamConfig {

    private final int playersSize;

    private final String prefix;
    private final String color;

    private final Material blockType;
    private final short data;

}
