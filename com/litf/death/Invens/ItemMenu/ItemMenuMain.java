package com.litf.death.Invens.ItemMenu;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class ItemMenuMain implements InventoryHolder {
    private static Inventory inv;
    public ItemMenuMain(){
        inv = Bukkit.createInventory(this, 27, "Item Menu");
        init();
    }
    private static void init(){
        for(int i = 0; i < 27; i++){
            switch(i){
                case 10:
                    inv.setItem(i, createItem(ChatColor.GRAY+"Weapons", Material.WOOD_SWORD, null, (byte) 0));
                    break;
                case 12:
                    ItemStack item = getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjFmOTA5Y2MxYzMyZGEzYjExNjAzOTQ0ZDRmNzMxZDYzYmFhOWFkZTFlMTIxMWRkZjlmMDE1NTA5MWM1Yzk0YiJ9fX0=");
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ChatColor.GRAY+"Items & Tools");
                    item.setItemMeta(meta);
                    inv.setItem(i, item);
                    break;
                case 14:
                    inv.setItem(i, createItem(ChatColor.GRAY+"Armor", Material.DIAMOND_CHESTPLATE, null, (byte) 0));
                    break;
                case 16:
                    inv.setItem(i, createItem(ChatColor.GRAY+"Misc", Material.RAW_FISH, null, (byte) 3));
                    break;
                default:
                    inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
            }
        }
    }
    public static ItemStack createItem(String name, Material mat, List<String> lore, Byte b){
        ItemStack item = new ItemStack(mat, 1, b);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack getcustomhead(String url){
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        if (url.isEmpty()) return head;

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
        return head;
    }
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
