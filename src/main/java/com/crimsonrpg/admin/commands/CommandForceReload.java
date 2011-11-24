/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.crimsonrpg.admin.CrimsonAdmin;
import com.crimsonrpg.util.CrimsonCommand;

/**
 * Forces reloading of the configuration.
 */
public class CommandForceReload extends CrimsonCommand {
    private CrimsonAdmin plugin;

    public CommandForceReload(CrimsonAdmin plugin) {
        this.plugin = plugin;
    }
    @Override
    public void execute(CommandSender sender, Command cmnd, String string, String[] args) {
        plugin.reload();
        sender.sendMessage("Crimson reloaded.");
    }
    
}
