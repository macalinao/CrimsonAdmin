/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import com.crimsonrpg.citizens.api.Citizen;
import com.crimsonrpg.citizens.api.CitizenAPI;
import com.crimsonrpg.util.CrimsonCommand;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 * Teleports people.
 */
public class CommandTeleportPosition extends CrimsonCommand {
    public void execute(CommandSender cs, Command cmnd, String string, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage("This command may only be used in-game.");
            return;
        }
        
        Citizen citizen = CitizenAPI.getCitizenManager().getCitizen((SpoutPlayer) cs);
        //checks if the player has entered a argument
        if (args.length < 3) {
            citizen.sendError("Please enter all three positions.");
            return;
        }
        
        //checks if the player has permission.
        if (!citizen.hasPermission("crimson.rank.admin")) {
            citizen.sendError("You do not have permission to use this command.");
            return;
        }
        //creates player object.
        SpoutPlayer player = (SpoutPlayer) citizen.getPlayer();
            
        int posX = 0;
        int posY = 0;
        int posZ = 0;
        
        try {
           posX =  Integer.parseInt(args[0]);
           posY =  Integer.parseInt(args[1]);
           posZ =  Integer.parseInt(args[2]);

        }   catch (NumberFormatException e) {
            citizen.sendError("Numbers only. ");
            return;
        }
        Location loc = new Location(citizen.getWorld(), posX, posY, posZ);
    }
}
