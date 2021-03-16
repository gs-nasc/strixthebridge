package br.com.strixcloud.bridge.services.arena.game.end;

import br.com.strixcloud.bridge.entities.arena.ArenaTeam;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ArenaGameEndDTO {

    private final ArenaTeam winner;
    private final ArenaTeam looser;

}
