package br.com.strixcloud.bridge.bukkit.command.arena.sub;

import br.com.strixcloud.bridge.entities.SubCommand;
import br.com.strixcloud.bridge.services.arena.config.save.ArenaConfigSaveController;
import lombok.var;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveSub extends SubCommand {
    public SaveSub() {
        super("save", "", "strixthebridge.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        var p = (Player) sender;

        ArenaConfigSaveController.getInstance().handle();

        p.sendMessage("§8[§5StrixTheBridge§8] §dConfiguração salva! Verifique o console.");
    }
}
