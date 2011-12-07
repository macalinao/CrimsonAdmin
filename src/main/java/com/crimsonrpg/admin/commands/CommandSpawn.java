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
public class CommandSpawn extends CrimsonCommand {
 
    public void execute(CommandSender cs, Command cmnd, String string, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage("This command may only be used in-game.");
            return;
        }

        Citizen citizen = CitizenAPI.getCitizenManager().getCitizen((SpoutPlayer) cs);
        //checks if the player has entered a argument
        if (args.length > 0) {
            citizen.sendError("This command requires nothing extra.");
            return;
        }
        
        //creates player object.
        SpoutPlayer player = (SpoutPlayer) citizen.getBukkitEntity();    
        Location spawn = player.getWorld().getSpawnLocation();
        player.teleport(spawn);

    }
}
