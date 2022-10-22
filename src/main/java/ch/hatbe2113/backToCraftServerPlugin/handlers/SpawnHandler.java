package ch.hatbe2113.backToCraftServerPlugin.handlers;

import ch.hatbe2113.backToCraftServerPlugin.Main;
import ch.hatbe2113.backToCraftServerPlugin.io.config.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SpawnHandler {
    public static Location getLocation(Main main) {
        ConfigHandler config = main.getPluginConfig();

        String world = config.getString("spawn.location.world");
        double x = config.getDouble("spawn.location.x");
        double y = config.getDouble("spawn.location.y");
        double z = config.getDouble("spawn.location.z");
        float pitch = (float) config.getDouble("spawn.location.pitch");
        float yaw = (float) config.getDouble("spawn.location.yaw");

        return new Location(Bukkit.getWorld(world), x, y, z, pitch, yaw);
    }

    public static void setLocation(Main main, Location location) {
        ConfigHandler config = main.getPluginConfig();
        String world = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float pitch = location.getPitch();
        float yaw = location.getYaw();

        location.getWorld().setSpawnLocation(location);

        config.set("spawn.location.world", world);
        config.set("spawn.location.x", x);
        config.set("spawn.location.y", y);
        config.set("spawn.location.z", z);
        config.set("spawn.location.pitch", pitch);
        config.set("spawn.location.yaw", yaw);
        config.save();
    }
}
