package br.com.strixcloud.bridge.services.arena.game.point;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.entity.Player;

@Data @AllArgsConstructor
public class ArenaGamePointDTO {

    private final Player player;

}
