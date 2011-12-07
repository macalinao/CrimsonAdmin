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
public class CommandSetSpawn extends CrimsonCommand {
 
    public void execute(CommandSender cs, Command cmnd, String string, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage("This command may only be used in-game.");
            return;
        }

        Citizen citizen = CitizenAPI.getCitizenManager().getCitizen((SpoutPlayer) cs);
        //checks if the player has entered a argument
        if (args.length > 0) {
            citizen.sendError("This requires nothing extra.");
            return;
        }
        
        //checks if the player has permission.
        if (!citizen.hasPermission("crimson.rank.admin")) {
            citizen.sendError("You do not have permission to use this command.");
            return;
        }
        //creates player object.
        SpoutPlayer player = (SpoutPlayer) citizen.getBukkitEntity();    
        Location spawn = player.getLocation();
        player.getWorld().setSpawnLocation(spawn.getBlockX(), spawn.getBlockY(), spawn.getBlockZ());
        // fix the position to the center of a block
        spawn = player.getWorld().getSpawnLocation();
        spawn.setX(spawn.getBlockX() + 0.5);
        spawn.setY(spawn.getBlockY());
        spawn.setZ(spawn.getBlockZ() + 0.5);
    }
}
