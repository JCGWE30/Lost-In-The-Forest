package com.litf.death.Invens.Deliveries;

import com.litf.death.Items.Deliveries;
import com.litf.death.Main;
import com.litf.death.Ranks.Calls;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DeliveryMenuEvents implements Listener {
    @EventHandler
    public static void click(InventoryClickEvent e){
        if(e.getInventory().getHolder() instanceof DeliveryMenuMain){
            e.setCancelled(true);
            if(e.getCurrentItem().getType()!= Material.AIR&&e.getCurrentItem().getType()!=Material.BEDROCK){
                Player p = (Player) e.getWhoClicked();
                ItemStack item = Deliveries.items.get(Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Deliveries.Items").get(e.getSlot())).clone();
                ItemMeta meta = item.getItemMeta();
                List<String> lore = new ArrayList<>();
                if(meta.hasLore()){
                    lore=meta.getLore();
                }
                if(Main.serverdata.getConfig().getIntegerList("Counts.Deliveries")==null){
                    List<Integer> ints = new ArrayList<>();
                    for(ItemStack ignored :Deliveries.items){
                        ints.add(0);
                    }
                    Main.serverdata.getConfig().set("Counts.Deliveries", ints);
                }
                if(Main.serverdata.getConfig().getIntegerList("Counts.Deliveries").size()<Deliveries.items.size()){
                    List<Integer> ints = Main.serverdata.getConfig().getIntegerList("Counts.Deliveries");
                    for(int i=0;i<Deliveries.items.size();i++){
                        ints.add(0);
                    }
                    Main.serverdata.getConfig().set("Counts.Deliveries", ints);
                    Main.serverdata.saveConfig();
                }
                lore.add(" ");
                lore.add(ChatColor.DARK_GRAY+"From: "+Main.data.getConfig().getStringList(p.getUniqueId().toString()+".Deliveries.Names").get(e.getSlot()));
                lore.add(ChatColor.DARK_GRAY+"To: "+ Calls.getStringRank(p)+" "+p.getName());
                lore.add(ChatColor.DARK_GRAY+"Generation "+ Main.serverdata.getConfig().getIntegerList("Counts.Deliveries").get(Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Deliveries.Items").get(e.getSlot())));
                List<Integer> countsg = Main.serverdata.getConfig().getIntegerList("Counts.Deliveries");
                int countg = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Deliveries.Items").get(e.getSlot());
                System.out.println(countg);
                countsg.set(countg, countsg.get(countg)+1);
                Main.serverdata.getConfig().set("Counts.Deliveries", countsg);
                Main.serverdata.saveConfig();
                meta.setLore(lore);
                item.setItemMeta(meta);
                e.getWhoClicked().getInventory().addItem(item);
                List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Deliveries.Items");
                ints.remove(e.getSlot());
                Main.data.getConfig().set(p.getUniqueId().toString()+".Deliveries.Items", ints);
                List<String> strs = Main.data.getConfig().getStringList(p.getUniqueId().toString()+".Deliveries.Names");
                strs.remove(e.getSlot());
                Main.data.getConfig().set(p.getUniqueId().toString()+".Deliveries.Names", strs);
                Main.data.saveConfig();
                DeliveryMenuMain gui = new DeliveryMenuMain(p);
                p.openInventory(gui.getInventory());
            }
        }
    }
}
