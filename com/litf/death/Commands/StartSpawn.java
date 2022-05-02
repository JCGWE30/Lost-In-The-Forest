package com.litf.death.Commands;

import com.litf.death.Entits.ForestGuard;
import com.litf.death.Entits.ForestSkiter;
import com.litf.death.Events.SpawnEvents;
import com.litf.death.Ranks.Calls;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class StartSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cms, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(Calls.getRank(p)>=3) {
                if (SpawnEvents.go) {
                    SpawnEvents.go = false;
                    p.sendMessage(ChatColor.GREEN + "Disabling Spawns");
                    for (ForestSkiter spid : SpawnEvents.spids) {
                        Entity ent = spid.getBukkitEntity();
                        ent.remove();
                    }
                    for (ForestGuard spid : SpawnEvents.grds) {
                        Entity ent = spid.getBukkitEntity();
                        ent.remove();
                    }
                    SpawnEvents.spids.clear();
                    SpawnEvents.grds.clear();
                } else {
                    SpawnEvents.go = true;
                    p.sendMessage(ChatColor.GREEN + "Enabling Spawns");
                    SpawnEvents.randomspawn(p.getWorld());
                }
            }else{
                p.sendMessage(ChatColor.RED+"You must be an admin or higher to use this command");
            }
        }
        return true;
    }
}
