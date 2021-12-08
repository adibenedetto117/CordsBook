package com.mixed.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Main extends JavaPlugin {
	ArrayList<Player> toggled = new ArrayList<>();
	int temp = 0;
    @Override
    public void onEnable() {
        System.out.println("CordsBook has been enabled.");
        
        getCommand("sc").setExecutor(new SaveCordsCommand());
        getCommand("lic").setExecutor(new listCommand());
        getCommand("remove").setExecutor(new removeCommand());
       
        
    }

    @Override
    public void onDisable() {
       
    }

    public void buildScoreBoard(Player player) {
    	
    }
    

public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		Scoreboard board;
		File f = new File(player.getName()+".txt");
		String line;
		int count = 0;
		String[] parts;
		String li;
		
		String dis = null;
		String whole = null;
		int track = 0;
		
		
		if (cmd.getName().equals("toggle")) {
		try {
			if(args[0].equalsIgnoreCase("off") && !(toggled.contains(player))) {
				 player.sendMessage("Theres nothing to toggle off?");
				 track = 1;
			}
			 if (toggled.contains(player) ) {
				 if(args[0].equalsIgnoreCase("off")) {
					 if (temp == 1) {
					 player.sendMessage("Toggle off");
					 player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
					 toggled.remove(player);
					 temp = 0;
					 return true;
					 
					 } else {
						 player.sendMessage("Theres nothing to toggle off?");
					 }
					 
					}
			 }
			 if (!f.exists()) {
		 			player.sendMessage(ChatColor.DARK_GREEN + "You have no saved locations.");
		 		
			 	}
				BufferedReader x = new BufferedReader(new FileReader(f));
				li = x.readLine();
			 if (li == null) {
					player.sendMessage(ChatColor.DARK_GREEN + "You have no saved locations.");
					
			 }
			String fa = args[0]+":";
			BufferedReader r = new BufferedReader(new FileReader(f));
			
			while (true) {
				line = r.readLine();
				if (line == null) {
					break;
				}
				parts = line.split("\\s+");
				if (fa.equalsIgnoreCase(parts[0])) {
					count = 1;
					dis = args[0];
					whole = parts[1] +" "+ parts[2] +" "+ parts[3];
					break;
				} 
				
			}	
			
			if (count !=1 && track != 1) {
						player.sendMessage(ChatColor.RED + args[0] + ChatColor.GREEN + " is not a saved location.");
						
						count = 0;
						track = 0;
			}	
				
			
			if (args[0] == null) {
				
				 return false;
				
			} else if(count ==1) {
			 if (sender instanceof Player) {   
				 
			 } else  {
				 sender.sendMessage(ChatColor.RED + "Must be a player to use this command!");
				 return false;
			 }
			 
			 
			
			 
			 player.sendMessage("Toggle on");
			 
			 board = Bukkit.getScoreboardManager().getNewScoreboard();
			 Objective obj = board.registerNewObjective("test", "dummy");
			 obj.setDisplayName(ChatColor.RED.toString() + dis);
			 obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			 Score cords = obj.getScore(ChatColor.YELLOW + whole );
			 cords.setScore(1);
			 player.setScoreboard(board);
			 temp = 1;
			 
			 toggled.add(player);
			 return true;
			 
		 }
			count =  0;
		} catch(Exception e) {
			  player.sendMessage(ChatColor.RED+ "invalid input!");
		}
		}
		return false;
		
	}
}
