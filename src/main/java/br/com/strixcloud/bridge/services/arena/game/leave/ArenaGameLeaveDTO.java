package br.com.strixcloud.bridge.services.arena.game.leave;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.entity.Player;

@Data @AllArgsConstructor
public class ArenaGameLeaveDTO {

    private final Player player;

}
