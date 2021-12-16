package com.mixed.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Main extends JavaPlugin{
	ArrayList<Player> toggled = new ArrayList<>();
	int temp = 0;
	Scoreboard board;
	Objective obj;
	
    @Override
    public void onEnable() {
        System.out.println("CordsBook has been enabled.");
        
        //Bukkit.getPluginManager().registerEvents(this, this);
        
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
				 player.sendMessage(ChatColor.RED + "Theres nothing to toggle off?");
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
			 temp = 1;
			 board = Bukkit.getScoreboardManager().getNewScoreboard();
			 obj = board.registerNewObjective("test", "dummy");
			 obj.setDisplayName(ChatColor.RED.toString() + dis);
			 obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			 
			
			 Score cords = obj.getScore(ChatColor.YELLOW + whole );
			 cords.setScore(1);
			 player.setScoreboard(board);
			 
			 
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
@EventHandler
	public void onMove(PlayerMoveEvent e) {
	
   	Player player =	e.getPlayer();
   	Location loc = player.getLocation();
   	double x = loc.getBlockX();
   	double y = loc.getBlockY();
   	double z = loc.getBlockZ();
   	
   
   	String total = "X"+String.valueOf(x)+" Y"+String.valueOf(y)+" Z"+String.valueOf(z);
   	if (temp == 1) {
	    Team bb = board.registerNewTeam("current");
		 bb.addEntry(ChatColor.AQUA.toString());
		 bb.setPrefix("CurrentCords: ");
		 bb.setSuffix(ChatColor.RED + total);
		 obj.getScore(ChatColor.AQUA.toString()).setScore(3);
	   	}
   	
		
	}
		
}
