package ch.hatbe2113.backToCraftServerPlugin.events;

import ch.hatbe2113.backToCraftServerPlugin.Main;
import ch.hatbe2113.backToCraftServerPlugin.handlers.PlayerBaseHandler;
import ch.hatbe2113.backToCraftServerPlugin.handlers.SpawnHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.Instant;

public class OnPlayerJoinEvent implements Listener {

    private Main main;

    public OnPlayerJoinEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e) {
        // handle player in spawnhandler
        SpawnHandler.onPlayerJoin(main, e);

        // handler player in playerbasehandler
        PlayerBaseHandler.onPlayerJoin(main, e);
    }
}
