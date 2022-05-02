package com.litf.death.Items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Misc {
    //Vars
    public static List<ItemStack> items = new ArrayList<>();
    //Intro Calls
    public static void init(){
        createspawnskiter();
        createskiterscale();
        createrefinedscale();
        createsealedscale();
        createskitermatter();
        createperfectscale();
        createspawnguard();
        createguardtail();
        createskitersack();
        createwindedstick();
        createrngmanipulator();
        createspooloftails();
        createforestessense();
        createessensehandle();
        createforestspirit();
        createiron();
        createlapis();
        creatediamond();
    }
    public static void createspawnskiter(){
        ItemStack item = getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q1NDE1NDFkYWFmZjUwODk2Y2QyNThiZGJkZDRjZjgwYzNiYTgxNjczNTcyNjA3OGJmZTM5MzkyN2U1N2YxIn19fQ==");
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_SPAWN");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q1NDE1NDFkYWFmZjUwODk2Y2QyNThiZGJkZDRjZjgwYzNiYTgxNjczNTcyNjA3OGJmZTM5MzkyN2U1N2YxIn19fQ");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Skiter Spawn Capsule");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Spawn Capsule",
                ChatColor.GRAY+"Right click the spawn capsule on the ground",
                ChatColor.GRAY+"To spawn the creature inside"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createskiterscale(){
        ItemStack item = new ItemStack(Material.INK_SACK, 1, (byte) 3);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_BASE");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY+"Skiter Scale");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"A small amount of scales dropped by a skiter",
                ChatColor.GRAY+"when combined with other scales, it creates a",
                ChatColor.GRAY+"single rigid scale with uses in crafting"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createrefinedscale(){
        ItemStack item = new ItemStack(Material.INK_SACK, 1, (byte) 14);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_REFINED");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+"Refined Scale");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"A single refined scale, very rigid, and can be",
                ChatColor.GRAY+"sharpened into a razor sharp blade or reinforced into a",
                ChatColor.GRAY+"more powerful scale"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createsealedscale(){
        ItemStack item = new ItemStack(Material.INK_SACK, 1, (byte) 2);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_SEALED");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE+"Sealed Scale");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"A scale sealed for perfect protection, and ",
                ChatColor.GRAY+"destruction, can be used in armor plating or in",
                ChatColor.GRAY+"the construction of a very powerful melee weapon",
                ChatColor.GRAY+"more powerful than imaginable"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createskitermatter(){
        ItemStack item = new ItemStack(Material.INK_SACK, 1, (byte) 0);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_MATTER");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Skiter Matter");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"Matter from a skiter, conducts a strange energy",
                ChatColor.GRAY+"which can be used to enhance gear, and make armor."
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createperfectscale(){
        ItemStack item = new ItemStack(Material.INK_SACK, 1, (byte) 5);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_PERFECT");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Perfect Scale");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"A beatiful scale, forged from its sealed counterpart,",
                ChatColor.GRAY+"its powerful aura can increase the luck of anyone who",
                ChatColor.GRAY+"uses it correctly."
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createspawnguard(){
        ItemStack item = getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY1N2NkNWMyOTg5ZmY5NzU3MGZlYzRkZGNkYzY5MjZhNjhhMzM5MzI1MGMxYmUxZjBiMTE0YTFkYjEifX19===");
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_SPAWN");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY1N2NkNWMyOTg5ZmY5NzU3MGZlYzRkZGNkYzY5MjZhNjhhMzM5MzI1MGMxYmUxZjBiMTE0YTFkYjEifX19=");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Guard Spawn Capsule");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Spawn Capsule",
                ChatColor.GRAY+"Right click the spawn capsule on the ground",
                ChatColor.GRAY+"To spawn the creature inside"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createguardtail(){
        ItemStack item = new ItemStack (Material.STRING, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_TAIL");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+"Guard tail");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"A tail of a feline creature that protects",
                ChatColor.GRAY+"the forest from intruders and threats",
                ChatColor.GRAY+"despite its size it is very powerful and is key",
                ChatColor.GRAY+"in the upgrading of items"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createskitersack(){
        ItemStack item = new ItemStack (Material.CLAY_BALL, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_SACK");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Sack of skiter matter");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"A sack of skiter matter compressed into a ball",
                ChatColor.GRAY+"used in the creation of advanced items"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createspooloftails(){
        ItemStack item = getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODdlYzY1ZDY2NDlhYzFiZjdiMjgyNTc1Y2VmMjk5Zjg2MDFlNTFkODQxOGQ2ZTU0NmU0ZmM3MWIyMThmNyJ9fX0=");
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_SPOOL");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODdlYzY1ZDY2NDlhYzFiZjdiMjgyNTc1Y2VmMjk5Zjg2MDFlNTFkODQxOGQ2ZTU0NmU0ZmM3MWIyMThmNyJ9fX0=");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE+"Spool of \"Yarn\"");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"Yuck"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createwindedstick(){
        ItemStack item = new ItemStack (Material.BONE, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_STAFF");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE+"Tightly Winded Staff");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"A staff made of guard tails, gives it",
                ChatColor.GRAY+"an enormous amount of strength when used",
                ChatColor.GRAY+"with other powerful items."
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createrngmanipulator(){
        ItemStack item = getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThhNDZjNGM1NWFlNzFmMzk3ZjZlMDk2ZWFmNzFkZDdhNmQzYmZlYTQzMjI5OTgxNDkzZDgxYzc2NGJiOTIyMCJ9fX0=");
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_RNG");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThhNDZjNGM1NWFlNzFmMzk3ZjZlMDk2ZWFmNzFkZDdhNmQzYmZlYTQzMjI5OTgxNDkzZDgxYzc2NGJiOTIyMCJ9fX0=");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE+"RNG Manipulator");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"WOW you must have some VERY GOOD RNG",
                ChatColor.GRAY+"to get THIS CUBE!!!",
                " ",
                ChatColor.YELLOW+"Right-click to view recipe"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createforestessense(){
        ItemStack item = new ItemStack(Material.SAPLING, 1, (byte) 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "TYRA_BASE");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Forest Essense");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"Essense of the forest"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createessensehandle(){
        ItemStack item = new ItemStack(Material.STICK, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "TYRA_HANDLE");
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
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Essense Handle");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"Very compressed handle",
                " ",
                ChatColor.YELLOW+"Right-click to view recipe"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createforestspirit(){
        ItemStack item = new ItemStack(Material.ENDER_PEARL, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "TYRA_SPIRIT");
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
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Spirit of the forest");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"Spirit of the forest, can be used",
                ChatColor.GRAY+"to forge very powerful items"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    //THE UNDERGROUND
    public static void createiron(){
        ItemStack item = new ItemStack(Material.IRON_INGOT, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "CAVE_IRON");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY+"Iron");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"IRONt you glad you mined this..."
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createlapis(){
        ItemStack item = new ItemStack(Material.INK_SACK, 1, (byte) 4);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "CAVE_LAPIS");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE+"Lapis");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"A very VERY blue gem",
                ChatColor.GRAY+"can be used in upgrading tools"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void creatediamond(){
        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "CAVE_DIAMO");
        ust.setBoolean("ust", false);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "0");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Diamond");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"Its soo beatiful... I guess"
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
}
