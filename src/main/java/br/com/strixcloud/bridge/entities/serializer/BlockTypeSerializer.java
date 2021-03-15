package br.com.strixcloud.bridge.entities.serializer;

import br.com.strixcloud.bridge.entities.arena.utils.ArenaBlockType;
import br.com.strixcloud.lib.util.serializer.Serializer;
import lombok.Getter;
import lombok.var;
import org.bukkit.Material;

public class BlockTypeSerializer implements Serializer<ArenaBlockType, String> {

    @Getter
    private static final BlockTypeSerializer instance = new BlockTypeSerializer();

    @Override
    public String serialize(ArenaBlockType arenaBlockType) {
        return arenaBlockType.getMaterial().toString() + ";" + arenaBlockType.getData();
    }

    @Override
    public ArenaBlockType deserialize(String s) {
        var split = s.split(";");
        return new ArenaBlockType(Material.valueOf(split[0]), Byte.parseByte(split[1]));
    }
}
