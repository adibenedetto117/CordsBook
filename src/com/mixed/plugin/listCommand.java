package com.mixed.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class listCommand  implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		File f = new File(player.getName()+".txt");
		
		String line;
		String li;
		int count = 0;
		String old = "";
		 if (!f.exists()) {
	 			player.sendMessage(ChatColor.DARK_GREEN + "You have no saved locations.");
		 	}
			 try {
					BufferedReader r = new BufferedReader(new FileReader(f));
					BufferedReader x = new BufferedReader(new FileReader(f));
					li = x.readLine();
					if (li == null) {
						player.sendMessage(ChatColor.DARK_GREEN + "You have no saved locations.");
						count = 1;
					}
					
					while (true) {
						line = r.readLine();
						if (line == null) {
							break;
						}
						old = old + line + "\n";
					}
					if (count == 0) {
						player.sendMessage(ChatColor.DARK_GREEN + old);
					}
					count = 0;
					
			 } catch (IOException e) {
					
				}
					
		 
		return false;
		}
	}
	



