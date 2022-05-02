package com.litf.death.Invens.PlayerStats;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerStatsEvents implements Listener {
    @EventHandler
    public static void plyclick(PlayerInteractAtEntityEvent e){
        if(e.getRightClicked()!=null&&e.getPlayer()!=null) {
            if (e.getRightClicked() instanceof Player) {
                PlayerStatsMenu gui = new PlayerStatsMenu((Player)e.getRightClicked());
                e.getPlayer().openInventory(gui.getInventory());
            }
        }
    }
    @EventHandler
    public static void denyclick(InventoryClickEvent e){
        if(e.getInventory().getHolder() instanceof PlayerStatsMenu){
            e.setCancelled(true);
            if(e.getSlot()==24){
                e.getWhoClicked().sendMessage(Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().split(" ")[1]).getName()+" "+e.getCurrentItem().getItemMeta().getDisplayName().split(" ")[1]);
                PlayerStatsStats gui = new PlayerStatsStats(Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().split(" ")[1]));
                e.getWhoClicked().openInventory(gui.getInventory());
            }
        }
        if(e.getInventory().getHolder() instanceof PlayerStatsStats){
            e.setCancelled(true);
        }
    }
}
