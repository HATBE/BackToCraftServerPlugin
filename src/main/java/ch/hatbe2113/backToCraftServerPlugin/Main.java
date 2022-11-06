package ch.hatbe2113.backToCraftServerPlugin;

import ch.hatbe2113.backToCraftServerPlugin.commands.*;
import ch.hatbe2113.backToCraftServerPlugin.commands.gamemode.GmaCommand;
import ch.hatbe2113.backToCraftServerPlugin.commands.gamemode.GmcCommand;
import ch.hatbe2113.backToCraftServerPlugin.commands.gamemode.GmsCommand;
import ch.hatbe2113.backToCraftServerPlugin.commands.gamemode.GmspCommand;
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
        // /wb
        // permissions: btc.wb
        getCommand("workbench").setExecutor(new WorkbenchCommand());
        // /ec *<player>
        // permissions: btc.ec && btc.ec.others
        getCommand("enderchest").setExecutor(new EnderchestCommand());
        // /ec *<player>
        // permissions: btc.invsee
        getCommand("invsee").setExecutor(new InvseeCommand());
        // /gms *<player>
        // permissions: btc.gms && btc.gms.others
        getCommand("gms").setExecutor(new GmsCommand());
        // /gmc *<player>
        // permissions: btc.gmc && btc.gmc.others
        getCommand("gmc").setExecutor(new GmcCommand());
        // /gma *<player>
        // permissions: btc.gma && btc.gma.others
        getCommand("gma").setExecutor(new GmaCommand());
        // /gmsp *<player>
        // permissions: btc.gmsp && btc.gmsp.others
        getCommand("gmsp").setExecutor(new GmspCommand());
        // /fly *<player>
        // permissions: btc.fly && btc.fly.others
        getCommand("fly").setExecutor(new FlyCommand());
        // /heal *<player>
        // permissions: btc.heal && btc.heal.others
        getCommand("heal").setExecutor(new HealCommand());
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
