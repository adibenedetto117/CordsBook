package com.mixed.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("CordsBook has been enabled.");
        
        getCommand("sc").setExecutor(new SaveCordsCommand());
        getCommand("lic").setExecutor(new listCommand());
        getCommand("remove").setExecutor(new removeCommand());
        getCommand("toggle").setExecutor(new ToggleListener());
        
    }

    @Override
    public void onDisable() {
       
    }

    
}
