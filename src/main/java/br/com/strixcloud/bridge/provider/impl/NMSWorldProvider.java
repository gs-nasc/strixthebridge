package br.com.strixcloud.bridge.provider.impl;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.bukkit.reflection.FastBlockUtils;
import br.com.strixcloud.bridge.entities.arena.utils.ArenaBlockType;
import br.com.strixcloud.bridge.entities.serializer.BlockTypeSerializer;
import br.com.strixcloud.bridge.provider.IWorldProvider;
import lombok.var;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.concurrent.CompletableFuture;

public class NMSWorldProvider implements IWorldProvider {

    private Location top;
    private Location down;

    private Vector min;
    private Vector max;

    @Override
    public void load(Location top, Location down) {
        this.top = top;
        this.down = down;
        calculate();

        var pl = StrixTheBridge.getInstance();

        for (int newX = min.getBlockX(); newX <= max.getBlockX(); newX++) {
            for (int newY = min.getBlockY(); newY <= max.getBlockY(); newY++) {
                for (int newZ = min.getBlockZ(); newZ <= max.getBlockZ(); newZ++) {
                    var block = top.getWorld().getBlockAt(newX, newY, newZ);

                    var blockType = new ArenaBlockType(block.getType(), block.getData());
                    var serialized = BlockTypeSerializer.getInstance().serialize(blockType);

                    block.setMetadata("bridge_type", new FixedMetadataValue(pl, serialized));
                }
            }
        }
    }

    @Override
    public void resetBlocks() {
        for (int newX = min.getBlockX(); newX <= max.getBlockX(); newX++) {
            for (int newY = min.getBlockY(); newY <= max.getBlockY(); newY++) {
                for (int newZ = min.getBlockZ(); newZ <= max.getBlockZ(); newZ++) {
                    var block = top.getWorld().getBlockAt(newX, newY, newZ);

                    var serialized = block.getMetadata("bridge_type").get(0).asString();
                    if (serialized == null) continue;

                    var blockType = BlockTypeSerializer.getInstance().deserialize(serialized);

                    var id = blockType.getMaterial().getId();
                    var data = blockType.getData();

                    FastBlockUtils.getInstance()
                            .setBlock((CraftWorld) top.getWorld(), newX, newY, newZ, id, data, false);
                }
            }
        }
    }

    private void calculate() {
        var minX = Math.min(top.getBlockX(), down.getBlockX());
        var minY = Math.min(top.getBlockY(), down.getBlockY());
        var minZ = Math.min(top.getBlockZ(), down.getBlockZ());
        min = new Vector(minX, minY, minZ);

        var maxX = Math.max(top.getBlockX(), down.getBlockX());
        var maxY = Math.max(top.getBlockY(), down.getBlockY());
        var maxZ = Math.max(top.getBlockZ(), down.getBlockZ());
        max = new Vector(maxX, maxY, maxZ);
    }

}
