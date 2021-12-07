package com.mixed.plugin;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ToggleListener implements Listener,CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		ArrayList<String> toggled = new ArrayList<>();
		try {
			if (args[0] == null) {
				
				 return false;
				
			} else {
			 if (sender instanceof Player) {   
				 
			 } else {
				 sender.sendMessage(ChatColor.RED + "Must be a player to use this command!");
				 return false;
			 }
			 
			 
			 if (toggled.contains(player.getName())) {
				 player.sendMessage("Toggle off");
				 toggled.remove(player.getName());
				 return true;
			 }
			 
			 player.sendMessage("Toggle on");
			 toggled.add(player.getName());
			 return true;
		
		 }
		} catch(Exception e) {
			  player.sendMessage(ChatColor.RED+ "missing location name!");
		}
		
		return false;
	}
}
