package com.mixed.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class removeCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		File f = new File(player.getName()+".txt");
		String line;
		String old = "";
		String[] parts;
		String fa = args[0]+":";
		int count = 0;
		if (!f.exists()) {
 			player.sendMessage(ChatColor.DARK_GREEN + "You have no saved locations.");
	 	}
			 try {
					BufferedReader r = new BufferedReader(new FileReader(f));
					
					while (true) {
						line = r.readLine();
						if (line == null) {
							if (count == 0) {
								player.sendMessage(ChatColor.RED + args[0] + ChatColor.GREEN + " is not a saved location.");
							}
							break;
						}
						parts = line.split("\\s+");
						if (fa.equalsIgnoreCase(parts[0])) {
							player.sendMessage(ChatColor.RED + args[0]+ ChatColor.GREEN + " successfully removed.");
							count = 1;
						} else {
						old = old + line + "\n";
						}
					}
					
					FileWriter w = new FileWriter(player.getName()+".txt");
					w.write(old);
					w.close();
			 } catch (IOException e) {
					
				}
		 
			 
		
		return false;
	}
}
