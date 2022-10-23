package ch.hatbe2113.backToCraftServerPlugin.events;

import ch.hatbe2113.backToCraftServerPlugin.Main;
import ch.hatbe2113.backToCraftServerPlugin.handlers.SpawnHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnPlayerRespawnEvent implements Listener {

        private Main main;
        private Player eventPlayer;

        public OnPlayerRespawnEvent(Main main) {
            this.main = main;
        }

        @EventHandler
        public void OnPlayerRespawn(PlayerRespawnEvent e) {
            eventPlayer = e.getPlayer();
            // if player has no bed "respawn location" teleport player to custom spawn (not mc world spawn)
            if(eventPlayer.getBedSpawnLocation() == null) {
                e.setRespawnLocation(SpawnHandler.getLocation(main)); // set respawn location to custom world spawn
            }
        }
}
