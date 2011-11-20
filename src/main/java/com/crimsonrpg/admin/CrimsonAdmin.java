/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crimsonrpg.admin;

import com.crimsonrpg.admin.commands.*;
import com.crimsonrpg.coreapi.util.CrimsonCommand;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

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
        commands.put("forceload", new CommandForceLoad());
        commands.put("forcesave", new CommandForceReload());
        commands.put("forcesave", new CommandForceSave());
        commands.put("gimme", new CommandGimme());
        commands.put("item", new CommandItem());
        commands.put("teleport", new CommandTeleport());
        
        CrimsonCommand.registerAll(commands, this);
        
        LOGGER.info("[CrimsonAdmin] Plugin enabled.");
    }
    
}
