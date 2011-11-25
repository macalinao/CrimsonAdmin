/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin.listeners;

import com.crimsonrpg.admin.CrimsonAdmin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerListener;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.PopupScreen;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 *
 * @author Dylan
 */
public class CAPlayerListener extends PlayerListener{
    private CrimsonAdmin ca;
    
    public CAPlayerListener(CrimsonAdmin ca) {
        this.ca = ca;
    }
   
    @Override
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (entity instanceof Player) {
             final SpoutPlayer player = (SpoutPlayer) event.getPlayer();
            if (!player.hasPermission("crimson.rank.mod")) {
                    return;
                }
            final SpoutPlayer target = (SpoutPlayer) event.getRightClicked();
            String targetName = target.getName();
                PopupScreen popup = new GenericPopup(); 
                Button banButton = (Button) new GenericButton("Ban " + targetName).setWidth(200).setHeight(20); 
                Button kickButton = (Button) new GenericButton("Kick " + targetName).setWidth(200).setHeight(20);
                Button exitButton = (Button) new GenericButton ("Exit").setWidth(200).setHeight(20);
                
                
                banButton = new GenericButton("Ban") {
                    {
                        setWidth(200);
                        setHeight(20);
                        setX(100);
                        setY(100);
                        setColor(new Color(1.0F, 1.0F, 0, 1.0F)); 
                        setHoverColor(new Color(1.0F, 0, 0, 1.0F)); 
                    }
                    
                    @Override
                    public void onButtonClick(ButtonClickEvent event) {
                        target.setBanned(true);
                        target.kickPlayer("You have been banned by " + player.getName());
                        player.getMainScreen().closePopup();
                                }
                            };
                
                kickButton = new GenericButton("Kick") {
                    {
                        setWidth(200);
                        setHeight(20);
                        setX(100);
                        setY(125);
                        setColor(new Color(1.0F, 1.0F, 0, 1.0F)); 
                        setHoverColor(new Color(1.0F, 0, 0, 1.0F)); 
                    }
                    
                    @Override
                    public void onButtonClick(ButtonClickEvent event) {
                        target.kickPlayer("You have been kicked by " + player.getName());
                        player.getMainScreen().closePopup();
                                }
                            };
                
                exitButton = new GenericButton("Exit") {
                    {
                        setWidth(200);
                        setHeight(20);
                        setX(100);
                        setY(150);
                        setColor(new Color(1.0F, 1.0F, 0, 1.0F)); 
                        setHoverColor(new Color(1.0F, 0, 0, 1.0F));  
                    }
                    
                    @Override
                    public void onButtonClick(ButtonClickEvent event) {
                        player.getMainScreen().closePopup();
                                }
                            };
                
                popup.attachWidget(ca,banButton); 
                popup.attachWidget(ca,kickButton);
                popup.attachWidget(ca,exitButton);
                player.getMainScreen().attachPopupScreen(popup);
            }
        }
    
}
