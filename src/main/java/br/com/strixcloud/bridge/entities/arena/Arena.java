package br.com.strixcloud.bridge.entities.arena;

import br.com.strixcloud.bridge.entities.arena.config.ArenaConfig;
import br.com.strixcloud.bridge.entities.arena.utils.ArenaStatus;
import br.com.strixcloud.bridge.entities.player.PlayerAccount;
import lombok.Data;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<PlayerAccount> getPlayers() {
        return Stream
                .concat(primaryTeam.getPlayers().stream(), secondaryTeam.getPlayers().stream())
                .collect(Collectors.toList());
    }

    public ArenaTeam getTeam(Player p) {
        return primaryTeam.isMember(p) ? primaryTeam : secondaryTeam;
    }

    public void reset() {
        primaryTeam.reset();
        secondaryTeam.reset();
        started = new Date();
    }

}
