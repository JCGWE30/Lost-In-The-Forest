package com.litf.death.Invens.AuctionHouse;

import com.litf.death.Events.UtilEvents;
import com.litf.death.Main;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Date;
import java.util.List;

public class AuctionHouseItems implements InventoryHolder {
    private static Inventory inv;

    public AuctionHouseItems(Player p) {
        inv = Bukkit.createInventory(this,54, "Your expired auctions");
        init(p);
    }
    private static void init(Player p){
        for(int a = 0; a < 54; a++) {
            if(Main.data.getConfig().getIntegerList(p.getUniqueId().toString() + ".Auctions.nums").size()-1>=a) {
                if (Main.data.getConfig().getIntegerList(p.getUniqueId().toString() + ".Auctions.nums") != null && Main.data.getConfig().getIntegerList(p.getUniqueId().toString() + ".Auctions.nums").get(a) != null) {
                    String i = Main.data.getConfig().getIntegerList(p.getUniqueId().toString() + ".Auctions.nums").get(a).toString();
                    ItemStack item = UtilEvents.getItemWithID(Main.data.getConfig().getString(p.getUniqueId().toString() + ".Auctions." + i + ".ID"));
                    if (item != null) {
                        item.setAmount(Main.data.getConfig().getInt(p.getUniqueId().toString()+".Auctions." + i + ".Amount"));
                        if (!Main.data.getConfig().getString(p.getUniqueId().toString() + ".Auctions." + i + ".Destiny").equals("None")) {
                            String[] destsplits = Main.data.getConfig().getString(p.getUniqueId().toString() + ".Auctions." + i + ".Destiny").split("\\.");
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            NBTTagCompound dest = new NBTTagCompound();
                            NBTTagCompound destc = new NBTTagCompound();
                            if (destsplits[0].equals("SKITING")) {
                                dest.setString("Destiny", "SKITING");
                            } else {
                                dest.setString("Destiny", "TAILION");
                            }
                            destc.setInt("DestinyCount", Integer.parseInt(destsplits[1]));
                            lis.add(dest);
                            lis.add(destc);
                            comp.set("comp", lis);
                            iten.setTag(comp);
                            item = CraftItemStack.asBukkitCopy(iten);
                        }
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(Main.data.getConfig().getString(p.getUniqueId().toString() + ".Auctions." + i + ".Name"));
                        List<String> lore = Main.data.getConfig().getStringList(p.getUniqueId().toString() + ".Auctions." + i + ".Lore");
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        inv.setItem(a, item);
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
