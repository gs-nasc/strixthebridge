package br.com.strixcloud.bridge.services.arena.player.pvp;

import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

@Data
public class ArenaPlayerPVPDTO {

    private final Player attacker;
    private final Player victim;
    private final double damage;

}
