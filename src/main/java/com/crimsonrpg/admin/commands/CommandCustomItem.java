/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.crimsonrpg.citizens.api.Citizen;
import com.crimsonrpg.citizens.api.CitizenAPI;
import com.crimsonrpg.citizens.api.MessageLevel;
import com.crimsonrpg.items.items.ItemAPI;
import com.crimsonrpg.util.CrimsonCommand;
import org.getspout.spoutapi.inventory.SpoutItemStack;

/**
 * Item command.
 */
public class CommandCustomItem extends CrimsonCommand {
    public void execute(CommandSender cs, Command cmnd, String string, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage("This command may only be used in-game.");
            return;
        }
        
        Citizen citizen = CitizenAPI.getCitizenManager().getCitizen((SpoutPlayer) cs);
        
        if (!citizen.hasPermission("crimson.rank.admin")) {
            citizen.sendError("You aren't allowed to use this command.");
            return;
        }
        
        if (args.length < 1) {
            citizen.sendMessage("Usage: /customitem <item> <amount>", MessageLevel.ERROR);
            return;
        }
        
        String matName = args[0];
        CustomItem item = ItemAPI.getCrimsonItemManager().getItem(matName);
        
        //Check if the item exists
        if (item == null) {
            citizen.sendMessage("That item does not exist.", MessageLevel.ERROR);
            return;
        }
        
        //Get the amount
        int amount = 1;
        
        //Check for a valid amount
        if (args.length > 1) {
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                citizen.sendError("'" + args[1] + "' is not a valid item.");
                return;
            }
        }
        
        PlayerInventory inventory = citizen.getPlayer().getInventory();
        
        //Check if the amount is greater than 64
        if (amount > 64) {
            int stacks = (int) Math.ceil((double) amount / 64);
            for (int i = 1; i <= stacks; i++) {
                if (inventory.firstEmpty() < 0) break;
            }
        } else {
            ItemStack stack = new SpoutItemStack(item, amount);
            citizen.getPlayer().getInventory().addItem(stack);
        }
        
        citizen.sendInfo("You have been given " + amount + " " + item.getFullName() + ".");
    } 
}
