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
 * Resets everything. VERY dangerous. And volatile.
 */
public class CommandForceReset extends CrimsonCommand {
    private CrimsonAdmin plugin;

    public CommandForceReset(CrimsonAdmin plugin) {
        this.plugin = plugin;
    }
    @Override
    public void execute(CommandSender sender, Command cmnd, String string, String[] args) {
        Bukkit.dispatchCommand(sender, "forcesave");
        Bukkit.dispatchCommand(sender, "forceload");
    }
    
    
}
