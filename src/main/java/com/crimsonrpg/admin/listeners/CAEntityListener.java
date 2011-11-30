/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.crimsonrpg.admin.CrimsonAdmin;
import com.crimsonrpg.items.api.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

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
        if (!(event instanceof EntityDamageByEntityEvent)) {
            return;
        }

        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
        Entity entity = event.getEntity();

        if (!(e.getDamager() instanceof Player)) {
            return;
        }

        if (!(entity instanceof Player)) {
            return;
        }

        SpoutPlayer player = (SpoutPlayer) e.getDamager();
        if (!player.hasPermission("crimson.rank.mod")) {
            return;
        }

        CustomItem banHammer = ItemAPI.getCrimsonItemManager().getItem("ban_hammer");
        CustomItem customItem = SpoutManager.getMaterialManager().getCustomItem(player.getItemInHand());
        if (customItem == null || !customItem.equals(banHammer)) {
            return;
        }

        final SpoutPlayer target = (SpoutPlayer) event.getEntity();
        Location loc = new Location(target.getWorld(), target.getLocation().getBlockX(), target.getLocation().getBlockY(), target.getLocation().getBlockZ());
        SpoutManager.getSoundManager().playGlobalCustomSoundEffect(ca, "http://resources.crimsonrpg.com/s/audios/banhammer.ogg", true, loc);

        target.setBanned(true);
        event.setCancelled(true);
        target.setVelocity(new Vector(0, 2, 0));

        Bukkit.getScheduler().scheduleSyncDelayedTask(ca, new Runnable() {

            public void run() {
                target.kickPlayer("Teh BanHammer haz spokez!! :D");
            }

        }, 100L);
    }

}