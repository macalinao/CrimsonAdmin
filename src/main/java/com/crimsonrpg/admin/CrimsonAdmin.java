/*1
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin;

import com.crimsonrpg.admin.listeners.CABlockListener;
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
import com.crimsonrpg.util.CrimsonCommand;
import org.bukkit.event.block.BlockListener;
import org.getspout.spoutapi.SpoutManager;
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
//        commands.put("spawn", new CommandSpawn());
        commands.put("customblock", new CommandCustomBlock());
        commands.put("clearinventory", new CommandClear());
        commands.put("sun", new CommandSun());
        commands.put("help", new CommandHelp());
        
        CrimsonCommand.registerAll(commands, this);
        EntityListener entityListener = new CAEntityListener(this);
        PlayerListener playerListener = new CAPlayerListener(this);
        BlockListener blockListener = new CABlockListener(this);
        
        //Registers the events
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvent(Type.ENTITY_DAMAGE, entityListener, Priority.Normal, this);
        pluginManager.registerEvent(Type.PLAYER_INTERACT_ENTITY, playerListener, Priority.Normal, this);
        pluginManager.registerEvent(Type.BLOCK_DAMAGE, blockListener, Priority.Normal, this);
        
        //Add to the spout cache
        SpoutManager.getFileManager().addToPreLoginCache(this, "http://resources.crimsonrpg.com/s/audio/audiobanhammer.ogg");
        
                LOGGER.info("[CrimsonAdmin] Plugin enabled.");
    }
}
