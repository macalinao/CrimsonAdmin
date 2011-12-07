/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.crimsonrpg.citizens.api.Citizen;
import com.crimsonrpg.citizens.api.CitizenAPI;
import com.crimsonrpg.util.CrimsonCommand;
import org.bukkit.Bukkit;

/**
 * @author Dylan
 */
public class CommandSun extends CrimsonCommand {
    public void execute(CommandSender cs, Command cmnd, String string, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage("This command may only be used in-game.");
            return;
        }
        
        Citizen citizen = CitizenAPI.getCitizenManager().getCitizen((SpoutPlayer) cs);
        
        if (args.length > 1) {
            citizen.sendError("Too many arguments.");
            return;
        }
        
        if (!citizen.hasPermission("crimson.rank.admin")) {
            citizen.sendError("You do not have permission to use this command.");
            return;
        } 
           citizen.getBukkitEntity().getWorld().setStorm(false);
           citizen.getBukkitEntity().getWorld().setThundering(false);
           citizen.getBukkitEntity().getWorld().setThundering(false);
           Bukkit.broadcastMessage(citizen.getName() + " is awesome, and has stopped a storm on " + citizen.getBukkitEntity().getWorld().getName() + ".");
    }
}
