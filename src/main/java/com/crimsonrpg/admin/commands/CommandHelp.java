/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.commands;

import com.crimsonrpg.citizens.api.Citizen;
import com.crimsonrpg.citizens.api.CitizenAPI;
import com.crimsonrpg.citizens.api.MessageLevel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;

import com.crimsonrpg.util.CrimsonCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Dylan
 */
public class CommandHelp extends CrimsonCommand {

    @Override
    public void execute(CommandSender sender, Command cmnd, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command may only be used in-game.");
            return;
        }
        Citizen citizen = CitizenAPI.getCitizenManager().getCitizen((SpoutPlayer) sender);
        
        citizen.sendMessage(ChatColor.GOLD + "=== HelpDesk ===");
        citizen.sendMessage(ChatColor.GRAY + "Useful commands:");
        citizen.sendMessage(ChatColor.RED + "/exchange <amount>; exchanges gold bars for server money.");
        citizen.sendMessage(ChatColor.RED + "/plotcreate <plotname>; creates a plot, (it costs server money plus 1 diamond).");
        citizen.sendMessage(ChatColor.RED + "/plotinvite <player>; invites a player");
        citizen.sendMessage(ChatColor.RED + "/clancreate <clanname>; creates a clan cost server money.");
        citizen.sendMessage(ChatColor.RED + "/claninvite <playername>; invites a player to your clan.");
        citizen.sendMessage(ChatColor.RED + "/cc <message>;sends a message through clan chat.");
        citizen.sendMessage(ChatColor.RED + "/wallet ; checks how much server money you have.");
        
    }
}
