package br.com.strixcloud.bridge.services.arena.player.prepare;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.entity.Player;

@AllArgsConstructor @Data
public class ArenaPlayerPrepareDTO {

    private final Player player;

}
