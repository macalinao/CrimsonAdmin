/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;


import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.crimsonrpg.citizens.api.Citizen;
import com.crimsonrpg.citizens.api.CitizenAPI;
import com.crimsonrpg.util.CrimsonCommand;

/**
 * Gets the player's position.
 */
public class CommandGetPos extends CrimsonCommand {

    @Override
    public void execute(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You can only get the position of yourself ingame...");
            return;
        }
        
        Citizen citizen = CitizenAPI.getCitizenManager().getCitizen((SpoutPlayer) sender);
        
        if (!citizen.hasPermission("crimson.rank.mod")) {
            citizen.sendError("You're not allowed to use this command.");
            return;
        }
        
        Location loc = citizen.getLocation();
        citizen.sendInfo("Your position is: " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + ".");
    }
    
}
