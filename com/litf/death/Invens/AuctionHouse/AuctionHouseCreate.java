package com.litf.death.Invens.AuctionHouse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class AuctionHouseCreate implements InventoryHolder {
    private static Inventory inv;

    public AuctionHouseCreate() {
        inv = Bukkit.createInventory(this,45, "Create Auction");
        init();
    }
    private static void init(){
        for(int i = 0; i < 45; i++){
            switch(i){
                case 28:
                    inv.setItem(i,createItem(ChatColor.YELLOW+"Set Price", Material.GOLD_INGOT, Collections.singletonList(ChatColor.GRAY +"Current price: 10"), (byte) 0));
                    break;
                case 13:
                    inv.setItem(i,createItem(ChatColor.YELLOW+"Click the item you want to sell", Material.CHEST, null, (byte) 0));
                    break;
                case 34:
                    inv.setItem(i,createItem(ChatColor.YELLOW+"You cant create this auction", Material.STAINED_CLAY, null, (byte) 14));
                    break;
                default:
                    inv.setItem(i,createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
            }
        }
    }

    public static ItemStack createItem(String name, Material mat, List<String> lore, Byte b) {
        ItemStack item = new ItemStack(mat, 1, b);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createAir() {
        ItemStack item = new ItemStack(Material.AIR, 1);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
