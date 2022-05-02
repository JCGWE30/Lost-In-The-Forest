package com.litf.death.Items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Armor {
    public static List<ItemStack> items = new ArrayList<>();
    public static void init(){
        createsealedhelmet();
        createsealedchestplate();
        createsealedleggings();
        createsealedboots();
        createreinforcedhelmet();
        createreinforcedchestplate();
        createreinforcedlegs();
        createreinforcedboots();
        createforesthelmet();
        createforestchestplate();
        createforestleggings();
        createforestboots();
    }
    //SKITER STICK
    public static void createsealedhelmet(){
        ItemStack item = getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQ1OWFhNzhjYjdlOWI2Y2E2ZmVlNDEyMTMyOTA1OWRkNjhhZmRkYzBjOGI1M2E5MDZiNzk1Mzk5NGU4YTc2In19fQ==");
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_HELMET");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQ1OWFhNzhjYjdlOWI2Y2E2ZmVlNDEyMTMyOTA1OWRkNjhhZmRkYzBjOGI1M2E5MDZiNzk1Mzk5NGU4YTc2In19fQ");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Sealed Helmet");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Scale Set Hemlet",
                ChatColor.GRAY+"A helmet made from skiter matter and sealed scales",
                ChatColor.GRAY+"protects the wearer, and increases their damage output",
                ChatColor.YELLOW+"Take 5% less damage from forest creatures",
                ChatColor.translateAlternateColorCodes('&', "&6&lSPECIAL STAT ")+ChatColor.BLUE+"When you are wearing all armor pieces",
                ChatColor.BLUE+"for this set, deal 2x damage to skiters, and take",
                ChatColor.BLUE+"an extra 10% less damage from forest creatures"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createsealedchestplate(){
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_CHEST");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Sealed Chestplate");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Scale Set Chestplate",
                ChatColor.GRAY+"A chestplate made from skiter matter and sealed scales",
                ChatColor.GRAY+"protects the wearer, and increases their damage output",
                ChatColor.YELLOW+"Take 5% less damage from forest creatures",
                ChatColor.translateAlternateColorCodes('&', "&6&lSPECIAL STAT ")+ChatColor.BLUE+"When you are wearing all armor pieces",
                ChatColor.BLUE+"for this set, deal 2x damage to skiters, and take",
                ChatColor.BLUE+"an extra 10% less damage from forest creatures"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createsealedleggings(){
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_LEGS");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Sealed Leggings");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Scale Set Leggins",
                ChatColor.GRAY+"A chestplate made from skiter matter and sealed scales",
                ChatColor.GRAY+"protects the wearer, and increases their damage output",
                ChatColor.YELLOW+"Take 5% less damage from forest creatures",
                ChatColor.translateAlternateColorCodes('&', "&6&lSPECIAL STAT ")+ChatColor.BLUE+"When you are wearing all armor pieces",
                ChatColor.BLUE+"for this set, deal 2x damage to skiters, and take",
                ChatColor.BLUE+"an extra 10% less damage from forest creatures"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createsealedboots(){
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_BOOTS");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Sealed Boots");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Scale Set Boots",
                ChatColor.GRAY+"A pair of boots made from skiter matter and sealed scales",
                ChatColor.GRAY+"protects the wearer, and increases their damage output",
                ChatColor.YELLOW+"Take 5% less damage from forest creatures",
                ChatColor.translateAlternateColorCodes('&', "&6&lSPECIAL STAT ")+ChatColor.BLUE+"When you are wearing all armor pieces",
                ChatColor.BLUE+"for this set, deal 2x damage to skiters, and take",
                ChatColor.BLUE+"an extra 10% less damage from forest creatures"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
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
    public static void createreinforcedhelmet(){
        ItemStack item = getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjNmMjkzZWJkMDkxMWJiODEzM2U3NTgwMjg5MDk5N2U4Mjg1NDkxNWRmNWQ4OGYxMTVkZTFkZWJhNjI4MTY0In19fQ==");
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_HELMET");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjNmMjkzZWJkMDkxMWJiODEzM2U3NTgwMjg5MDk5N2U4Mjg1NDkxNWRmNWQ4OGYxMTVkZTFkZWJhNjI4MTY0In19fQ==");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Reinforced Helmet");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Tail Set Hemlet",
                ChatColor.GRAY+"A helmet reinforced with guard tails, making it very strong",
                ChatColor.GRAY+"protecting the wearer and increasing the damage output",
                ChatColor.YELLOW+"Take 10% less damage from forest creatures, and",
                ChatColor.YELLOW+"Deal 1.5x more damage to forest creatures with",
                ChatColor.YELLOW+"less health then you",
                ChatColor.translateAlternateColorCodes('&', "&6&lSPECIAL STAT ")+ChatColor.BLUE+"When you are wearing all armor pieces",
                ChatColor.BLUE+"creatures do not need to have less health then you for the",
                ChatColor.BLUE+"damage buff to execute, and the damage buff is",
                ChatColor.BLUE+"increased to 1.7 per piece"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createreinforcedchestplate(){
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_CHESTPLATE");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(255,255,255));
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Reinforced Helmet");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Tail Set Chestplate",
                ChatColor.GRAY+"A chestplate reinforced with guard tails, making it very strong",
                ChatColor.GRAY+"protecting the wearer and increasing the damage output",
                ChatColor.YELLOW+"Take 10% less damage from forest creatures, and",
                ChatColor.YELLOW+"Deal 1.5x more damage to forest creatures with",
                ChatColor.YELLOW+"less health then you",
                ChatColor.translateAlternateColorCodes('&', "&6&lSPECIAL STAT ")+ChatColor.BLUE+"When you are wearing all armor pieces",
                ChatColor.BLUE+"creatures do not need to have less health then you for the",
                ChatColor.BLUE+"damage buff to execute, and the damage buff is",
                ChatColor.BLUE+"increased to 1.7 per piece"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createreinforcedlegs(){
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_LEGS");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(255,255,255));
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Reinforced Leggings");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Tail Set Leggings",
                ChatColor.GRAY+"Some leggings reinforced with guard tails, making it very strong",
                ChatColor.GRAY+"protecting the wearer and increasing the damage output",
                ChatColor.YELLOW+"Take 10% less damage from forest creatures, and",
                ChatColor.YELLOW+"Deal 1.5x more damage to forest creatures with",
                ChatColor.YELLOW+"less health then you",
                ChatColor.translateAlternateColorCodes('&', "&6&lSPECIAL STAT ")+ChatColor.BLUE+"When you are wearing all armor pieces",
                ChatColor.BLUE+"creatures do not need to have less health then you for the",
                ChatColor.BLUE+"damage buff to execute, and the damage buff is",
                ChatColor.BLUE+"increased to 1.7 per piece"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createreinforcedboots(){
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_BOOTS");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(255,255,255));
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Reinforced Boots");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Tail Set Boots",
                ChatColor.GRAY+"Some leggings reinforced with guard tails, making it very strong",
                ChatColor.GRAY+"protecting the wearer and increasing the damage output",
                ChatColor.YELLOW+"Take 10% less damage from forest creatures, and",
                ChatColor.YELLOW+"Deal 1.5x more damage to forest creatures with",
                ChatColor.YELLOW+"less health then you",
                ChatColor.translateAlternateColorCodes('&', "&6&lSPECIAL STAT ")+ChatColor.BLUE+"When you are wearing all armor pieces",
                ChatColor.BLUE+"creatures do not need to have less health then you for the",
                ChatColor.BLUE+"damage buff to execute, and the damage buff is",
                ChatColor.BLUE+"increased to 1.7 per piece"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createforesthelmet(){
        ItemStack item = getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmZjODY0MTgzNWRkZTYyNzNhZGYyNGY2MDE0NmEyMmU2NGVjNGNmYTFhMTMwOTA0ZDk0OTdjMjFjODQ3MGMwIn19fQ==");
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "TYRA_HELMET");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmZjODY0MTgzNWRkZTYyNzNhZGYyNGY2MDE0NmEyMmU2NGVjNGNmYTFhMTMwOTA0ZDk0OTdjMjFjODQ3MGMwIn19fQ==");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Forest Helmet");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Forest Set Helmet",
                ChatColor.GRAY+"A helmet made from forest essense",
                ChatColor.GRAY+"granting the weared emmense power while lost in the forest",
                ChatColor.YELLOW+"Take 30% less damage from forest creatures, and",
                ChatColor.YELLOW+"Deal 3x more damage to forest creatures"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createforestchestplate(){
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "TYRA_CHEST");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(49,102,22));
        meta.setDisplayName(ChatColor.GOLD+"Forest Chestplate");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Forest Set Chestplate",
                ChatColor.GRAY+"A chestplate made from forest essense",
                ChatColor.GRAY+"granting the wearer immense power while lost in the forest",
                ChatColor.YELLOW+"Take 30% less damage from forest creatures, and",
                ChatColor.YELLOW+"Deal 3x more damage to forest creatures"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createforestleggings(){
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "TYRA_LEGS");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(49,102,22));
        meta.setDisplayName(ChatColor.GOLD+"Forest Leggings");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Forest Set Leggings",
                ChatColor.GRAY+"Some leggings made from forest essense",
                ChatColor.GRAY+"granting the wearer immense power while lost in the forest",
                ChatColor.YELLOW+"Take 30% less damage from forest creatures, and",
                ChatColor.YELLOW+"Deal 3x more damage to forest creatures"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createforestboots(){
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "TYRA_BOOTS");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(49,102,22));
        meta.setDisplayName(ChatColor.GOLD+"Forest Boots");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Forest Set Boots",
                ChatColor.GRAY+"Some boots made from forest essense",
                ChatColor.GRAY+"granting the wearer immense power while lost in the forest",
                ChatColor.YELLOW+"Take 30% less damage from forest creatures, and",
                ChatColor.YELLOW+"Deal 3x more damage to forest creatures"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
}
