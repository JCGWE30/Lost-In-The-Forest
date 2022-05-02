package com.litf.death.Commands;

import com.litf.death.Invens.PlayerStats.PlayerStatsMenu;
import com.litf.death.Main;
import com.litf.death.Ranks.Calls;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class stats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cms, String label, String[] args) {
        if(sender instanceof Player) {
            if(args.length==0){
                PlayerStatsMenu gui = new PlayerStatsMenu((Player)sender);
                ((Player)sender).openInventory(gui.getInventory());
            }else{
                try{
                    PlayerStatsMenu gui = new PlayerStatsMenu(Bukkit.getPlayer(args[0]));
                    ((Player)sender).openInventory(gui.getInventory());
                }catch (NumberFormatException ignored){

                }
            }
    }
        return true;
}
}
