package br.com.strixcloud.bridge.bukkit.command.arena.sub;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.entities.SubCommand;
import lombok.var;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPrimaryPitSub extends SubCommand {

    public SetPrimaryPitSub() {
        super("setpit1", "", "strixthebridge.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        var p = (Player) sender;

        StrixTheBridge.getInstance()
                .getArena()
                .getConfig()
                .getPrimaryTeamConfig()
                .setPit(p.getLocation());

        p.sendMessage("§8[§5StrixTheBridge§8] §dLocalização do pit 1 setada com sucesso!");
    }
}
