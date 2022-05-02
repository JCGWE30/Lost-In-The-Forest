package com.litf.death.Invens.ItemMenu;

import com.litf.death.Items.Misc;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemMenuMisc implements InventoryHolder {
    private static Inventory inv;
    public ItemMenuMisc(){
        int num = (int) Math.ceil((double) Misc.items.size()/9)*9;
        if(Math.ceil((double)Misc.items.size()/9)*9==0){
            num = 9;
        }
        inv = Bukkit.createInventory(this,num+9, "Misc");
        init(num+9);
    }
    private static void init(int num){
        for(int i = 0; i < num; i++){
            if(i>num-10){
                inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
                if(num-5==i){
                    inv.setItem(i, createItem(ChatColor.YELLOW+"Go Back", Material.ARROW, null, (byte)0));
                }
            }else if(Misc.items.size()-1>=i){
                ItemStack item = Misc.items.get(i).clone();
                ItemMeta meta = item.getItemMeta();
                List<String> lore = new ArrayList<>();
                if(meta.hasLore()) {
                    lore = meta.getLore();
                }
                lore.add(" ");
                lore.add(ChatColor.DARK_BLUE+"ID: "+ Misc.items.indexOf(item));
                lore.add(" ");
                lore.add(ChatColor.BLUE+"Left Click for one");
                lore.add(ChatColor.GREEN+"Right Click for a stack");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(i, item);
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
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
