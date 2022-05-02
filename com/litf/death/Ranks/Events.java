package com.litf.death.Ranks;

import com.litf.death.Invens.AuctionHouse.AuctionHouseEvents;
import com.litf.death.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;

public class Events implements Listener {
    public static Main plugin;

    public Events(Main plugin){
        savePlugin(plugin);
    }
    public static void savePlugin(Main plugi){
        Events.plugin=plugi;
    }
    @EventHandler
    public static void talk(AsyncPlayerChatEvent e){
        if(AuctionHouseEvents.pinv.get(e.getPlayer())==null){
        if(Calls.getRank(e.getPlayer())==4){
            e.setCancelled(true);
            Bukkit.broadcastMessage(ChatColor.RED+"[Owner] "+e.getPlayer().getName()+": "+ChatColor.WHITE+e.getMessage());
        }
        if(Calls.getRank(e.getPlayer())==3){
            e.setCancelled(true);
            Bukkit.broadcastMessage(ChatColor.RED+"[Admin] "+e.getPlayer().getName()+": "+ChatColor.WHITE+e.getMessage());
        }
        if(Calls.getRank(e.getPlayer())==1){
            e.setCancelled(true);
            Bukkit.broadcastMessage(ChatColor.YELLOW+"[Builder] "+e.getPlayer().getName()+": "+ChatColor.WHITE+e.getMessage());
        }
        if(Calls.getRank(e.getPlayer())==2){
            e.setCancelled(true);
            Bukkit.broadcastMessage(ChatColor.GREEN+"[Moderator] "+e.getPlayer().getName()+": "+ChatColor.WHITE+e.getMessage());
        }
        if(Calls.getRank(e.getPlayer())==0){
            e.setCancelled(true);
            Bukkit.broadcastMessage(ChatColor.GRAY+e.getPlayer().getName()+": "+ChatColor.WHITE+e.getMessage());
        }
    }}
    @EventHandler
    public static void join(PlayerJoinEvent e){
       PermissionAttachment atat = e.getPlayer().addAttachment(plugin);
        if(Calls.getRank(e.getPlayer())>0){
            atat.setPermission("minecraft.command.gamemode", true);
        }
    }
}
