/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import com.crimsonrpg.core.Crimson;
import com.crimsonrpg.coreapi.util.CrimsonCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Resets everything. VERY dangerous. And volatile.
 */
public class CommandForceReset extends CrimsonCommand {
    private Crimson core;

    public CommandForceReset(Crimson core) {
        this.core = core;
    }

    @Override
    public void execute(CommandSender sender, Command cmnd, String string, String[] args) {
        //sender.sendError("This command is not yet supported.");
    }
    
    
}
