package com.mixed.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaveCordsCommand implements CommandExecutor {
	
	public void cordsDataNew(String fname, String locName,double x, double y, double z) {
		String content = String.format(locName +": x%.2f, y%.2f, z%.2f\n",x,y,z);
		try {
			FileWriter w = new FileWriter(fname+".txt");
			w.write(content);
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void updateData(String fname, String locName,double x, double y, double z) {
		File f = new File(fname+".txt");
		String old = "";
		String line;
		String content = String.format(locName +": x%.2f, y%.2f, z%.2f",x,y,z);
		
		try {
			BufferedReader r = new BufferedReader(new FileReader(f));
			
			while (true) {
				line = r.readLine();
				if (line == null) {
					old = old + content;
					break;
				}
				old = old + line + "\n";
			}
			FileWriter w = new FileWriter(fname+".txt");
			w.write(old);
			w.close();
			
		} catch (IOException e) {
			
		}
	}
	//sc <name>
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		Location loc = player.getLocation();
		File file = new File(player.getName()+".txt");
		try {
        if (!args[0].equalsIgnoreCase("Placerholder1x3")) {
        	 if (sender instanceof Player) {   
        		
        		 
        		 if (!file.exists()) {
        			 
	        		 cordsDataNew(player.getName(),args[0],loc.getX(),loc.getY(),loc.getZ());
	        		 player.sendMessage(ChatColor.GRAY +"Your cords for " + ChatColor.GREEN + args[0] + ChatColor.GRAY + " have been saved." );
	        		 
        		 } else {
        			updateData(player.getName(),args[0],loc.getX(),loc.getY(),loc.getZ());
        			player.sendMessage(ChatColor.GRAY +"Your cords for " + ChatColor.GREEN + args[0] + ChatColor.GRAY + " have been saved." );
        			 
        		 }
        	
        	 } else {
        		 sender.sendMessage(ChatColor.RED + "Must be a player to use this command!");
             }
        } 
        
		} catch(Exception e) {
			player.sendMessage(ChatColor.RED + "Not a valid Input");
		}
		
		
        return false;
    }
}
