package br.com.strixcloud.bridge.bukkit.command.arena.sub;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.entities.SubCommand;
import lombok.var;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSecondarySpawnSub extends SubCommand {

    public SetSecondarySpawnSub() {
        super("setspawn2", "", "strixthebridge.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        var p = (Player) sender;

        StrixTheBridge.getInstance()
                .getArena()
                .getConfig()
                .getSecondaryTeamConfig()
                .setSpawn(p.getLocation());

        p.sendMessage("§8[§5StrixTheBridge§8] §dLocalização do spawn 2 setado com sucesso!");
    }
}
