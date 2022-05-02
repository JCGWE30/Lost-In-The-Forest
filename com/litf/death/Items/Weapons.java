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

public class Weapons {
    //Vars
    public static List<ItemStack> items = new ArrayList<>();
    //Intro Calls
    public static void init(){
        createscalestick();
        createsscaledagger();
        createsscaleexecutor();
        createknightedsythe();
        createbigminer();
        createessensedagger();
    }
    //SKITER STICK
    public static void createscalestick(){
        ItemStack item = new ItemStack(Material.STICK, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_BLADE");
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
        meta.setDisplayName(ChatColor.GREEN+"Scale Stick");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafring Weapon",
                ChatColor.GRAY+"A Blade made from skiter scales, and pure will",
                ChatColor.GRAY+"can deliver a serious punch when used correctly.",
                ChatColor.YELLOW+"This weapon deals 1.5x more damage to forest mobs,",
                ChatColor.YELLOW+"and increases drop chances of forest mobs by 20%"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createsscaledagger(){
        ItemStack item = new ItemStack(Material.STONE_SWORD, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_DAGGER");
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
        meta.setDisplayName(ChatColor.BLUE+"Skiter Dagger");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafring Weapon",
                ChatColor.GRAY+"A Dagger made from refined skiter scales",
                ChatColor.GRAY+"can slice and cut flesh with ease.",
                ChatColor.YELLOW+"This weapon deals 2x more damage to forest mobs,",
                ChatColor.YELLOW+"and increases drop chances of forest mobs by 40%"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createsscaleexecutor(){
        ItemStack item = new ItemStack(Material.FLINT, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_EXECUTOR");
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
        meta.setDisplayName(ChatColor.DARK_PURPLE+"The Scale Executor");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Melee Weapon",
                ChatColor.GRAY+"A very powerful melee weapon forged",
                ChatColor.GRAY+"from sealed scales, demolishes anything it touches.",
                ChatColor.YELLOW+"This weapon deals 3x more damage to forest mobs,",
                ChatColor.YELLOW+"and increases drop chances of forest mobs by 55%",
                ChatColor.translateAlternateColorCodes('&', "&6&lSPECIAL STAT &r&9Skiters drop special items,"),
                ChatColor.BLUE+"when killed with this weapon"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createknightedsythe(){
        ItemStack item = new ItemStack(Material.STONE_HOE, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_SWORD");
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
        meta.setDisplayName(ChatColor.GOLD+"Knighted Sythe");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Melee Weapon",
                ChatColor.GRAY+"A melee weapon strengthened with the power of",
                ChatColor.GRAY+"Forest Guards. Very interesting to study",
                ChatColor.YELLOW+"This weapon deals 5x more damage to forest mobs,",
                ChatColor.YELLOW+"and increases your speed by 50%"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createbigminer(){
        ItemStack item = new ItemStack(Material.YELLOW_FLOWER, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "ADMIN_SWORD");
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
        meta.setDisplayName(ChatColor.GOLD+"The Big Miner");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Melee Weapon",
                ChatColor.GRAY+"Boom Bing BOOM",
                ChatColor.GRAY+"Will eat you :)",
                " ",
                ChatColor.YELLOW+"Doubles as a Pickaxe"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createessensedagger(){
        ItemStack item = new ItemStack(Material.WOOD_SWORD, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "TYRA_DAGGER");
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
        meta.setDisplayName(ChatColor.GOLD+"Essense Dagger");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Melee Weapon",
                ChatColor.GRAY+"A melee weapon forged from the power of the forest",
                ChatColor.GRAY+"that can harness positive energy for powerful results",
                ChatColor.YELLOW+"This weapon fires an orb of light when you right click.",
                ChatColor.YELLOW+"the orb damages mobs in a 3 block radius when it collides with a",
                ChatColor.YELLOW+"block or an entity, damage from this weapon is multiplied by 7.5"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
}
