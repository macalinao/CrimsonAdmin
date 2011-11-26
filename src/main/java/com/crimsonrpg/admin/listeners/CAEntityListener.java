/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.listeners;

import com.crimsonrpg.admin.CrimsonAdmin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Dylan
 */
public class CAEntityListener extends EntityListener {
    private CrimsonAdmin ca;

    public CAEntityListener(CrimsonAdmin ca) {
        this.ca = ca;
    }
    
    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
            Entity entity = event.getEntity();
            if (e.getDamager() instanceof Player) {
                SpoutPlayer player = (SpoutPlayer) e.getDamager();
                if (!player.hasPermission("crimson.rank.mod")) {
                    return;
                }
                if (entity instanceof Player && player.getItemInHand().getType().equals(Material.FLINT)) {
                    SpoutPlayer target = (SpoutPlayer) event.getEntity();
                    Location loc = new Location(target.getWorld(), target.getLocation().getBlockX(),target.getLocation().getBlockY(),target.getLocation().getBlockZ());
                    SpoutManager.getSoundManager().playGlobalCustomSoundEffect(ca, "http://resources.crimsonrpg.com/s/audio/BanHammerAudio.ogg", true, loc);
                    target.setBanned(true); 
                    target.kickPlayer("Have fun enjoing your pointless life. BTW I won. :D");
                    event.setCancelled(true);
                }
            }
        }
    }
}