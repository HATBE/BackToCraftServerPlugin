package ch.hatbe2113.backToCraftServerPlugin;

import ch.hatbe2113.backToCraftServerPlugin.commands.spawn.SetSpawnCommand;
import ch.hatbe2113.backToCraftServerPlugin.commands.spawn.SpawnCommand;
import ch.hatbe2113.backToCraftServerPlugin.events.OnPlayerRespawnEvent;
import ch.hatbe2113.backToCraftServerPlugin.events.OnPlayerJoinEvent;
import ch.hatbe2113.backToCraftServerPlugin.io.config.ConfigHandler;
import ch.hatbe2113.backToCraftServerPlugin.io.config.CustomConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    public static final String PLUGIN_NAME = "BackToCraftServerPlugin";
    private Logger pLogger = Bukkit.getLogger();
    private PluginManager plManager = Bukkit.getPluginManager();
    private ConfigHandler pluginConfig = new ConfigHandler(this);
    private CustomConfigHandler locationsConfig = new CustomConfigHandler(this, "locations");
    private CustomConfigHandler playerBase = new CustomConfigHandler(this, "playerBase");

    @Override
    public void onEnable() {
        getpLogger().info(String.format("%s starting up", PLUGIN_NAME));

        // check if something is wrong
        // if something was detected, disable plugin
        if(!checkStartupRoutine()) {
            plManager.disablePlugin(this);
            return;
        }

        buildDefaultConfigs();

        registerEvents();
        registerCommands();

        /* TODO */ debug(); // REMOVE before PROD! -X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X-X
    }

    @Override
    public void onDisable() {
        getpLogger().info(String.format("%s shutting down", PLUGIN_NAME));
    }

    private void registerCommands() {
        // in this function every command is registered

        // /spawn
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        // /setspawn
        // permissions: btc.spawn.set
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
    }

    private void registerEvents() {
        // in this function every event is registered
        plManager.registerEvents(new OnPlayerJoinEvent(this), this);
        plManager.registerEvents(new OnPlayerRespawnEvent(this), this);
    }

    private void buildDefaultConfigs() {

    }

    private Boolean checkStartupRoutine() {
        // in this function all necessary settings are checked.
        // if false is returned, something went wrong

        // TODO:
        return true;
    }

    private void debug() {
        // print stuff for debug in a 3-second interval
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {

            }
        }, 20L * 1, 20L * 3);
    }

    public ConfigHandler getPluginConfig() {
        return pluginConfig;
    }

    public CustomConfigHandler getLocationsConfig() {
        return locationsConfig;
    }

    public CustomConfigHandler getPlayerBase() {
        return playerBase;
    }

    public Logger getpLogger() {
        return pLogger;
    }
}
