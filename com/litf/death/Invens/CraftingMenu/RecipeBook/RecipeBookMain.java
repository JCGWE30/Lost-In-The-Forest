package com.litf.death.Invens.CraftingMenu.RecipeBook;

import com.avaje.ebean.Page;
import com.litf.death.Events.RecipeTierEvents;
import com.litf.death.Invens.CraftingMenu.CraftingMenuEvents;
import com.litf.death.Items.Misc;
import com.litf.death.Items.Weapons;
import com.litf.death.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class RecipeBookMain implements InventoryHolder {
    private static Inventory inv;

    public RecipeBookMain(Player p, int PageID) {
        int div = 0;
        switch (PageID){
            case 2:
                div=26;
                break;
        }
        int num = (int) Math.ceil((float)(CraftingMenuEvents.recs.size()-div)/9)*9;
        if(Math.floor((CraftingMenuEvents.recs.size()-div)/9)*9==0){
            num = 9;
        }
        String name = "Recipes";
        switch (PageID){
            case 1:
                name = "Forest Recipes";
                break;
            case 2:
                name ="The Underground Recipes";
                break;
        }
        inv = Bukkit.createInventory(this,num+9, name);
        init(num+9, p, PageID, div);
    }
    private static void init(int num, Player p, int PageID, int div){
        inv.clear();
        for(int i = div; i < num+div; i++){
            if(i-div>num-10){
                if(i-div==num-5){
                    inv.setItem(i-div, createItem(ChatColor.YELLOW+"Go Back", Material.ARROW, null, (byte) 0));
                }else if(i-div==num-1){
                    inv.setItem(i-div, createItem(ChatColor.YELLOW+"Next Section", Material.ARROW, null, (byte) 0));
                }else {
                    inv.setItem(i-div, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
                }
        }else if(CraftingMenuEvents.recs.size()-1>=i) {
                if (PageID==1&&i<26) {
                    if (Main.data.getConfig().get(p.getUniqueId().toString() + ".Recipes") == null) {
                        Main.data.getConfig().set(p.getUniqueId().toString() + ".Recipes", RecipeTierEvents.defints);
                        Main.data.saveConfig();
                    }
                    List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString() + ".Recipes");
                    if (ints.get(i) > 0) {
                        CharSequence cs = ints.get(i).toString();
                        CharSequence cf = "%";
                        inv.setItem(i, createItem(ChatColor.RED + "Recipe Locked", Material.INK_SACK, Arrays.asList(ChatColor.GRAY + RecipeTierEvents.strings.get(i).replace(cf, cs)), (byte) 8));
                    } else {
                        inv.setItem(i, CraftingMenuEvents.recs.get(i).get(9));
                    }
                }else if (PageID==2) {
                    if (Main.data.getConfig().get(p.getUniqueId().toString() + ".Recipes") == null) {
                        Main.data.getConfig().set(p.getUniqueId().toString() + ".Recipes", RecipeTierEvents.defints);
                        Main.data.saveConfig();
                    }
                    List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString() + ".Recipes");
                    if (ints.get(i) > 0) {
                        CharSequence cs = ints.get(i).toString();
                        CharSequence cf = "%";
                        inv.setItem(i-26, createItem(ChatColor.RED + "Recipe Locked", Material.INK_SACK, Arrays.asList(ChatColor.GRAY + RecipeTierEvents.strings.get(i).replace(cf, cs)), (byte) 8));
                    } else {
                        inv.setItem(i-div, CraftingMenuEvents.recs.get(i).get(9));
                    }
                }
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
