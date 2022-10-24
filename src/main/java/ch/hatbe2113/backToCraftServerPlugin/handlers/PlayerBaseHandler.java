package ch.hatbe2113.backToCraftServerPlugin.handlers;

import ch.hatbe2113.backToCraftServerPlugin.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.Instant;

public class PlayerBaseHandler {

    public static void onPlayerJoin(Main main, PlayerJoinEvent e) {
        if(!e.getPlayer().hasPlayedBefore()) {
            // write data to playerbase
            String username = e.getPlayer().getName();
            String uuid = e.getPlayer().getUniqueId().toString();
            int joindate = (int) Instant.now().getEpochSecond();;

            main.getUserBase().set(String.format("user.%s.username", uuid), username);
            main.getUserBase().set(String.format("user.%s.joindate", uuid), joindate);
            main.getUserBase().save();
        }
    }
}
