package br.com.strixcloud.bridge.services.arena.config.save;

import br.com.strixcloud.bridge.entities.arena.Arena;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ArenaConfigSaveDTO {

    private final Arena arena;

}
