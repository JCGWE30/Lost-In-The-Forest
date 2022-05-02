package com.litf.death.Items;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deliveries {
    public static List<ItemStack> items = new ArrayList<>();
    public static void init(){
        createsturdybricks();
        createsearlyprototype();
        createcrudesketch();
        createbugnet();
        createpoorrefund();
        createstringnull();
        createpassport();
        createkey();
        createslimeball();
        createrichmans();
    }
    public static void createsturdybricks(){
        ItemStack item = new ItemStack(Material.BRICK, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "DELIVERY_BRICK");
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
        meta.setDisplayName(ChatColor.AQUA+"Sturdy bricks");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Delivery",
                ChatColor.GRAY+"Some sturdy bricks made by a very talented",
                ChatColor.GRAY+"builder (some say they are a god)"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createsearlyprototype(){
        ItemStack item = new ItemStack(Material.PISTON_BASE, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "DELIVERY_PROTOTYPE");
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
        meta.setDisplayName(ChatColor.AQUA+"Early Prototype");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Delivery",
                ChatColor.GRAY+"A early prototype machine, seems to be a very",
                ChatColor.GRAY+"downgraded version of the final product"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createcrudesketch(){
        ItemStack item = new ItemStack(Material.PAPER, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "DELIVERY_SKETCH");
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
        meta.setDisplayName(ChatColor.AQUA+"Crude Sketch");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Delivery",
                ChatColor.GRAY+"A sketch of the final product before production began",
                ChatColor.GRAY+"the lines are very bold however"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createbugnet(){
        ItemStack item = new ItemStack(Material.WEB, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "DELIVERY_NET");
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
        meta.setDisplayName(ChatColor.AQUA+"Bug Net");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Delivery",
                ChatColor.GRAY+"A very intricate net used to cath the worst of bugs",
                ChatColor.GRAY+"seems to need some cleaning"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createpoorrefund(){
        ItemStack item = new ItemStack(Material.GOLD_INGOT, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "DELIVERY_REFUND");
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
        meta.setDisplayName(ChatColor.AQUA+"Poor refund");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Delivery",
                ChatColor.GRAY+"\"We apologise for the inconvenience,",
                ChatColor.GRAY+"so as a refund we give you Store credit!\"",
                ChatColor.GRAY+"- Some support number"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createstringnull(){
        ItemStack item = new ItemStack(Material.STRING, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "DELIVERY_NULL");
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
        meta.setDisplayName(ChatColor.AQUA+"String = null;");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Delivery",
                ChatColor.GRAY+"Why on earth would you type that!"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createpassport(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "DELIVERY_PASPORT");
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
        meta.setDisplayName(ChatColor.AQUA+"Moderation Passport");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Delivery",
                ChatColor.GRAY+"Ensuring the new wave of players remain C A L M"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createkey(){
        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "DELIVERY_PASPORT");
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
        meta.setDisplayName(ChatColor.AQUA+"All access key");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Delivery",
                ChatColor.GRAY+"Ensuring the new wave of players are noice",
                ChatColor.GRAY+"as the amazing admin you are"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createslimeball(){
        ItemStack item = new ItemStack(Material.SLIME_BALL, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "DELIVERY_CREATIVE");
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
        meta.setDisplayName(ChatColor.AQUA+"Creators Slimebal");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Delivery",
                ChatColor.GRAY+"A slimebal than was derived from a good idea",
                ChatColor.GRAY+"must have been very good for this to exist"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createrichmans(){
        ItemStack item = new ItemStack(Material.GOLD_BLOCK, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "DELIVERY_RICH");
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
        meta.setDisplayName(ChatColor.AQUA+"Rich mans money thing");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Delivery",
                ChatColor.GRAY+"I am verry verrrrry rich..."
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
}
