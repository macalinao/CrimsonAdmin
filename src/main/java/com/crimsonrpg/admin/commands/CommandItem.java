/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import com.crimsonrpg.core.Crimson;
import com.crimsonrpg.coreapi.citizen.Citizen;
import com.crimsonrpg.coreapi.util.CrimsonCommand;
import com.crimsonrpg.coreapi.CrimsonManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 * Item command.
 */
public class CommandItem extends CrimsonCommand {
    public void execute(CommandSender cs, Command cmnd, String string, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage("This command may only be used in-game.");
            return;
        }
        
        Citizen citizen = CrimsonManager.getCitizenManager().getCitizen((SpoutPlayer) cs);
        
        if (!citizen.hasPermission("crimson.rank.admin")) {
            citizen.sendError("You aren't allowed to use this command.");
            return;
        }
        
        if (args.length < 1) {
            citizen.sendError("Usage: /item <block> <amount>");
            return;
        }
        
        citizen.getPlayer().getInventory().addItem(new ItemStack(Integer.parseInt(args[0].substring(1)), 64));
    } 
}
