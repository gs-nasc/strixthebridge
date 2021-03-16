package br.com.strixcloud.bridge.services.arena.game.join;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.entity.Player;

@Data @AllArgsConstructor
public class ArenaGameJoinDTO {

    private final Player player;

}
