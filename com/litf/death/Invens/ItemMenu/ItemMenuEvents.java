package com.litf.death.Invens.ItemMenu;

import com.litf.death.Items.Armor;
import com.litf.death.Items.Items;
import com.litf.death.Items.Misc;
import com.litf.death.Items.Weapons;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class ItemMenuEvents implements Listener {
    @EventHandler
    //Main Menu
    public static void click(InventoryClickEvent e){
        if(e.getClickedInventory()!=null&&e.getClickedInventory().getHolder() instanceof ItemMenuMain){
            e.setCancelled(true);
            //Open Sub menus
            if(e.getSlot()==10){
                ItemMenuWeapons gui = new ItemMenuWeapons();
                e.getWhoClicked().openInventory(gui.getInventory());
            }else if(e.getSlot()==12){
                ItemMenuItems gui = new ItemMenuItems();
                e.getWhoClicked().openInventory(gui.getInventory());
            }else if(e.getSlot()==14){
                ItemMenuArmor gui = new ItemMenuArmor();
                e.getWhoClicked().openInventory(gui.getInventory());
            }else if(e.getSlot()==16){
                ItemMenuMisc gui = new ItemMenuMisc();
                e.getWhoClicked().openInventory(gui.getInventory());
            }
        }
        //Go back
        if(e.getClickedInventory()!=null&&e.getClickedInventory().getHolder() instanceof ItemMenuWeapons){
            if(e.getClickedInventory().getSize()-5==e.getSlot()){
                ItemMenuMain gui = new ItemMenuMain();
                e.getWhoClicked().openInventory(gui.getInventory());
            }
        }
        if(e.getClickedInventory()!=null&&e.getClickedInventory().getHolder() instanceof ItemMenuArmor){
            if(e.getClickedInventory().getSize()-5==e.getSlot()){
                ItemMenuMain gui = new ItemMenuMain();
                e.getWhoClicked().openInventory(gui.getInventory());
            }
        }
        if(e.getClickedInventory()!=null&&e.getClickedInventory().getHolder() instanceof ItemMenuItems){
            if(e.getClickedInventory().getSize()-5==e.getSlot()){
                ItemMenuMain gui = new ItemMenuMain();
                e.getWhoClicked().openInventory(gui.getInventory());
            }
        }
        if(e.getClickedInventory()!=null&&e.getClickedInventory().getHolder() instanceof ItemMenuMisc){
            if(e.getClickedInventory().getSize()-5==e.getSlot()){
                ItemMenuMain gui = new ItemMenuMain();
                e.getWhoClicked().openInventory(gui.getInventory());
            }
        }
            //Menus
            if (e.getClickedInventory()!=null&&e.getClickedInventory().getHolder() instanceof ItemMenuWeapons) {
                try{
                e.setCancelled(true);
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(e.getCurrentItem().clone());
                NBTTagCompound comp = iten.getTag();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                Boolean ust = lis.get(1).getBoolean("ust");
                String id = lis.get(0).getString("id");
                for(ItemStack itema:Weapons.items){
                    net.minecraft.server.v1_8_R3.ItemStack itef = CraftItemStack.asNMSCopy(itema);
                    NBTTagCompound coma = (NBTTagCompound) itef.getTag().clone();
                    NBTTagList lia = (NBTTagList) coma.get("comp");
                    String ida = lia.get(0).getString("id");
                    String value = lia.get(2).getString("SKULL_VALUE");
                    ItemStack item = null;
                    if (value.length()<20) {
                        item = new ItemStack(itema.getType(), 1, itema.getDurability());
                        ItemMeta meta = item.getItemMeta();
                        if(itema.getItemMeta().getLore()!=null) {
                            meta.setLore(itema.getItemMeta().getLore());
                        }
                        meta.setDisplayName(itema.getItemMeta().getDisplayName());
                        item.setItemMeta(meta);
                    }else{
                        item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                        ItemMeta meta = item.getItemMeta();
                        if(itema.getItemMeta().getLore()!=null) {
                            meta.setLore(itema.getItemMeta().getLore());
                        }
                        meta.setDisplayName(itema.getItemMeta().getDisplayName());
                        item.setItemMeta(meta);
                    }
                    net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(item);
                    ited.setTag(coma);
                    item = CraftItemStack.asBukkitCopy(ited);
                    if(id.equals(ida)) {
                        if(ust) {
                            NBTTagCompound uuid = new NBTTagCompound();
                            uuid.setString("uuid", UUID.randomUUID().toString());
                            lia.add(uuid);
                            coma.set("comp", lia);
                            ited.setTag(coma);
                            itema = CraftItemStack.asBukkitCopy(ited);
                        }
                        if(e.getClick().equals(ClickType.LEFT)||ust){
                            e.getWhoClicked().getInventory().addItem(item);
                        }else if(e.getClick().equals(ClickType.RIGHT)){
                            item.setAmount(64);
                            e.getWhoClicked().getInventory().addItem(item);
                        }
                    }
                }

                }
                catch(Exception ex){

                }
            }else if(e.getClickedInventory()!=null&&e.getClickedInventory().getHolder() instanceof ItemMenuMisc){
                try{
                    e.setCancelled(true);
                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(e.getCurrentItem().clone());
                    NBTTagCompound comp = iten.getTag();
                    NBTTagList lis = (NBTTagList) comp.get("comp");
                    Boolean ust = lis.get(1).getBoolean("ust");
                    String id = lis.get(0).getString("id");
                    for(ItemStack itema:Misc.items){
                        net.minecraft.server.v1_8_R3.ItemStack itef = CraftItemStack.asNMSCopy(itema);
                        NBTTagCompound coma = (NBTTagCompound) itef.getTag().clone();
                        NBTTagList lia = (NBTTagList) coma.get("comp");
                        String ida = lia.get(0).getString("id");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        ItemStack item = null;
                        if (value == "0") {
                            item = new ItemStack(itema.getType(), 1, itema.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if(itema.getItemMeta().getLore()!=null) {
                                meta.setLore(itema.getItemMeta().getLore());
                            }
                            meta.setDisplayName(itema.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }else{
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if(itema.getItemMeta().getLore()!=null) {
                                meta.setLore(itema.getItemMeta().getLore());
                            }
                            meta.setDisplayName(itema.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(item);
                        ited.setTag(coma);
                        item = CraftItemStack.asBukkitCopy(ited);
                        if(id.equals(ida)) {
                            if(ust) {
                                NBTTagCompound uuid = new NBTTagCompound();
                                uuid.setString("uuid", UUID.randomUUID().toString());
                                lia.add(uuid);
                                coma.set("comp", lia);
                                ited.setTag(coma);
                                itema = CraftItemStack.asBukkitCopy(ited);
                            }
                            if(e.getClick().equals(ClickType.LEFT)||ust){
                                e.getWhoClicked().getInventory().addItem(item);
                            }else if(e.getClick().equals(ClickType.RIGHT)){
                                item.setAmount(64);
                                e.getWhoClicked().getInventory().addItem(item);
                            }
                        }
                    }

                }
                catch(Exception ex){

                }
            }else if (e.getClickedInventory()!=null&&e.getClickedInventory().getHolder() instanceof ItemMenuArmor) {
                try{
                    e.setCancelled(true);
                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(e.getCurrentItem().clone());
                    NBTTagCompound comp = iten.getTag();
                    NBTTagList lis = (NBTTagList) comp.get("comp");
                    Boolean ust = lis.get(1).getBoolean("ust");
                    String id = lis.get(0).getString("id");
                    for(ItemStack itema: Armor.items){
                        net.minecraft.server.v1_8_R3.ItemStack itef = CraftItemStack.asNMSCopy(itema);
                        NBTTagCompound coma = (NBTTagCompound) itef.getTag().clone();
                        NBTTagList lia = (NBTTagList) coma.get("comp");
                        String ida = lia.get(0).getString("id");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        ItemStack item = null;
                        if (value == "0") {
                            item = new ItemStack(itema.getType(), 1);
                            ItemMeta meta = item.getItemMeta();
                            if(itema.getItemMeta().getLore()!=null) {
                                meta.setLore(itema.getItemMeta().getLore());
                            }
                            meta.setDisplayName(itema.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }else{
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if(itema.getItemMeta().getLore()!=null) {
                                meta.setLore(itema.getItemMeta().getLore());
                            }
                            meta.setDisplayName(itema.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(item);
                        ited.setTag(coma);
                        item = CraftItemStack.asBukkitCopy(ited);
                        if(id.equals(ida)) {
                            if(ust) {
                                NBTTagCompound uuid = new NBTTagCompound();
                                uuid.setString("uuid", UUID.randomUUID().toString());
                                lia.add(uuid);
                                coma.set("comp", lia);
                                ited.setTag(coma);
                                itema = CraftItemStack.asBukkitCopy(ited);
                            }
                            if(e.getClick().equals(ClickType.LEFT)||ust){
                                e.getWhoClicked().getInventory().addItem(item);
                            }else if(e.getClick().equals(ClickType.RIGHT)){
                                item.setAmount(64);
                                e.getWhoClicked().getInventory().addItem(item);
                            }
                        }
                    }

                }
                catch(Exception ex){

                }
            }else if (e.getClickedInventory()!=null&&e.getClickedInventory().getHolder() instanceof ItemMenuItems) {
                try{
                    e.setCancelled(true);
                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(e.getCurrentItem().clone());
                    NBTTagCompound comp = iten.getTag();
                    NBTTagList lis = (NBTTagList) comp.get("comp");
                    Boolean ust = lis.get(1).getBoolean("ust");
                    String id = lis.get(0).getString("id");
                    for(ItemStack itema: Items.items){
                        net.minecraft.server.v1_8_R3.ItemStack itef = CraftItemStack.asNMSCopy(itema);
                        NBTTagCompound coma = (NBTTagCompound) itef.getTag().clone();
                        NBTTagList lia = (NBTTagList) coma.get("comp");
                        String ida = lia.get(0).getString("id");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        ItemStack item = null;
                        if (value.length()<20) {
                            item = new ItemStack(itema.getType(), 1, itema.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if(itema.getItemMeta().getLore()!=null) {
                                meta.setLore(itema.getItemMeta().getLore());
                            }
                            meta.setDisplayName(itema.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }else{
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if(itema.getItemMeta().getLore()!=null) {
                                meta.setLore(itema.getItemMeta().getLore());
                            }
                            meta.setDisplayName(itema.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(item);
                        ited.setTag(coma);
                        item = CraftItemStack.asBukkitCopy(ited);
                        if(id.equals(ida)) {
                            if(ust) {
                                NBTTagCompound uuid = new NBTTagCompound();
                                uuid.setString("uuid", UUID.randomUUID().toString());
                                lia.add(uuid);
                                coma.set("comp", lia);
                                ited.setTag(coma);
                                itema = CraftItemStack.asBukkitCopy(ited);
                            }
                            if(e.getClick().equals(ClickType.LEFT)||ust){
                                e.getWhoClicked().getInventory().addItem(item);
                            }else if(e.getClick().equals(ClickType.RIGHT)){
                                item.setAmount(64);
                                e.getWhoClicked().getInventory().addItem(item);
                            }
                        }
                    }

                }
                catch(Exception ex){

                }
            }

    }
    public static SkullMeta getheadmeta(String url){
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        if (url.isEmpty()) return null;

        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", url));

        try {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException|NoSuchFieldException|SecurityException|IllegalAccessException ex) {
            ex.printStackTrace();
        }

        head.setItemMeta(skullMeta);
        return skullMeta;
    }
}
