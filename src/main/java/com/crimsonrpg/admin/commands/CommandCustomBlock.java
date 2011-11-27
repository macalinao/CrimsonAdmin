/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.crimsonrpg.citizens.api.Citizen;
import com.crimsonrpg.citizens.api.CitizenAPI;
import com.crimsonrpg.citizens.api.MessageLevel;
import com.crimsonrpg.items.api.ItemAPI;
import com.crimsonrpg.util.CrimsonCommand;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomBlock;

/**
 * Item command.
 */
public class CommandCustomBlock extends CrimsonCommand {
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
        CustomBlock block = ItemAPI.getCrimsonBlockManager().getBlock(matName);
        
        //Check if the block exists
        if (block == null) {
            citizen.sendMessage("That block does not exist.", MessageLevel.ERROR);
            return;
        }
        
        //Get the amount
        int amount = 1;
        
        //Check for a valid amount
        if (args.length > 1) {
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                citizen.sendError("'" + args[1] + "' is not a valid block.");
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
            ItemStack stack = new SpoutItemStack(block, amount);
            citizen.getPlayer().getInventory().addItem(stack);
        }
        
        citizen.sendInfo("You have been given " + amount + " " + block.getFullName() + ".");
    } 
}
