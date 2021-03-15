package br.com.strixcloud.bridge.entities.arena.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Material;

@Data @AllArgsConstructor
public class ArenaBlockType {

    private Material material;
    private byte data;

}
