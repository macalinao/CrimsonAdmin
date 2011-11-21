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
 * Forces reloading of the configuration.
 */
public class CommandForceReload extends CrimsonCommand {
    @Override
    public void execute(CommandSender sender, Command cmnd, String string, String[] args) {
        CrimsonManager.getDataManager().reload();
        sender.sendMessage("Crimson reloaded.");
    }
    
}
