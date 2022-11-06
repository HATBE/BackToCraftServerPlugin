package ch.hatbe2113.backToCraftServerPlugin.commands.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GmaCommand implements CommandExecutor {

    private Player senderPlayer;
    private Player targetPlayer;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch(args.length) {
            case 0:
                if(!(sender instanceof Player)) {
                    sender.sendMessage(String.format("%sYou have to be a Player to execute this command!", ChatColor.DARK_RED));
                    return false;
                }
                senderPlayer = (Player) sender;

                if(!(senderPlayer.hasPermission("btc.gma"))) {
                    senderPlayer.sendMessage(String.format("%sYou don't have the permissions to execute this command", ChatColor.DARK_RED));
                    return false;
                }

                senderPlayer.setGameMode(GameMode.ADVENTURE);
                senderPlayer.sendMessage("Your gamemode has changed to adventure");
                break;
            case 1:
                Boolean canSend = false;
                // if this command is executed in a console, its always allowed
                if(sender instanceof ConsoleCommandSender) {
                    canSend = true;
                }
                // if the sender is a player, then check if he has perms
                if(sender instanceof Player) {
                    senderPlayer = (Player) sender;
                    if(senderPlayer.hasPermission("btc.gma.others")) {
                        canSend = true;
                    }
                }

                // if sending is not allowed, throw error
                if(!canSend) {
                    sender.sendMessage(String.format("%sYou don't have the permissions to execute this command", ChatColor.DARK_RED));
                    return false;
                }

                targetPlayer = (Player) Bukkit.getPlayer(args[0]);

                if(targetPlayer == null) {
                    sender.sendMessage(String.format("%s%s is not currently playing on this server", ChatColor.DARK_RED, args[0]));
                    return false;
                }

                if(senderPlayer == targetPlayer) {
                    sender.sendMessage(String.format("%sPlease use %s", ChatColor.DARK_RED, command.getName()));
                    return false;
                }

                targetPlayer.setGameMode(GameMode.ADVENTURE);
                targetPlayer.sendMessage("Your gamemode has changed to spectator");
                sender.sendMessage(String.format("%ss gamemode has changed to adventure", targetPlayer.getDisplayName()));
                break;
            default:
                sender.sendMessage(String.format("%sYou are using the wrong format! Use %s *<player>", ChatColor.DARK_RED, command.getName()));
                return false;
        }
        return true;
    }
}
