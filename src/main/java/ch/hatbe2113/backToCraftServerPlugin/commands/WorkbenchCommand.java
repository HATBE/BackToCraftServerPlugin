package ch.hatbe2113.backToCraftServerPlugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkbenchCommand implements CommandExecutor {

    private Player senderPlayer;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // if sender is no player, return error and exit
        if(!(sender instanceof Player)) {
            sender.sendMessage(String.format("%sYou have to be a Player to execute this command!", ChatColor.DARK_RED));
            return false;
        }

        senderPlayer = (Player) sender;

        // if wrong command format is given, return error and exit
        if(args.length != 0) {
            senderPlayer.sendMessage(String.format("%sYou are using the wrong format! Use %s", ChatColor.DARK_RED, command.getName()));
            return false;
        }

        // if player has not the right permission, return error and exit
        if(!(senderPlayer.hasPermission("btc.wb"))) {
            senderPlayer.sendMessage(String.format("%sYou don't have the permissions to execute this command", ChatColor.DARK_RED));
            return false;
        }

        senderPlayer.openWorkbench(null, true);

        return true;
    }
}
