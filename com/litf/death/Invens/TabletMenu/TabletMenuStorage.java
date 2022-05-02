package com.litf.death.Invens.TabletMenu;

import com.litf.death.Events.UtilEvents;
import com.litf.death.Main;
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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class TabletMenuStorage implements InventoryHolder {
    private static Inventory inv;
    public TabletMenuStorage(Player p){
        inv = Bukkit.createInventory(this,54, "Storage Menu");
        init(p);
    }
    private static void init(Player p) {
        if (Main.data.getConfig().getBoolean(p.getUniqueId().toString() + ".Storage.Active")) {
            for (int i = 0; i < 54; i++) {
                if (!Main.data.getConfig().getString(p.getUniqueId().toString() + ".Storage." + i + ".ID").equals("AIR")) {
                    ItemStack item = UtilEvents.getItemWithID(Main.data.getConfig().getString(p.getUniqueId().toString() + ".Storage." + i + ".ID"));
                    if (!Main.data.getConfig().getString(p.getUniqueId().toString() + ".Storage." + i + ".Destiny").equals("None.0")) {
                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                        NBTTagCompound comp = iten.getTag();
                        NBTTagList lis = (NBTTagList) comp.get("comp");
                        NBTTagCompound dest = new NBTTagCompound();
                        NBTTagCompound destc = new NBTTagCompound();
                        String[] dests = Main.data.getConfig().getString(p.getUniqueId().toString() + ".Storage." + i + ".Destiny").split("\\.");
                        dest.setString("Destiny", dests[0]);
                        destc.setInt("DestinyCount", Integer.parseInt(dests[1]));
                        lis.add(dest);
                        lis.add(destc);
                        comp.set("comp", lis);
                        iten.setTag(comp);
                        item = CraftItemStack.asBukkitCopy(iten);
                    }
                    if (Main.data.getConfig().getString(p.getUniqueId().toString() + ".Storage." + i + ".ID").equals("GUARD_CUBE")) {
                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                        NBTTagCompound comp = iten.getTag();
                        NBTTagList lis = (NBTTagList) comp.get("comp");
                        NBTTagCompound bufcomp = new NBTTagCompound();
                        NBTTagCompound buff = new NBTTagCompound();
                        NBTTagList buffs = new NBTTagList();
                        for (String buffstr : Main.data.getConfig().getStringList(p.getUniqueId().toString() + ".Storage." + i + ".CubeStats")) {
                            buff.setString("buff", buffstr);
                            buffs.add(buff);
                        }
                        bufcomp.set("buffs", buffs);
                        lis.add(bufcomp);
                        comp.set("comp", lis);
                        iten.setTag(comp);
                        item = CraftItemStack.asBukkitCopy(iten);
                    }
                    item.setAmount(Main.data.getConfig().getInt(p.getUniqueId().toString() + ".Storage." + i + ".Count"));
                    ItemMeta meta = item.getItemMeta();
                    meta.setLore(Main.data.getConfig().getStringList(p.getUniqueId().toString() + ".Storage." + i + ".Lore"));
                    item.setItemMeta(meta);
                    inv.setItem(i, item);
                }
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
