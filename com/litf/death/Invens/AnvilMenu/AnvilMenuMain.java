package com.litf.death.Invens.AnvilMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class AnvilMenuMain implements InventoryHolder {
    private static Inventory inv;
    public AnvilMenuMain(){
        inv = Bukkit.createInventory(this,54, "Crafting Menu");
        init();
    }
    private static void init(){
        for(int i=0;i<54;i++){
            switch(i){
                case 19:
                case 25:
                    inv.setItem(i, createAir());
                    break;
                case 28:
                case 37:
                case 38:
                case 39:
                case 34:
                case 41:
                case 42:
                case 43:
                    inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
                    break;
                case 40:
                    inv.setItem(i,createItem(ChatColor.RED+"Anvil Output", Material.BARRIER, Arrays.asList(
                            ChatColor.GRAY+"if the 2 items above can be combined",
                            ChatColor.GRAY+"then the output item will appear in this slot"), (byte) 0));
                    break;
                case 31:
                    inv.setItem(i,createItem(ChatColor.AQUA+"Combine", Material.ANVIL, Arrays.asList(ChatColor.GRAY+"Combine the 2 items"), (byte) 0));
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
