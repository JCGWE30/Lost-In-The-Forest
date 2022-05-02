package com.litf.death.Events;

import com.litf.death.Main;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryStoringEvents implements Listener {
    @EventHandler
    public static void connect(PlayerJoinEvent e){
        if(Main.data.getConfig().getBoolean(e.getPlayer().getUniqueId().toString() + ".Inventory.Active")) {
            e.getPlayer().getInventory().clear();
            e.getPlayer().getInventory().setArmorContents(null);
            for(int i =0;i<36;i++) {
                if (!Main.data.getConfig().getString(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".ID").equals("AIR")) {
                    ItemStack item = UtilEvents.getItemWithID(Main.data.getConfig().getString(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".ID"));
                    if(!Main.data.getConfig().getString(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Destiny").equals("None.0")) {
                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                        NBTTagCompound comp = iten.getTag();
                        NBTTagList lis = (NBTTagList) comp.get("comp");
                        NBTTagCompound dest = new NBTTagCompound();
                        NBTTagCompound destc = new NBTTagCompound();
                        String[] dests = Main.data.getConfig().getString(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Destiny").split("\\.");
                        dest.setString("Destiny", dests[0]);
                        destc.setInt("DestinyCount", Integer.parseInt(dests[1]));
                        lis.add(dest);
                        lis.add(destc);
                        comp.set("comp", lis);
                        iten.setTag(comp);
                        item = CraftItemStack.asBukkitCopy(iten);
                    }
                    if(Main.data.getConfig().getString(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".ID").equals("GUARD_CUBE")){
                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                        NBTTagCompound comp = iten.getTag();
                        NBTTagList lis = (NBTTagList) comp.get("comp");
                        NBTTagCompound bufcomp = new NBTTagCompound();
                        NBTTagCompound buff = new NBTTagCompound();
                        NBTTagList buffs= new NBTTagList();
                            for(String buffstr:Main.data.getConfig().getStringList(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".CubeStats")){
                               buff.setString("buff", buffstr);
                               buffs.add(buff);
                            }
                            bufcomp.set("buffs", buffs);
                            lis.add(bufcomp);
                        comp.set("comp", lis);
                        iten.setTag(comp);
                        item = CraftItemStack.asBukkitCopy(iten);
                    }
                    item.setAmount(Main.data.getConfig().getInt(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Count"));
                    ItemMeta meta = item.getItemMeta();
                    meta.setLore(Main.data.getConfig().getStringList(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Lore"));
                    item.setItemMeta(meta);
                    e.getPlayer().getInventory().setItem(i, item);
                }
            }
            for(int i =36;i<40;i++) {
                System.out.println(Main.data.getConfig().getString(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".ID")+ i);
                if (!Main.data.getConfig().getString(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".ID").equals("AIR")) {
                    ItemStack item = UtilEvents.getItemWithID(Main.data.getConfig().getString(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".ID"));
                    if(!Main.data.getConfig().getString(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Destiny").equals("None.0")) {
                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                        NBTTagCompound comp = iten.getTag();
                        NBTTagList lis = (NBTTagList) comp.get("comp");
                        NBTTagCompound dest = new NBTTagCompound();
                        NBTTagCompound destc = new NBTTagCompound();
                        String[] dests = Main.data.getConfig().getString(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Destiny").split("\\.");
                        dest.setString("Destiny", dests[0]);
                        destc.setInt("DestinyCount", Integer.parseInt(dests[1]));
                        lis.add(dest);
                        lis.add(destc);
                        comp.set("comp", lis);
                        iten.setTag(comp);
                        item = CraftItemStack.asBukkitCopy(iten);
                    }
                    if(Main.data.getConfig().getStringList(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".CubeStats")!=null){
                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                        NBTTagCompound comp = iten.getTag();
                        NBTTagList lis = (NBTTagList) comp.get("comp");
                        NBTTagCompound bufcomp = new NBTTagCompound();
                        NBTTagCompound buff = new NBTTagCompound();
                        NBTTagList buffs= new NBTTagList();
                        for(String buffstr:Main.data.getConfig().getStringList(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".CubeStats")){
                            buff.setString("buff", buffstr);
                            buffs.add(buff);
                        }
                        bufcomp.set("buffs", buffs);
                        lis.add(bufcomp);
                        comp.set("comp", lis);
                        iten.setTag(comp);
                        item = CraftItemStack.asBukkitCopy(iten);
                    }
                    item.setAmount(Main.data.getConfig().getInt(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Count"));
                    ItemMeta meta = item.getItemMeta();
                    meta.setLore(Main.data.getConfig().getStringList(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Lore"));
                    item.setItemMeta(meta);
                    if (i == 39) {
                        e.getPlayer().getInventory().setHelmet(item);
                    } else if (i == 38) {
                        e.getPlayer().getInventory().setChestplate(item);
                    } else if (i == 37) {
                        e.getPlayer().getInventory().setLeggings(item);
                    } else if (i == 36) {
                        e.getPlayer().getInventory().setBoots(item);
                    }
                }
            }
        }
        }

    @EventHandler
    public static void disconnect(PlayerQuitEvent e){
        int i = 0;
        for(ItemStack item:e.getPlayer().getInventory().getContents()){
            Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory." + i, null);
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
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".ID", UtilEvents.getId(item));
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Count", item.getAmount());
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Destiny", DestinyEvents.getDestiny(item).split("\\.")[0]+"."+count);
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Lore", item.getItemMeta().getLore());
                ArrayList<String> buffs = new ArrayList<>();
                if(DestinyEvents.getCubeStats(item)!=null) {
                    for (int a = 0; a < DestinyEvents.getCubeStats(item).size(); a++) {
                        buffs.add(DestinyEvents.getCubeStats(item).get(a).getString("buff"));
                    }
                    Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".CubeStats", buffs);
                }
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory.Active", true);
                Main.data.saveConfig();
            }catch(NullPointerException ignored){
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() +".Inventory."+ i+".ID", "AIR");
                Main.data.saveConfig();
            }
            i++;
        }
        for(ItemStack item:e.getPlayer().getInventory().getArmorContents()){
            Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory. " + i, null);
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
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".ID", UtilEvents.getId(item));
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Count", item.getAmount());
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Destiny", DestinyEvents.getDestiny(item).split("\\.")[0]+"."+count);
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".Lore", item.getItemMeta().getLore());
                ArrayList<String> buffs = new ArrayList<>();
                if(DestinyEvents.getCubeStats(item)!=null) {
                    for (int a = 0; a < DestinyEvents.getCubeStats(item).size(); a++) {
                        buffs.add(DestinyEvents.getCubeStats(item).get(a).getString("buff"));
                    }
                    Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory." + i + ".CubeStats", buffs);
                }
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Inventory.Active", true);
                Main.data.saveConfig();
            }catch(NullPointerException ignored){
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() +".Inventory."+ i+".ID", "AIR");
                Main.data.saveConfig();
            }
            i++;
        }
    }
}
