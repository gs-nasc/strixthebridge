package br.com.strixcloud.bridge.entities.arena;

import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class ArenaCage {

    private final Player player;

    public void build() {
        player.getLocation()
                .add(0, -1, 0)
                .getBlock()
                .setType(Material.STAINED_GLASS);
        player.getLocation()
                .add(0, 3, 0)
                .getBlock()
                .setType(Material.STAINED_GLASS);
        for (int i = 0; i < 3; i++) {
            player.getLocation()
                    .add(1, i, 0)
                    .getBlock()
                    .setType(Material.STAINED_GLASS);
            player.getLocation()
                    .add(-1, i, 0)
                    .getBlock()
                    .setType(Material.STAINED_GLASS);
            player.getLocation()
                    .add(0, i, 1)
                    .getBlock()
                    .setType(Material.STAINED_GLASS);
            player.getLocation()
                    .add(0, i, -1)
                    .getBlock()
                    .setType(Material.STAINED_GLASS);
        }
    }

    public void delete() {
        player.getLocation()
                .add(0, -1, 0)
                .getBlock()
                .setType(Material.AIR);
        player.getLocation()
                .add(0, 3, 0)
                .getBlock()
                .setType(Material.AIR);
        for (int i = 0; i < 3; i++) {
            player.getLocation()
                    .add(1, i, 0)
                    .getBlock()
                    .setType(Material.AIR);
            player.getLocation()
                    .add(-1, i, 0)
                    .getBlock()
                    .setType(Material.AIR);
            player.getLocation()
                    .add(0, i, 1)
                    .getBlock()
                    .setType(Material.AIR);
            player.getLocation()
                    .add(0, i, -1)
                    .getBlock()
                    .setType(Material.AIR);
        }
    }

}
