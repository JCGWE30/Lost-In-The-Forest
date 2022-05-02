package com.litf.death.Invens.CraftingMenu;

import com.litf.death.Items.Misc;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CraftingMenuMain implements InventoryHolder {
    private static Inventory inv;
    public CraftingMenuMain(){
        inv = Bukkit.createInventory(this,54, "Crafting Menu");
        init();
    }
    private static void init(){
        for(int i=0;i<54;i++){
            switch(i){
                case 10:
                case 11:
                case 12:
                case 19:
                case 20:
                case 21:
                case 28:
                case 29:
                case 30:
                    inv.setItem(i, createAir());
                    break;
                case 24:
                    inv.setItem(i, createItem(ChatColor.RED+"Crafting Output", Material.BARRIER, Arrays.asList(
                            ChatColor.GRAY+"This is where your item will",
                            ChatColor.GRAY+"appear, if the recipe is correct"
                    ), (byte) 0));
                    break;
                case 33:
                    inv.setItem(i, createItem(ChatColor.AQUA+"Attempt to craft", Material.ANVIL, Arrays.asList(
                            ChatColor.GRAY+"Attempt to craft your item, and see",
                            ChatColor.GRAY+"if your recipe is correct"
                    ), (byte) 0));
                    break;
                case 42:
                    inv.setItem(i, createItem(ChatColor.YELLOW+"Recipe book", Material.BOOK, Arrays.asList(
                            ChatColor.GRAY+"Shows the things you can craft and",
                            ChatColor.GRAY+"how to craft them."
                    ), (byte) 0));
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
