/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.crimsonrpg.admin.CrimsonAdmin;
import com.crimsonrpg.util.CrimsonCommand;
import org.bukkit.Bukkit;

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
        
        Bukkit.dispatchCommand(sender, "plotloadall");
        sender.sendMessage("Crimson loaded.");
    }
    
}
