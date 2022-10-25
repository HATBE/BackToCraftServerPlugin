package ch.hatbe2113.backToCraftServerPlugin.io.config;
import ch.hatbe2113.backToCraftServerPlugin.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {
    private Main main;

    protected FileConfiguration config;

    public ConfigHandler(Main main) {
        this.main = main;
        config = main.getConfig();
    }

    public void addDefault(String path, Object obj) {
        config.addDefault(path, obj);
        config.options().copyDefaults(true);
        save();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        main.saveConfig();
    }

    public void set(String path, Object obj) {
        config.set(path, obj);
    }

    public void delete(String path) {
        config.set(path, null);
    }

    public Object get(String path) {
        if(!config.contains(path))
            return null;
        return config.get(path);
    }

    public String getString(String path) {
        if(!config.contains(path))
            return "";
        return config.getString(path);
    }

    public double getDouble(String path) {
        if(!config.contains(path))
            return 0.0;
        return config.getDouble(path);
    }

    public int getInt(String path) {
        if(!config.contains(path))
            return 0;
        return config.getInt(path);
    }

    public boolean contains(String path) {
        return config.contains(path);
    }

}