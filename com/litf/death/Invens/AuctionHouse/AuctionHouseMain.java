package com.litf.death.Invens.AuctionHouse;

import com.litf.death.Events.RecipeTierEvents;
import com.litf.death.Main;
import com.litf.death.Ranks.Calls;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AuctionHouseMain implements InventoryHolder {
    private static Inventory inv;

    public AuctionHouseMain() {
        inv = Bukkit.createInventory(this,27, "Auction House");
        init();
    }
    private static void init(){
        for(int i = 0; i < 27; i++){
           switch(i){
               case 10:
                   inv.setItem(i,createItem(ChatColor.YELLOW+"View Active auctions", Material.GOLD_INGOT, null, (byte) 0));
                   break;
               case 13:
                   inv.setItem(i,createItem(ChatColor.YELLOW+"View your expired auctions", Material.CHEST, null, (byte) 0));
                   break;
               case 16:
                   inv.setItem(i,createItem(ChatColor.YELLOW+"Create an auction", Material.DIAMOND, null, (byte) 0));
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
