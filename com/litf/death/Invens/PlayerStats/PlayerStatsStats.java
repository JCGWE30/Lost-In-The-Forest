package com.litf.death.Invens.PlayerStats;

import com.litf.death.Events.RecipeTierEvents;
import com.litf.death.Events.Scoreboardinit;
import com.litf.death.Main;
import com.litf.death.Ranks.Calls;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PlayerStatsStats implements InventoryHolder {
    private static Inventory inv;

    public PlayerStatsStats(Player p) {
        inv = Bukkit.createInventory(this,18, p.getDisplayName());
        init(p);
    }
    private static void init(Player p){
        DateFormat simple=new SimpleDateFormat("dd/MM/yyyy");
        Date result = new Date(Main.data.getConfig().getLong(p.getUniqueId().toString()+".JoinDate"));
        RecipeTierEvents.confirmRecipes(p);
        List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes");
        int count = 0;
        for(Integer in:ints){
            if(in==0){
                count++;
            }
        }
        if (Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Kills").size()!=3) {
            Main.data.getConfig().set(p.getUniqueId().toString()+".Kills", Arrays.asList(0,0,0));
            Main.data.saveConfig();
        }
        List<Integer> kills = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Kills");
        for(int i = 0; i < 18; i++){
            switch (i){
                case 11:
                    ItemStack item = new ItemStack(Material.COMMAND, 1);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ChatColor.BLUE+"General Stats");
                    meta.setLore(Arrays.asList(
                            ChatColor.GRAY+"Join Date: "+simple.format(result),
                            ChatColor.GRAY+"Recipes: "+count+"/"+RecipeTierEvents.defints.size(),
                            ChatColor.GRAY+"Coins: "+ Scoreboardinit.getcoins(p)
                    ));
                    item.setItemMeta(meta);
                    inv.setItem(i, item);
                    break;
                case 15:
                    item = createItem(ChatColor.GRAY+"Mob Kills", Material.DIAMOND_SWORD, null, (byte) 0);
                    meta = item.getItemMeta();
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    meta.setLore(Arrays.asList(
                            ChatColor.GRAY+"Forest Skiters: "+kills.get(0),
                            ChatColor.GRAY+"Forest Guards: "+kills.get(1),
                            ChatColor.GRAY+"Forest Tyrants: "+ kills.get(2)
                            ));
                    item.setItemMeta(meta);
                    inv.setItem(i, item);
                    break;
                default:
                    inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
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
