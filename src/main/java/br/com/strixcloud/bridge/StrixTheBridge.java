package br.com.strixcloud.bridge;

import br.com.strixcloud.bridge.repository.IAccountsRepository;
import br.com.strixcloud.lib.sql.IQueryExecutor;
import br.com.strixcloud.lib.util.log.IStrixLogger;
import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class StrixTheBridge extends JavaPlugin {

    @Getter private static StrixTheBridge instance;

    @Getter private IStrixLogger sLogger;

    @Getter private IQueryExecutor queryExecutor;
    @Getter private IAccountsRepository accountsRepository;

    @Override
    public void onEnable() {
        instance = this;
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage("§d ___ _____ _        ___     _    _          ");
        sender.sendMessage("§d/ __|_   _| |_  ___| _ )_ _(_)__| |__ _ ___ ");
        sender.sendMessage("§d\\__ \\ | | | ' \\/ -_) _ \\ '_| / _` / _` / -_) §7§l"+this.getDescription().getVersion());
        sender.sendMessage("§d|___/ |_| |_||_\\___|___/_| |_\\__,_\\__, \\___|");
        sender.sendMessage("§d                                  |___/     ");
        sender.sendMessage("  §7§lCoding your dreams §8| §5§lStrix Cloud");
        sender.sendMessage("§8 ----------------------------------");
    }

    private boolean setupDatabase() {
        return false;
    }

    private void loadRepositories() {

    }

    private void loadData() {

    }

    @Override
    public void onDisable() {

    }
}
