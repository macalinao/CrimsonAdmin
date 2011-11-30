/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.listeners;

import com.crimsonrpg.admin.CrimsonAdmin;
import com.crimsonrpg.items.api.ItemAPI;
import org.bukkit.Location;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Dylan
 */
public class CABlockListener extends BlockListener {

    private CrimsonAdmin ca;

    public CABlockListener(CrimsonAdmin ca) {
        this.ca = ca;
    }

    @Override
    public void onBlockDamage(BlockDamageEvent event) {
        SpoutPlayer player = (SpoutPlayer) event.getPlayer();
        CustomItem banHammer = ItemAPI.getCrimsonItemManager().getItem("ban_hammer");
        CustomItem customItem = SpoutManager.getMaterialManager().getCustomItem(player.getItemInHand());
        
        
        if (customItem == null || !customItem.equals(banHammer)) {
            return;
        }
        
        if (customItem.equals(banHammer)) {
            Location loc = new Location(event.getBlock().getWorld(), event.getBlock().getLocation().getBlockX(), event.getBlock().getLocation().getBlockY(), event.getBlock().getLocation().getBlockZ()); 
            SpoutManager.getSoundManager().playGlobalCustomSoundEffect(ca, "http://resources.crimsonrpg.com/s/audios/banhammer.ogg", true, loc);
            event.setCancelled(true);
        }
    }
}
