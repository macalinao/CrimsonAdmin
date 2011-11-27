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

                CustomItem banHammer = ItemAPI.getCrimsonItemManager().getItem("ban_hammer");
                CustomItem customItem = SpoutManager.getMaterialManager().getCustomItem(player.getItemInHand());
                if (customItem == null || !customItem.equals(banHammer)) {
                    return;
                }

                if (entity instanceof Player) {
                    SpoutPlayer target = (SpoutPlayer) event.getEntity();
                    target.setBanned(true);
                    target.kickPlayer("Teh BanHammer haz spokez!! :D");
                    event.setCancelled(true);
                }
            }
        }
    }
}