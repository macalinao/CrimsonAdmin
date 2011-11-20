/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import com.crimsonrpg.coreapi.CrimsonManager;
import com.crimsonrpg.coreapi.util.CrimsonCommand;
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
        CrimsonManager.getDataManager().save();
        cs.sendMessage("Config saved.");
    }
    
}
