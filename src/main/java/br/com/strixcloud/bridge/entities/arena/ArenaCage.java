package br.com.strixcloud.bridge.entities.arena;

import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;

@AllArgsConstructor
public class ArenaCage {

    private final Location location;

    public void build() {
        location
                .clone()
                .add(0, -1, 0)
                .getBlock()
                .setType(Material.STAINED_GLASS);
        location
                .clone()
                .add(0, 3, 0)
                .getBlock()
                .setType(Material.STAINED_GLASS);
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            location
                    .clone()
                    .add(1, i, 0)
                    .getBlock()
                    .setType(Material.STAINED_GLASS);
            location
                    .clone()
                    .add(-1, i, 0)
                    .getBlock()
                    .setType(Material.STAINED_GLASS);
            location
                    .clone()
                    .add(0, i, 1)
                    .getBlock()
                    .setType(Material.STAINED_GLASS);
            location
                    .clone()
                    .add(0, i, -1)
                    .getBlock()
                    .setType(Material.STAINED_GLASS);
        }
    }

    public void delete() {
        location
                .clone()
                .add(0, -1, 0)
                .getBlock()
                .setType(Material.AIR);
        location
                .clone()
                .add(0, 3, 0)
                .getBlock()
                .setType(Material.AIR);
        for (int i = 0; i < 3; i++) {
            location
                    .clone()
                    .add(1, i, 0)
                    .getBlock()
                    .setType(Material.AIR);
            location
                    .clone()
                    .add(-1, i, 0)
                    .getBlock()
                    .setType(Material.AIR);
            location
                    .clone()
                    .add(0, i, 1)
                    .getBlock()
                    .setType(Material.AIR);
            location
                    .clone()
                    .add(0, i, -1)
                    .getBlock()
                    .setType(Material.AIR);
        }
    }

}
