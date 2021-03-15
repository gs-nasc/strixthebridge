package br.com.strixcloud.bridge.bukkit.command.arena.sub;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.entities.SubCommand;
import lombok.var;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSecondaryPitSub extends SubCommand {

    public SetSecondaryPitSub() {
        super("setpit2", "", "strixthebridge.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        var p = (Player) sender;

        StrixTheBridge.getInstance()
                .getArena()
                .getConfig()
                .getSecondaryTeamConfig()
                .setPit(p.getLocation());

        p.sendMessage("§8[§5StrixTheBridge§8] §dLocalização do pit 2 setada com sucesso!");
    }
}
