package br.com.strixcloud.bridge.services.arena.player.damage;

import lombok.Data;
import org.bukkit.event.entity.EntityDamageEvent;

@Data
public class ArenaPlayerDamageDTO {

    private final EntityDamageEvent event;

}
