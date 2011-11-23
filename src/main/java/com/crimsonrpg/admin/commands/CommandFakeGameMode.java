/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.crimsonrpg.citizens.api.Citizen;
import com.crimsonrpg.citizens.api.CitizenAPI;
import com.crimsonrpg.util.CrimsonCommand;

/**
 * Sends a fake game mode message
 */
public class CommandFakeGameMode extends CrimsonCommand {
    @Override
    public void execute(CommandSender sender, Command cmnd, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Fake game mode can only be used ingame.");
            return;
        }
        
        Citizen citizen = CitizenAPI.getCitizenManager().getCitizen((SpoutPlayer) sender);
        
        //Check for permission to create plots
        if (!citizen.hasPermission("crimson.rank.admin")) {
            citizen.sendError("You're not allowed to use fake game mode!");
            return;
        }
        
        if (args.length < 1) {
            citizen.sendError(cmnd.getUsage());
            return;
        }
        
        String receiverStr = args[0];
        Player receiverPlayer = Bukkit.getServer().getPlayer(receiverStr);
        
        //Check if the player is online
        if (receiverPlayer == null) {
            citizen.sendMessage(ChatColor.RED + "That player is either not online does not exist!");
            return;
        }
        
        receiverPlayer.sendMessage(ChatColor.GRAY + "(" + sender.getName() + ": Setting " + receiverPlayer.getName() + " to game mode 1");
        receiverPlayer.sendMessage("Your game mode has been changed");
    }
    
}
