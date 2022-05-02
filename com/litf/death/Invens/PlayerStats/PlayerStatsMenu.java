package com.litf.death.Invens.PlayerStats;

import com.litf.death.Events.RecipeTierEvents;
import com.litf.death.Events.Scoreboardinit;
import com.litf.death.Invens.CraftingMenu.CraftingMenuEvents;
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
import java.util.*;

public class PlayerStatsMenu implements InventoryHolder {
    private static Inventory inv;

    public PlayerStatsMenu(Player p) {
        inv = Bukkit.createInventory(this,54, p.getDisplayName());
        init(p);
    }
    private static void init(Player p){
        PlayerInventory pinv = p.getInventory();
        for(int i = 0; i < 54; i++){
            switch (i){
                case 11:
                    inv.setItem(i, createItem(ChatColor.GRAY+"Cap", Material.STAINED_GLASS_PANE, null, (byte) 0));
                    if(pinv.getHelmet()!=null){
                        inv.setItem(i, pinv.getHelmet());
                    }
                    break;
                case 20:
                    inv.setItem(i, createItem(ChatColor.GRAY+"Shirt", Material.STAINED_GLASS_PANE, null, (byte) 0));
                    if(pinv.getChestplate()!=null){
                        inv.setItem(i, pinv.getChestplate());
                    }
                    break;
                case 29:
                    inv.setItem(i, createItem(ChatColor.GRAY+"Pants", Material.STAINED_GLASS_PANE, null, (byte) 0));
                    if(pinv.getLeggings()!=null){
                        inv.setItem(i, pinv.getLeggings());
                    }
                    break;
                case 38:
                    inv.setItem(i, createItem(ChatColor.GRAY+"Boots", Material.STAINED_GLASS_PANE, null, (byte) 0));
                    if(pinv.getBoots()!=null){
                        inv.setItem(i, pinv.getBoots());
                    }
                    break;
                case 22:
                    inv.setItem(i, createItem(ChatColor.GRAY+"Hand", Material.STAINED_GLASS_PANE, null, (byte) 0));
                    if(pinv.getItemInHand().getType()!=Material.AIR){
                        inv.setItem(i, pinv.getItemInHand());
                    }
                    break;
                case 40:
                    String id = " ";
                    inv.setItem(i, createItem(ChatColor.GRAY+"Cube", Material.STAINED_GLASS_PANE, null, (byte) 0));
                    for(int y = 0;y<8;y++){
                        ItemStack item = pinv.getItem(y);
                        try {
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            id = lis.get(0).getString("id");
                            if(id.equals("GUARD_CUBE")){
                                inv.setItem(i, item);
                            }
                        }catch(NullPointerException ignored){

                        }
                    }
                    break;
                case 24:
                    ItemStack item = new ItemStack(Material.SKULL_ITEM, 1 , (short) 3);
                    SkullMeta meta = (SkullMeta) item.getItemMeta();
                    meta.setDisplayName(Calls.getStringRank(p)+" "+p.getName());
                    meta.setOwner(p.getName());
                    meta.setLore(Collections.singletonList(
                            ChatColor.YELLOW + "Click to view this players stats"
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
