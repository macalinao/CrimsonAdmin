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
 *
 * @author ianschool
 */
public class CommandForceLoad extends CrimsonCommand {
    @Override
    public void execute(CommandSender sender, Command cmnd, String string, String[] args) {
        if (!sender.hasPermission("crimson.rank.superadmin")) {
            sender.sendMessage("You aren't allowed to use this command.");
            return;
        }
        
        CrimsonManager.getDataManager().load();
        sender.sendMessage("Crimson loaded.");
    }
    
}
