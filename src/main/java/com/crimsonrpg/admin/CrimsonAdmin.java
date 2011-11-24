/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin;

import com.crimsonrpg.economy.commands.CommandGimme;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.crimsonrpg.admin.commands.*;
import com.crimsonrpg.admin.listeners.CAEntityListener;
import com.crimsonrpg.admin.listeners.CAPlayerListener;
import com.crimsonrpg.citizens.api.CitizenAPI;
import com.crimsonrpg.plots.api.PlotAPI;
import com.crimsonrpg.util.CrimsonCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;

/**
 * The CrimsonAdmin plugin.
 */
public class CrimsonAdmin extends JavaPlugin {
    public static final Logger LOGGER = Logger.getLogger("Minecraft");
    
    public void onDisable() {
        LOGGER.info("[CrimsonAdmin] Plugin disabled.");
    }

    public void onEnable() {
        HashMap<String, CrimsonCommand> commands = new HashMap<String, CrimsonCommand>();
        commands.put("bring", new CommandBring());
        commands.put("fakegamemode", new CommandFakeGameMode());
        commands.put("forceload", new CommandForceLoad(this));
//        commands.put("forcesave", new CommandForceReload(this));
        commands.put("forcesave", new CommandForceSave(this));
        commands.put("getpos", new CommandGetPos());
        commands.put("item", new CommandItem());
        commands.put("teleport", new CommandTeleport());
        
        CrimsonCommand.registerAll(commands, this);
        EntityListener entityListener = new CAEntityListener(this);
        PlayerListener playerListener = new CAPlayerListener(this);
        
        //Registers the events
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvent(Type.ENTITY_DAMAGE, entityListener, Priority.Normal, this);
        pluginManager.registerEvent(Type.PLAYER_INTERACT_ENTITY, playerListener, Priority.Normal, this);
        
        
        
        LOGGER.info("[CrimsonAdmin] Plugin enabled.");
    }
    
    
    public void load() {
        LOGGER.info("[Crimson] Loading data...");
        this.reloadConfig();
        
        PlotAPI.getPlotManager().load();
    }

    public void save() {
        //Start saving...
        LOGGER.info("[Crimson] Saving data...");
        
        //Save complete
        LOGGER.info("[Crimson] Save complete.");
    }

    public void reset() {
        //TODO: this
    }
    
    public void reload() {
        save();
        load();
    }
}
