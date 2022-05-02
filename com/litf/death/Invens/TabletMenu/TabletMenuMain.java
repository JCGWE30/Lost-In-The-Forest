package com.litf.death.Invens.TabletMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabletMenuMain implements InventoryHolder {
    private static Inventory inv;
    public TabletMenuMain(){
        inv = Bukkit.createInventory(this,27, "Tablet Menu");
        init();
    }
    private static void init(){
        for(int i=0;i<27;i++){
            switch(i){
                case 10:
                    inv.setItem(i, createItem(ChatColor.YELLOW+"Crafting Menu", Material.WORKBENCH, Collections.singletonList(ChatColor.GOLD + "Click to open the crafting menu"), (byte) 0));
                    break;
                case 12:
                    inv.setItem(i, createItem(ChatColor.YELLOW+"Anvil Menu", Material.ANVIL, Collections.singletonList(ChatColor.GOLD + "Click to open the anvil menu"), (byte) 0));
                    break;
                case 14:
                    inv.setItem(i, createItem(ChatColor.YELLOW+"Storage Menu", Material.CHEST, Collections.singletonList(ChatColor.GOLD + "Click to open the storage menu"), (byte) 0));
                    break;
                case 16:
                    inv.setItem(i, createItem(ChatColor.RED+"LOCKED", Material.BEDROCK, Collections.singletonList(ChatColor.RED + "This tab is locked"), (byte) 0));
                    break;
                default:
                    inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
            }
        }
    }
    public static ItemStack createItem(String name, Material mat, List<String> lore, Byte b){
        ItemStack item = new ItemStack(mat, 1, b);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack createAir(){
        ItemStack item = new ItemStack(Material.AIR, 1);
        return item;
    }
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
