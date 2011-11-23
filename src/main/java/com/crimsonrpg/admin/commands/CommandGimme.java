/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.crimsonrpg.citizens.api.Citizen;
import com.crimsonrpg.citizens.api.CitizenAPI;
import com.crimsonrpg.util.CrimsonCommand;
import com.crimsonrpg.economy.FlagMoney;

/**
 * Gives the player an arbitrary amount of money.
 */
public class CommandGimme extends CrimsonCommand {
    public void execute(CommandSender cs, Command cmnd, String string, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage("This command may only be used in-game for now.");
            return;
        }
        
        Citizen citizen = CitizenAPI.getCitizenManager().getCitizen((SpoutPlayer) cs);
        
        if (!citizen.hasPermission("crimson.rank.admin")) {
            citizen.sendError("You aren't allowed to use this command.");
            return;
        }
        
        if (args.length < 1) {
            citizen.sendError("Usage: /gimme <amount of coins>");
            return;
        }
        
        int moneyAmount = Integer.parseInt(args[0]); //TODO: Check if the number is valid
        
        
        citizen.getFlag(FlagMoney.class).add(moneyAmount);
        citizen.sendMessage(ChatColor.GREEN + "Okay, okay, here's your " + moneyAmount + " coins!");
    }
    
}
