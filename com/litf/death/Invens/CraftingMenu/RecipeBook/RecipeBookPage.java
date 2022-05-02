package com.litf.death.Invens.CraftingMenu.RecipeBook;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class RecipeBookPage implements InventoryHolder {
    private static Inventory inv;
    public RecipeBookPage(List<ItemStack> stacks){
        inv = Bukkit.createInventory(this,45, stacks.get(9).getItemMeta().getDisplayName());
        init(stacks);
    }
    private static void init(List<ItemStack> stacks){
        int ii = 0;
        for(int i=0;i<45;i++){
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
                    if(stacks.get(ii)==null){
                        inv.setItem(i, createAir());
                    }else{
                        inv.setItem(i, stacks.get(ii));
                    }
                    ii++;
                    break;
                case 24:
                    inv.setItem(i, stacks.get(9));
                    break;
                case 40:
                    inv.setItem(i, createItem(ChatColor.YELLOW+"Go Back", Material.ARROW, null, (byte) 0));
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
