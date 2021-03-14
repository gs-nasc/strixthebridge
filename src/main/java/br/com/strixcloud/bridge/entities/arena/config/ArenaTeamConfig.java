package br.com.strixcloud.bridge.entities.arena.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;

@Data @RequiredArgsConstructor
public class ArenaTeamConfig {

    private final String prefix;
    private final String color;

    private final Material blockType;
    private final short data;

    /*
        Storage
     */

    private Location pit;
    private Location base;

}
