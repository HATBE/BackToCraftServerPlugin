package ch.hatbe2113.backToCraftServerPlugin.handlers;

import ch.hatbe2113.backToCraftServerPlugin.Main;
import ch.hatbe2113.backToCraftServerPlugin.io.config.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SpawnHandler {
    private static String configRootPath = "locations.spawn.location.";

    public static boolean locationExists(Main main) {
        ConfigHandler config = main.getLocationsConfig();

        if(!config.contains(configRootPath)) {
            // fallback if spawn location does not exist in configfile
            Bukkit.getLogger().warning(String.format("Path \"%s.spawn\" does not exist in config", configRootPath));
            return false;
        }
        return true;
    }

    public static Location getLocation(Main main) {
        ConfigHandler config = main.getLocationsConfig();

        if(!locationExists(main)) {
            // fallback if spawn location does not exist in configfile
            return new Location(Bukkit.getWorld("world"), 0,100,0);
        }

        // read location from configfile
        String world = config.getString(String.format("%sworld", configRootPath));
        double x = config.getDouble(String.format("%sx", configRootPath));
        double y = config.getDouble(String.format("%sy", configRootPath));
        double z = config.getDouble(String.format("%sz", configRootPath));
        float pitch = (float) config.getDouble(String.format("%spitch", configRootPath));
        float yaw = (float) config.getDouble(String.format("%syaw", configRootPath));

        // return new spawn location from config file
        return new Location(Bukkit.getWorld(world), x, y, z, pitch, yaw);
    }

    public static void setLocation(Main main, Location location) {
        ConfigHandler config = main.getLocationsConfig();

        // get location parts from location
        String world = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float pitch = location.getPitch();
        float yaw = location.getYaw();

        location.getWorld().setSpawnLocation(location); // set mc og world spawn

        // write location to configfile
        config.set(String.format("%sworld", configRootPath), world);
        config.set(String.format("%sx", configRootPath), x);
        config.set(String.format("%sy", configRootPath), y);
        config.set(String.format("%sz", configRootPath), z);
        config.set(String.format("%spitch", configRootPath), pitch);
        config.set(String.format("%syaw", configRootPath), yaw);

        config.save();
    }

    public static void onPlayerJoin(Main main, PlayerJoinEvent e) {
        Player ePlayer = e.getPlayer();

        // if its players first join, tp him to spawn
        if(ePlayer.hasPlayedBefore()) {
            ePlayer.teleport(getLocation(main));
        }
    }

    public static void onPlayerRespawn(Main main, PlayerRespawnEvent e) {
        Player ePlayer = e.getPlayer();

        // if player has no bed "respawn location" teleport player to custom spawn (not mc world spawn)
        if(ePlayer.getBedSpawnLocation() == null) {
            e.setRespawnLocation(SpawnHandler.getLocation(main)); // set respawn location to custom world spawn
        }
    }
}
