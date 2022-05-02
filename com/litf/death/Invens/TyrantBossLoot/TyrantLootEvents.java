package com.litf.death.Invens.TyrantBossLoot;

import com.litf.death.Commands.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class TyrantLootEvents implements Listener {
    @EventHandler
    public static void invclk(InventoryClickEvent e){
        if(e.getInventory().getHolder() instanceof TyrantLoot){
            e.setCancelled(true);
            switch(e.getSlot()){
                case 39:
                    if(e.getClickedInventory().getItem(24).getType()!= Material.BEDROCK){
                        e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(20));
                        if(e.getClickedInventory().getItem(22).getType()!=Material.BEDROCK)
                        e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(22));
                        e.getInventory().setItem(1,new ItemStack(Material.STONE));
                        e.getWhoClicked().closeInventory();
                        e.getWhoClicked().sendMessage(ChatColor.GREEN+"Rewards Claimed");
                    }
                    break;
                case 40:
                    if(e.getClickedInventory().getItem(24).getType()== Material.BEDROCK){
                        e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(20));
                        if(e.getClickedInventory().getItem(22).getType()!=Material.BEDROCK)
                        e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(22));
                        e.getInventory().setItem(1,new ItemStack(Material.STONE));
                        e.getWhoClicked().closeInventory();
                        e.getWhoClicked().sendMessage(ChatColor.GREEN+"Rewards Claimed");
                    }
                    break;
                case 41:
                    if(e.getClickedInventory().getItem(24).getType()!= Material.BEDROCK){
                        e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(24));
                        e.getInventory().setItem(1,new ItemStack(Material.STONE));
                        e.getWhoClicked().closeInventory();
                        e.getWhoClicked().sendMessage(ChatColor.GREEN+"Bonus Reward Claimed");
                    }
                    break;
            }
        }
    }
    @EventHandler
    public static void clos(InventoryCloseEvent e){
        if(e.getInventory().getHolder() instanceof TyrantLoot) {
            if (e.getInventory().getItem(1).getType() != Material.STONE) {
                e.getPlayer().getInventory().addItem(e.getInventory().getItem(20));
                if (e.getInventory().getItem(22).getType() != Material.BEDROCK)
                    e.getPlayer().getInventory().addItem(e.getInventory().getItem(22));
                e.getPlayer().closeInventory();
                e.getPlayer().sendMessage(ChatColor.GREEN + "Rewards Claimed");
            }
        }
    }
}
