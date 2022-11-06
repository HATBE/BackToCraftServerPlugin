package ch.hatbe2113.backToCraftServerPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand implements CommandExecutor {

    private Player senderPlayer;
    private Player targetPlayer;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // if sender is no player, return error and exit
        if(!(sender instanceof Player)) {
            sender.sendMessage(String.format("%sYou have to be a Player to execute this command!", ChatColor.DARK_RED));
            return false;
        }

        senderPlayer = (Player) sender;

        switch(args.length) {
            case 0:
                // open own enderchest
                if(!(senderPlayer.hasPermission("btc.ec"))) {
                    senderPlayer.sendMessage(String.format("%sYou don't have the permissions to execute this command", ChatColor.DARK_RED));
                    return false;
                }
                senderPlayer.openInventory(senderPlayer.getEnderChest());
                break;
            case 1:
                // open enderchst of other player
                if(!(senderPlayer.hasPermission("btc.ec.others"))) {
                    senderPlayer.sendMessage(String.format("%sYou don't have the permissions to execute this command", ChatColor.DARK_RED));
                    return false;
                }

                targetPlayer = (Player) Bukkit.getPlayer(args[0]);

                if(targetPlayer == null) {
                    senderPlayer.sendMessage(String.format("%s%s is not currently playing on this server", ChatColor.DARK_RED, args[0]));
                    return false;
                }
                senderPlayer.openInventory(targetPlayer.getEnderChest());

                break;
            default:
                senderPlayer.sendMessage(String.format("%sYou are using the wrong format! Use %s *<player>", ChatColor.DARK_RED, command.getName()));
                return false;
        }
        return true;
    }
}
