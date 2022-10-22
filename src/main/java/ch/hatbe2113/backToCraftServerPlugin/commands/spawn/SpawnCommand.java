package ch.hatbe2113.backToCraftServerPlugin.commands.spawn;

import ch.hatbe2113.backToCraftServerPlugin.Main;
import ch.hatbe2113.backToCraftServerPlugin.handlers.SpawnHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private Main main;

    private Player senderPlayer;

    public SpawnCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(String.format("%sYou have to be a Player to execute this command!", ChatColor.DARK_RED));
            return false;
        }

        senderPlayer = (Player) sender;

        if(args.length != 0) {
            senderPlayer.sendMessage(String.format("%sYou are using the wrong format! Use %s", ChatColor.DARK_RED, command.getName()));
            return false;
        }

        senderPlayer.sendMessage(String.format("%sYou are teleporting to Spawn", ChatColor.GREEN));
        senderPlayer.teleport(SpawnHandler.getLocation(main));
        return true;
    }
}
