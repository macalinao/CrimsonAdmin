/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import com.crimsonrpg.coreapi.citizen.Citizen;
import com.crimsonrpg.coreapi.util.CrimsonCommand;
import com.crimsonrpg.coreapi.CrimsonManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 * Item command.
 */
public class CommandItem extends CrimsonCommand {
    public void execute(CommandSender cs, Command cmnd, String string, String[] args) {
        if (!(cs instanceof Player)) {
            cs.sendMessage("This command may only be used in-game.");
            return;
        }
        
        Citizen citizen = CrimsonManager.getCitizenManager().getCitizen((SpoutPlayer) cs);
        
        if (!citizen.hasPermission("crimson.rank.admin")) {
            citizen.sendError("You aren't allowed to use this command.");
            return;
        }
        
        if (args.length < 1) {
            citizen.sendError("Usage: /item <block> <amount>");
            return;
        }
        
        Material mat = null;
        
        try {
            int matId = Integer.parseInt(args[0]);
            mat = Material.getMaterial(matId);
        } catch (NumberFormatException e) {
            mat = Material.getMaterial(args[0].toUpperCase().replace(" ", "_"));
        }
        
        
        //Check for valid item
        if (mat == null) {
            citizen.sendError("The item '" + args[0] + "' is not a valid item.");
            return;
        }
        
        //Get the amount
        int amount = 64;
        
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
            citizen.getPlayer().getInventory().addItem(new ItemStack(mat, amount));
        }
        
        citizen.sendInfo("You have been given " + amount + " " + mat.name() + ".");
    } 
}
