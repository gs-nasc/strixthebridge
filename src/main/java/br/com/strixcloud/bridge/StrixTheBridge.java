package br.com.strixcloud.bridge;

import br.com.strixcloud.bridge.bukkit.command.arena.ArenaCommand;
import br.com.strixcloud.bridge.bukkit.listener.PlayerDamageListener;
import br.com.strixcloud.bridge.bukkit.listener.PlayerJoinListener;
import br.com.strixcloud.bridge.entities.arena.Arena;
import br.com.strixcloud.bridge.entities.serializer.StatisticsSerializer;
import br.com.strixcloud.bridge.provider.IArenaConfigProvider;
import br.com.strixcloud.bridge.provider.IWorldProvider;
import br.com.strixcloud.bridge.provider.impl.NMSWorldProvider;
import br.com.strixcloud.bridge.provider.impl.YamlArenaConfigProvider;
import br.com.strixcloud.bridge.repository.IAccountsRepository;
import br.com.strixcloud.bridge.repository.impl.SQLAccountsRepository;
import br.com.strixcloud.bridge.services.account.data.load.AccountDataLoadController;
import br.com.strixcloud.bridge.services.account.data.save.AccountDataSaveController;
import br.com.strixcloud.bridge.services.arena.config.load.ArenaConfigLoadController;
import br.com.strixcloud.lib.entities.DatabaseType;
import br.com.strixcloud.lib.entities.util.ConfigFile;
import br.com.strixcloud.lib.entities.util.DateDuration;
import br.com.strixcloud.lib.sql.IQueryExecutor;
import br.com.strixcloud.lib.sql.impl.HikariQueryExecutor;
import br.com.strixcloud.lib.sql.impl.SQLiteQueryExecutor;
import br.com.strixcloud.lib.util.log.IStrixLogger;
import br.com.strixcloud.lib.util.log.impl.StrixLogger;
import lombok.Getter;

import lombok.var;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class StrixTheBridge extends JavaPlugin {

    @Getter private ConfigFile configuration;
    @Getter private ConfigFile fileStorage;

    @Getter private IStrixLogger sLogger;

    @Getter private IWorldProvider worldProvider;
    @Getter private IArenaConfigProvider arenaConfigProvider;

    @Getter private IQueryExecutor queryExecutor;
    @Getter private IAccountsRepository accountsRepository;

    @Getter private Arena arena;

    @Override
    public void onEnable() {
        if(!load()) return;
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage("§d ___ _____ _        ___     _    _          ");
        sender.sendMessage("§d/ __|_   _| |_  ___| _ )_ _(_)__| |__ _ ___ ");
        sender.sendMessage("§d\\__ \\ | | | ' \\/ -_) _ \\ '_| / _` / _` / -_) §7§l"+this.getDescription().getVersion());
        sender.sendMessage("§d|___/ |_| |_||_\\___|___/_| |_\\__,_\\__, \\___|");
        sender.sendMessage("§d                                  |___/     ");
        sender.sendMessage("  §7§lCoding your dreams §8| §5§lStrix Cloud");
        sender.sendMessage("§8 ----------------------------------");
    }

    private boolean load() {
        if (setupDependencies()) {
            Bukkit.getPluginManager().disablePlugin(this);
            return false;
        }
        setupConfig();
        sLogger = new StrixLogger(this, configuration);
        if (!setupDatabase()) {
            Bukkit.getPluginManager().disablePlugin(this);
            return false;
        }
        loadRepositories();
        loadData();
        loadBukkit();
        setupArena();
        return true;
    }

    public void loadBukkit() {
        setupListeners();
        setupCommands();
    }

    /*
        Setup
     */

    private boolean setupDependencies() {
        var strixLib = Bukkit.getPluginManager().getPlugin("StrixLib");
        return strixLib == null || !strixLib.isEnabled();
    }

    private void setupConfig() {
        configuration = new ConfigFile(this, "config.yml");
        fileStorage = new ConfigFile(this, "storage.yml");
    }

    private boolean setupDatabase() {
        DateDuration duration = new DateDuration();
        var dbType = DatabaseType.valueOf(configuration.getMessage("Database.type"));
        switch (dbType) {
            case MYSQL: {
                queryExecutor = new HikariQueryExecutor(configuration);
                try {
                    var open = queryExecutor.open();
                    var ms = duration.calculate();
                    sLogger.info(String.format("Connection stabilised to MySQL (%s ms)", ms));
                    return open;
                } catch (Exception e) {
                    sLogger.error("Could not connect to MySQL database, trying to connect SQLite");
                    queryExecutor = new SQLiteQueryExecutor(configuration);
                    sLogger.info("Connection stabilised to SQLite, because MySQL failed");
                    return true;
                }
            }
            case SQLITE: {
                queryExecutor = new SQLiteQueryExecutor(configuration);
                var ms = duration.calculate();
                sLogger.info(String.format("Connection stabilised to SQLite (%s ms)", ms));
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public void setupListeners() {
        DateDuration duration = new DateDuration();

        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);

        var ms = duration.calculate();
        sLogger.info(String.format("Successfully setup listeners (%s ms)", ms));
    }

    public void setupCommands() {
        getCommand("arena").setExecutor(new ArenaCommand());
    }

    public void setupArena() {
        DateDuration duration = new DateDuration();
        worldProvider = new NMSWorldProvider();
        arenaConfigProvider = new YamlArenaConfigProvider(configuration, fileStorage);

        this.arena = ArenaConfigLoadController.getInstance().handle();

        var ms = duration.calculate();
        sLogger.info(String.format("Successfully setup arena (%s ms)", ms));
    }

    /*
        Loading
     */

    public static StrixTheBridge getInstance() {
        return getPlugin(StrixTheBridge.class);
    }

    private void loadRepositories() {
        var statisticsSerializer = StatisticsSerializer.getInstance();
        accountsRepository = new SQLAccountsRepository(
                statisticsSerializer, queryExecutor, sLogger);
        accountsRepository.initialize();
    }

    private void loadData() {
        AccountDataLoadController.getInstance().handle();
    }

    @Override
    public void onDisable() {
        try {
            if (queryExecutor != null && queryExecutor.isConnected()) {
                AccountDataSaveController.getInstance().handle();
                queryExecutor.close();
            }
        } catch (SQLException e) { }
    }
}
