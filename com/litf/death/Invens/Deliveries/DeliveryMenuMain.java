package com.litf.death.Invens.Deliveries;

import com.litf.death.Events.RecipeTierEvents;
import com.litf.death.Items.Deliveries;
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

public class DeliveryMenuMain implements InventoryHolder {
    private static Inventory inv;
    public DeliveryMenuMain(Player p){
        inv = Bukkit.createInventory(this,27, "Deliveries");
        init(p);
    }
    private static void init(Player p){
        if (Main.data.getConfig().get(p.getUniqueId().toString()+".Deliveries")==null) {
            Main.data.getConfig().set(p.getUniqueId().toString()+".Deliveries", Arrays.asList());
            Main.data.saveConfig();
        }
        if(Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Deliveries.Items").size()==0){
            inv.setItem(13, createItem(ChatColor.RED+"No deliveries", Material.BEDROCK, Arrays.asList(
                    ChatColor.GRAY+"You have no deliveries, how sad :("
            ), (byte) 0));
        }else{
            for(Integer i:Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Deliveries.Items")){
                inv.addItem(Deliveries.items.get(i));
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
