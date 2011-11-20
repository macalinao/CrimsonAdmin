/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import com.crimsonrpg.coreapi.citizen.Citizen;
import com.crimsonrpg.coreapi.util.CrimsonCommand;
import com.crimsonrpg.coreapi.CrimsonManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 * @author Dylan
 */
public class CommandBring extends CrimsonCommand {
    public void execute(CommandSender cs, Command cmnd, String string, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage("This command may only be used in-game.");
            return;
        }
        
        Citizen citizen = CrimsonManager.getCitizenManager().getCitizen((SpoutPlayer) cs);
        
        if (args.length < 1) {
            citizen.sendError("Please enter a name.");
            return;
        }
        
        if (!citizen.hasPermission("crimson.rank.admin")) {
            citizen.sendError("You do not have permission to use this command.");
            return;
        }
            SpoutPlayer player = (SpoutPlayer) Bukkit.getServer().getPlayer(args[0]);
            
            player.teleport(citizen.getLocation());
    }
}
