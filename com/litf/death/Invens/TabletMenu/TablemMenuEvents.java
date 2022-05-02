package com.litf.death.Invens.TabletMenu;

import com.litf.death.Events.DestinyEvents;
import com.litf.death.Events.UtilEvents;
import com.litf.death.Invens.AnvilMenu.AnvilMenuMain;
import com.litf.death.Invens.CraftingMenu.CraftingMenuMain;
import com.litf.death.Main;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TablemMenuEvents implements Listener {
    @EventHandler
    public static void clickers(InventoryClickEvent e){
        if(e.getInventory().getHolder() instanceof TabletMenuMain){
            e.setCancelled(true);
            switch(e.getSlot()){
                case 10:
                    CraftingMenuMain gui = new CraftingMenuMain();
                    e.getWhoClicked().openInventory(gui.getInventory());
                    break;
                case 12:
                    AnvilMenuMain guia = new AnvilMenuMain();
                    e.getWhoClicked().openInventory(guia.getInventory());
                    break;
                case 14:
                    TabletMenuStorage guis = new TabletMenuStorage((Player) e.getWhoClicked());
                    e.getWhoClicked().openInventory(guis.getInventory());
                    break;
            }
        }
    }
    @EventHandler
    public static void closesave(InventoryCloseEvent e){
        if(e.getInventory().getHolder() instanceof TabletMenuStorage){
            int i = 0;
            for(ItemStack item:e.getView().getTopInventory()){
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Storage." + i, null);
                try {
                    int count = 0;
                    try {
                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                        NBTTagCompound comp = iten.getTag();
                        NBTTagList lis = (NBTTagList) comp.get("comp");
                        count = lis.get(5).getInt("DestinyCount");
                        comp.set("comp", lis);
                    }catch(Exception ex){
                    }
                    Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Storage." + i + ".ID", UtilEvents.getId(item));
                    Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Storage." + i + ".Count", item.getAmount());
                    Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Storage." + i + ".Destiny", DestinyEvents.getDestiny(item).split("\\.")[0]+"."+count);
                    Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Storage." + i + ".Lore", item.getItemMeta().getLore());
                    ArrayList<String> buffs = new ArrayList<>();
                    if(DestinyEvents.getCubeStats(item)!=null) {
                        for (int a = 0; a < DestinyEvents.getCubeStats(item).size(); a++) {
                            buffs.add(DestinyEvents.getCubeStats(item).get(a).getString("buff"));
                        }
                        Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Storage." + i + ".CubeStats", buffs);
                    }
                    Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Storage.Active", true);
                    Main.data.saveConfig();
                }catch(NullPointerException ignored){
                    Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() +".Storage."+ i+".ID", "AIR");
                    Main.data.saveConfig();
                }
                i++;
            }
            }
        }
    }