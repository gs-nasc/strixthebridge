package br.com.strixcloud.bridge.entities.arena;

import br.com.strixcloud.bridge.entities.arena.config.ArenaConfig;
import br.com.strixcloud.bridge.entities.arena.utils.ArenaStatus;
import lombok.Data;
import org.bukkit.entity.Player;

import java.util.Date;

@Data
public class Arena {

    private ArenaConfig config;

    private Date started;

    private ArenaTeam primaryTeam;
    private ArenaTeam secondaryTeam;

    private ArenaStatus status;

    public Arena() {
        status = ArenaStatus.WAITING;
        primaryTeam = new ArenaTeam();
        secondaryTeam = new ArenaTeam();
    }

    public boolean isPlaying(Player p) {
        return primaryTeam.isMember(p) || secondaryTeam.isMember(p);
    }

    public void reset() {
        primaryTeam.reset();
        secondaryTeam.reset();
        started = new Date();
    }

}
