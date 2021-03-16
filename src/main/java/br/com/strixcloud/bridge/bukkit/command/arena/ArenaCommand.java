package br.com.strixcloud.bridge.bukkit.command.arena;

import br.com.strixcloud.bridge.bukkit.command.arena.sub.*;
import br.com.strixcloud.bridge.entities.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ArenaCommand implements CommandExecutor {

    private final List<SubCommand> subCommands;

    public ArenaCommand() {
        subCommands = new ArrayList<>();

        subCommands.add(new TestSub());

        subCommands.add(new SaveSub());
        subCommands.add(new ResetSub());

        subCommands.add(new SetDownSub());
        subCommands.add(new SetTopSub());
        subCommands.add(new SetLobbySub());

        subCommands.add(new SetPrimaryPitSub());
        subCommands.add(new SetPrimarySpawnSub());

        subCommands.add(new SetSecondaryPitSub());
        subCommands.add(new SetSecondarySpawnSub());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length > 0) {
            String currentArg = args[0];
            for (SubCommand subCmd : subCommands) {
                if (currentArg.equalsIgnoreCase(subCmd.getName()) || subCmd.getAlias().contains(currentArg)) {
                    if (sender.hasPermission(subCmd.getPermission()) || subCmd.getPermission().isEmpty()) {
                        subCmd.execute(sender, args);
                        return true;
                    }
                    sender.sendMessage("§8[§4StrixTheBridge§8] §cSem permissão!");
                    return false;
                }
            }
            return false;
        }
        sender.sendMessage("§8[§4StrixTheBridge§8] §cUtilize /arena §c&l<settop|setdown|setlobby|setspawn1|setpit1|setspawn2|setpit2|save|reset>");
        return false;
    }

}
