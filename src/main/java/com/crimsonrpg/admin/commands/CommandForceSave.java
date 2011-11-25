/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import com.crimsonrpg.admin.CrimsonAdmin;
import com.crimsonrpg.util.CrimsonCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Forces saving of everything.
 */
public class CommandForceSave extends CrimsonCommand {
    public void execute(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (!cs.hasPermission("crimson.rank.superadmin")) {
            cs.sendMessage("You aren't allowed to use this command.");
            return;
        }
        
        cs.sendMessage("Saving config...");
        Bukkit.dispatchCommand(cs, "plotsaveall");
        Bukkit.dispatchCommand(cs, "citizensaveall");
        cs.sendMessage("Config saved.");
    }
    
}
