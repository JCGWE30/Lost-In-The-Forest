package com.litf.death.Items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
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

public class Items {
    //VARS
    public static List<ItemStack> items = new ArrayList<>();
    public static void init(){
        createskiterdestiny();
        createguarddestiny();
        createmanipulationcube();
        createnurealtransmit();
        createhighroller();
        createtyrantkey();
        createtyrantdestiny();
        createpowershield();
        createdestinydupe();
        createtablet();
        createstarterpick();
        createimprovisedpick();
        createreinforcedpickaxe();
        createoreempower();
    }
    public static void createskiterdestiny(){
        ItemStack item = getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE2NDVkZmQ3N2QwOTkyMzEwN2IzNDk2ZTk0ZWViNWMzMDMyOWY5N2VmYzk2ZWQ3NmUyMjZlOTgyMjQifX19==");
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "SCALE_DESTINY");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE2NDVkZmQ3N2QwOTkyMzEwN2IzNDk2ZTk0ZWViNWMzMDMyOWY5N2VmYzk2ZWQ3NmUyMjZlOTgyMjQifX19");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Skiter infuser");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Destiny infuser",
                ChatColor.GRAY+"Combine with a compatable item in an anvil",
                ChatColor.GRAY+"to give it a destiny,"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createguarddestiny(){
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_DESTINY");
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
        meta.setDisplayName(ChatColor.GOLD+"Tailion coating");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Destiny infuser",
                ChatColor.GRAY+"Combine with a compatable item in an anvil",
                ChatColor.GRAY+"to give it a destiny,"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createmanipulationcube(){
        ItemStack item = getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzc0MDBlYTE5ZGJkODRmNzVjMzlhZDY4MjNhYzRlZjc4NmYzOWY0OGZjNmY4NDYwMjM2NmFjMjliODM3NDIyIn19fQ==");
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_CUBE");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzc0MDBlYTE5ZGJkODRmNzVjMzlhZDY4MjNhYzRlZjc4NmYzOWY0OGZjNmY4NDYwMjM2NmFjMjliODM3NDIyIn19fQ==");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Manipulation Cube");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Cube",
                ChatColor.GRAY+"A cube which can understand and use knowledge",
                ChatColor.GRAY+"to benefit the user in many ways when held in their hotbar",
                " ",
                ChatColor.GRAY+"Buffs: "+ChatColor.RED+"None"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createnurealtransmit(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "CUBE_NEURAL");
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
        meta.setDisplayName(ChatColor.BLUE+"Manipulation Scroll (Neural link)");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Manipulation scroll",
                ChatColor.GRAY+"Apply this scroll to a manipulation cube via an anvil",
                ChatColor.GRAY+"to give it the "+ChatColor.BLUE+"Neural link "+ChatColor.GRAY+"Buff",
                " ",
                ChatColor.BLUE+"Neural link: "+ChatColor.GRAY+"Items you drop are teleported into your inventory,",
                ChatColor.GRAY+"however, if your inventory is full the item will be deletet"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createhighroller(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "CUBE_ROLLER");
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
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Manipulation Scroll (High Roller)");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Manipulation scroll",
                ChatColor.GRAY+"Apply this scroll to a manipulation cube via an anvil",
                ChatColor.GRAY+"to give it the "+ChatColor.DARK_PURPLE+"High Roller "+ChatColor.GRAY+"Buff",
                " ",
                ChatColor.DARK_PURPLE+"High Roller: "+ChatColor.GRAY+"Your luck is tripled, and you gain the ability",
                ChatColor.GRAY+"to get a special item from all forest mobs"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createtyrantkey(){
        ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GUARD_KEY");
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
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Tyrant's Den Key");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Boss Key",
                ChatColor.GRAY+"Use this key on the Forest Tyrant's door to unlock it, and",
                ChatColor.GRAY+"summon the Forest Tyrant!",
                " ",
                ChatColor.DARK_GRAY+"Uses: 5/5"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createtyrantdestiny(){
        ItemStack item = new ItemStack(Material.IRON_BLOCK, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "TYRA_FIERCE");
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
        meta.setDisplayName(ChatColor.GOLD+"Fierce offense");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Crafting Ingredient",
                ChatColor.GRAY+"A block of iron that is very unstable",
                ChatColor.GRAY+"grants the user immense abilities when harnessed",
                " ",
                ChatColor.YELLOW+"Right-click to view recipe"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createpowershield(){
        ItemStack item = new ItemStack(Material.BOOK, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "CUBE_POWER");
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
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Manipulation Scroll (Power Shield)");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Manipulation scroll",
                ChatColor.GRAY+"Apply this scroll to a manipulation cube via an anvil",
                ChatColor.GRAY+"to give it the "+ChatColor.DARK_PURPLE+"Power Shield "+ChatColor.GRAY+"Buff",
                " ",
                ChatColor.DARK_PURPLE+"Power Shield: "+ChatColor.GRAY+"Deal 20% more damage to mobs and",
                ChatColor.GRAY+"take 5% less damage for every 20 health a mob has"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createdestinydupe(){
        ItemStack item = getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2NhNDVlZjU4MjFhOGIxMDdjYmZiYTdkNjZlOTk3ZmI2YWJlNTUyMWMxNTVjZWUyZjI0YjM0YjNkOTFhNSJ9fX0=");
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "TYRA_DUPER");
        ust.setBoolean("ust", true);
        NBTTagCompound skvalue = new NBTTagCompound();
        skvalue.setString("SKULL_VALUE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2NhNDVlZjU4MjFhOGIxMDdjYmZiYTdkNjZlOTk3ZmI2YWJlNTUyMWMxNTVjZWUyZjI0YjM0YjNkOTFhNSJ9fX0=");
        lis.add(id);
        lis.add(ust);
        lis.add(skvalue);
        comp.set("comp", lis);
        iten.setTag(comp);
        item = CraftItemStack.asBukkitCopy(iten);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+"Destiny Duplicator");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Destiny Manipulator",
                ChatColor.GRAY+"Apply this device to an item with a destiny on it",
                ChatColor.GRAY+"to duplicate the stats on the destiny. Only works if the",
                ChatColor.GRAY+"destiny is maximun level"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createtablet(){
        ItemStack item = new ItemStack(Material.DETECTOR_RAIL, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "TYRA_TABLET");
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
        meta.setDisplayName(ChatColor.GOLD+"Spiritual Tablet");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Tool",
                ChatColor.GRAY+"A tablet which allows you to access",
                ChatColor.GRAY+"to useful menu's",
                " ",
                ChatColor.YELLOW+"Right-click to open tablet"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createstarterpick(){
        ItemStack item = new ItemStack(Material.WOOD_PICKAXE, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "CAVE_PICK");
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
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setDisplayName(ChatColor.GRAY+"Starter pickaxe");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Tool",
                ChatColor.GRAY+"A pickaxe courtesy of Miner Joe.",
                ChatColor.GRAY+"will help you get started in the underground"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createimprovisedpick(){
        ItemStack item = new ItemStack(Material.STONE_PICKAXE, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "CAVE_IMPROV");
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
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setDisplayName(ChatColor.GREEN+"Improvised pickaxe");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Tool",
                ChatColor.GRAY+"An improvised pickaxe, will get the job done",
                ChatColor.GRAY+"and does it right"
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createreinforcedpickaxe(){
        ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "CAVE_FORCEDRE");
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
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setDisplayName(ChatColor.BLUE+"Reinforced Pickaxe");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Tool",
                ChatColor.GRAY+"A Reinforced Pickaxe, with this you are",
                ChatColor.GRAY+"a SERIOUS miner, so prove that you are."
        ));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        items.add(item);
    }
    public static void createoreempower(){
        ItemStack item = new ItemStack(Material.BEACON, 1);
        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
        NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
        NBTTagList lis = new NBTTagList();
        NBTTagCompound id = new NBTTagCompound();
        NBTTagCompound ust = new NBTTagCompound();
        id.setString("id", "GOLEM_SPAWNER");
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
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setDisplayName(ChatColor.DARK_PURPLE+"Ore Enchanter");
        meta.setLore(Arrays.asList(
                ChatColor.DARK_GRAY+"Item",
                ChatColor.GRAY+"A beacon of some sort with the power to",
                ChatColor.GRAY+"modify ore giving them dangerous properties."
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
