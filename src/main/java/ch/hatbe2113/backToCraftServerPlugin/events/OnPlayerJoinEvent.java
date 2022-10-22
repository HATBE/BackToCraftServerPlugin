package ch.hatbe2113.backToCraftServerPlugin.events;

import ch.hatbe2113.backToCraftServerPlugin.Main;
import ch.hatbe2113.backToCraftServerPlugin.handlers.SpawnHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoinEvent implements Listener {

    private Main main;
    private Player eventPlayer;

    public OnPlayerJoinEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e) {
        eventPlayer = e.getPlayer();

        if(!eventPlayer.hasPlayedBefore()) {
            // If player has not joined before
            eventPlayer.teleport(SpawnHandler.getLocation(main));
        }
    }
}
