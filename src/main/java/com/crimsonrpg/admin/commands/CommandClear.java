/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import com.crimsonrpg.citizens.api.Citizen;
import com.crimsonrpg.citizens.api.CitizenAPI;
import com.crimsonrpg.citizens.api.MessageLevel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;

import com.crimsonrpg.util.CrimsonCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author simplyianm
 */
public class CommandClear extends CrimsonCommand {

    @Override
    public void execute(CommandSender sender, Command cmnd, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command may only be used in-game.");
            return;
        }
        Citizen citizen = CitizenAPI.getCitizenManager().getCitizen((SpoutPlayer) sender);
        
        if (citizen.hasPermission("crimson.rank.admin")) {
            citizen.sendMessage("You don't have permission to use this command.", MessageLevel.ERROR);
            return;
        }
        
        if (args.length > 1) {
            citizen.sendMessage("You may only enter one name or no name, and clear your inventory.", MessageLevel.ERROR);
            return;
        }
        
        if (args.length == 1) {
            String playerName = args[0];
            SpoutPlayer player = (SpoutPlayer) Bukkit.getServer().getPlayer(playerName); 
            Inventory inventory = player.getInventory();
            for (int i = 0; i <= 39; i++) {
                inventory.setItem(i, null);
            }
            return;
        } else if (args.length < 1) {
            Inventory inventory = citizen.getInventory();
            for (int i = 0; i <= 39; i++) {
                inventory.setItem(i, null);     
            }
            return;
        }  
    }
}
