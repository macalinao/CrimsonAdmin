/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;

import com.crimsonrpg.util.CrimsonCommand;

/**
 *
 * @author simplyianm
 */
public class CommandClear extends CrimsonCommand {

    @Override
    public void execute(CommandSender sender, Command cmnd, String string, String[] args) {
        Inventory inventory = player.getInventory();

        for (int i = 0; i <= 39; i++) {
            inventory.setItem(i, null);
        }
    }

}
