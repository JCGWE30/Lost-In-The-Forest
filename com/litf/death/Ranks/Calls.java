package com.litf.death.Ranks;

import com.litf.death.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Calls {
    public static Integer getRank(Player p){
        if(Main.serverdata.getConfig().getStringList("Ranks.owner").contains(p.getUniqueId().toString()))
            return 4;
        if(Main.serverdata.getConfig().getStringList("Ranks.admin").contains(p.getUniqueId().toString()))
            return 3;
        if(Main.serverdata.getConfig().getStringList("Ranks.mod").contains(p.getUniqueId().toString()))
            return 2;
        if(Main.serverdata.getConfig().getStringList("Ranks.builder").contains(p.getUniqueId().toString()))
            return 1;
        return 0;
    }
    public static String getStringRank(Player p){
        if(Main.serverdata.getConfig().getStringList("Ranks.owner").contains(p.getUniqueId().toString()))
            return ChatColor.RED+"[Owner]";
        if(Main.serverdata.getConfig().getStringList("Ranks.admin").contains(p.getUniqueId().toString()))
            return ChatColor.RED+"[Admin]";
        if(Main.serverdata.getConfig().getStringList("Ranks.builder").contains(p.getUniqueId().toString()))
            return ChatColor.YELLOW+"[Builder]";
        if(Main.serverdata.getConfig().getStringList("Ranks.mod").contains(p.getUniqueId().toString()))
            return ChatColor.GREEN+"[Moderator]";
        return ChatColor.GRAY+"Default";
    }
}
