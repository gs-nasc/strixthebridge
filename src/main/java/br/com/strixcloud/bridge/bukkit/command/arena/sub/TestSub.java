package br.com.strixcloud.bridge.bukkit.command.arena.sub;

import br.com.strixcloud.bridge.StrixTheBridge;
import br.com.strixcloud.bridge.entities.SubCommand;
import br.com.strixcloud.bridge.task.VerifyPlayersTask;
import lombok.var;
import org.bukkit.command.CommandSender;

public class TestSub extends SubCommand {
    public TestSub() {
        super("test", "", "strixthebridge.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        var pl = StrixTheBridge.getInstance();
        var arena = pl.getArena();

        new VerifyPlayersTask(arena).runTaskTimer(pl, 0L, 20L);
    }
}
