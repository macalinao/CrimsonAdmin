/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import com.crimsonrpg.citizens.api.Citizen;
import com.crimsonrpg.citizens.api.CitizenAPI;
import com.crimsonrpg.util.CrimsonCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 * Teleports people.
 */
public class CommandTeleport extends CrimsonCommand {
    public void execute(CommandSender cs, Command cmnd, String string, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage("This command may only be used in-game.");
            return;
        }
        
        Citizen citizen = CitizenAPI.getCitizenManager().getCitizen((SpoutPlayer) cs);
        //checks if the player has entered a argument
        if (args.length < 1) {
            citizen.sendError("Please enter a name.");
            return;
        }
        
        //checks if the player has permission.
        if (!citizen.hasPermission("crimson.rank.admin")) {
            citizen.sendError("You do not have permission to use this command.");
            return;
        }
        //creates player object.
            SpoutPlayer player1 = (SpoutPlayer) Bukkit.getServer().getPlayer(args[0]);
        //checks if there are two arguments.    
        if (args.length == 2) {
            SpoutPlayer player2 = (SpoutPlayer) Bukkit.getServer().getPlayer(args[1]);
            player1.getPlayer().teleport(player2);
            citizen.sendInfo("You teleported " + player1.getName()+ "to " + player2.getName());
            return;
        //if there isn't two arguments checks if there is one :D 
        } else if (args.length == 1) {
            citizen.getPlayer().teleport(player1);
            citizen.sendInfo("You have been teleported to " + player1.getName());
        }   
    }
}
