package br.com.strixcloud.bridge.bukkit.command.arena.sub;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.entities.SubCommand;
import lombok.var;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbySub extends SubCommand {

    public SetLobbySub() {
        super("setlobby", "", "strixthebridge.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        var p = (Player) sender;

        StrixTheBridge.getInstance().getArena().getConfig().setLobbyLocation(p.getLocation());

        p.sendMessage("§8[§5StrixTheBridge§8] §dLocalização do lobby setada com sucesso!");
    }
}
