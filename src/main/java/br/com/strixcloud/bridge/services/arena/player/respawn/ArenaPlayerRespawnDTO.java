package br.com.strixcloud.bridge.services.arena.player.respawn;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@Data @RequiredArgsConstructor
public class ArenaPlayerRespawnDTO {

    private final Player player;

}
