package ch.hatbe2113.backToCraftServerPlugin.handlers;

import ch.hatbe2113.backToCraftServerPlugin.Main;
import ch.hatbe2113.backToCraftServerPlugin.io.config.CustomConfigHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.Instant;

public class PlayerBaseHandler {

    public static void onPlayerJoin(Main main, PlayerJoinEvent e) {
        Player ePlayer = e.getPlayer();
        CustomConfigHandler playerBase = main.getPlayerBase();

        // if it's the first join of player, write his data to the playerbase
        if(!ePlayer.hasPlayedBefore()) {
            // write data to playerbase
            String username = e.getPlayer().getName();
            String uuid = e.getPlayer().getUniqueId().toString();
            int joindate = (int) Instant.now().getEpochSecond();;

            playerBase.set(String.format("user.%s.username", uuid), username);
            playerBase.set(String.format("user.%s.joindate", uuid), joindate);
            playerBase.save();
        } else {
            // if he's joined already, check if data has changed!
            // TODO: check username from uuid and stuff...
        }
    }
}
