/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.crimsonrpg.admin.commands.*;
import com.crimsonrpg.admin.listeners.CAEntityListener;
import com.crimsonrpg.admin.listeners.CAPlayerListener;
import com.crimsonrpg.plots.api.PlotAPI;
import com.crimsonrpg.util.CrimsonCommand;

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
        commands.put("customitem", new CommandCustomItem());
        commands.put("fakegamemode", new CommandFakeGameMode());
        commands.put("forceload", new CommandForceLoad());
//      commands.put("forcesave", new CommandForceReload(this));
        commands.put("forcesave", new CommandForceSave());
        commands.put("getpos", new CommandGetPos());
        commands.put("item", new CommandItem());
        commands.put("teleport", new CommandTeleport());
        commands.put("tpos", new CommandTeleportPosition());
        commands.put("setspawn", new CommandSetSpawn());
        commands.put("spawn", new CommandSpawn());
        commands.put("customblock", new CommandCustomBlock());
        
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
