package com.litf.death.Commands;

import com.litf.death.Entits.ForestGuard;
import com.litf.death.Entits.ForestSkiter;
import com.litf.death.Events.SpawnEvents;
import com.litf.death.Main;
import com.litf.death.Ranks.Calls;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class refreshspawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cms, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(Calls.getRank(p)>=3) {
                SpawnEvents.randomspawn(p.getWorld());
                p.sendMessage(ChatColor.GREEN+"Spawns Refreshed");
            }else{
                p.sendMessage(ChatColor.RED+"You must be an admin or higher to use this command");
            }
        }
        return true;
    }
}
