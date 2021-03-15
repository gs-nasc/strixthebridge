package br.com.strixcloud.bridge.bukkit.command.arena.sub;

import br.com.strixcloud.bridge.entities.SubCommand;
import br.com.strixcloud.bridge.services.arena.world.reset.ArenaWorldResetController;
import lombok.var;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetSub extends SubCommand {
    public ResetSub() {
        super("reset", "", "strixthebridge.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        var p = (Player) sender;

        ArenaWorldResetController.getInstance().handle();

        p.sendMessage("§8[§5StrixTheBridge§8] §dÁrea da arena resetada.");
    }
}
