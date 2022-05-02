package com.litf.death.Invens.AuctionHouse;

import com.litf.death.Events.DestinyEvents;
import com.litf.death.Events.UtilEvents;
import com.litf.death.Items.Armor;
import com.litf.death.Items.Items;
import com.litf.death.Items.Misc;
import com.litf.death.Items.Weapons;
import com.litf.death.Main;
import com.litf.death.Ranks.Calls;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class AuctionHouseBrowse implements InventoryHolder {
    private static Inventory inv;
    public static List<Integer> wll = new ArrayList<>();
    public static HashMap<Integer, Integer> rnums = new HashMap<>();

    public AuctionHouseBrowse(int pgnum) {
        inv = Bukkit.createInventory(this,54, "Active Auctions");
        init(pgnum);
    }
    private static void init(int pgnum){
        int ct=0;
        List<Integer> strs = new ArrayList<>();
        for(Integer num: Main.serverdata.getConfig().getIntegerList("Auctions.Numbers")){
            try {
                if (UtilEvents.getItemCata(Main.serverdata.getConfig().getString("Auctions." + num + ".ID")) == pgnum)
                    strs.add(num);
            }catch(NullPointerException ignored){}
        }
        for(int i = 0; i < 54; i++){
            switch(i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 10:
                case 17:
                case 19:
                case 26:
                case 28:
                case 35:
                case 37:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:

                case 50:
                case 53:
                    inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
                    wll.add(i);
                    rnums.put(i, ct);
                    break;
                case 49:
                    ItemStack item = createItem(ChatColor.YELLOW + "Go Back", Material.ARROW, null, (byte) 0);
                    inv.setItem(i,item);
                    wll.add(i);
                    rnums.put(i, ct);
                    break;
                case 51:
                    item = createItem(ChatColor.YELLOW + "Filter", Material.HOPPER, Arrays.asList(
                            ChatColor.GOLD+"COMING SOON!",
                            ChatColor.GREEN+"> Newest First",
                            ChatColor.GRAY+"Lowest Price",
                            ChatColor.GRAY+"Highest Price"
                    ), (byte) 0);
                    inv.setItem(i, item);
                    wll.add(i);
                    rnums.put(i, ct);
                    break;
                case 52:
                    item = createItem(ChatColor.YELLOW + "Search", Material.SIGN, Collections.singletonList(ChatColor.GOLD+"Click to search for items (COMING SOON)"), (byte) 0);
                    inv.setItem(i, item);
                    wll.add(i);
                    rnums.put(i, ct);
                    break;
                case 9:
                    item = createItem(ChatColor.YELLOW + "Weapons", Material.WOOD_SWORD, null, (byte) 0);
                    if (pgnum == 0) {
                        ItemMeta meta = item.getItemMeta();
                        meta.addEnchant(Enchantment.DURABILITY, 1, true);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        item.setItemMeta(meta);
                    }
                    inv.setItem(i, item);
                    wll.add(i);
                    rnums.put(i, ct);
                    break;
                case 18:
                    item = createItem(ChatColor.YELLOW + "Items/Tools", Material.DIAMOND_PICKAXE, null, (byte) 0);
                    if (pgnum == 1) {
                        ItemMeta meta = item.getItemMeta();
                        meta.addEnchant(Enchantment.DURABILITY, 1, true);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        item.setItemMeta(meta);
                    }
                    inv.setItem(i, item);
                    wll.add(i);
                    rnums.put(i, ct);
                    break;
                case 27:
                    item = createItem(ChatColor.YELLOW + "Armor", Material.IRON_CHESTPLATE, null, (byte) 0);
                    if (pgnum == 2) {
                        ItemMeta meta = item.getItemMeta();
                        meta.addEnchant(Enchantment.DURABILITY, 1, true);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(meta);
                    }
                    inv.setItem(i, item);
                    wll.add(i);
                    rnums.put(i, ct);
                    break;
                case 36:
                    item = createItem(ChatColor.YELLOW + "Misc", Material.RAW_FISH, null, (byte) 3);
                    if (pgnum == 3) {
                        ItemMeta meta = item.getItemMeta();
                        meta.addEnchant(Enchantment.DURABILITY, 1, true);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(meta);
                    }
                    inv.setItem(i, item);
                    wll.add(i);
                    rnums.put(i, ct);
                    break;
                default:
                    int cti = ct;
                    try {
                        ct = strs.get(ct);
                        Date date = new Date();
                        item = UtilEvents.getItemWithID(Main.serverdata.getConfig().getString("Auctions." + ct + ".ID"));
                        if (item != null) {
                            if (Main.serverdata.getConfig().getLong("Auctions." + ct + ".Expire") < date.getTime()) {
                                AuctionHouseEvents.moveAuctionToExpired(ct, Bukkit.getPlayer(UUID.fromString(Main.serverdata.getConfig().getString("Auctions." + ct + ".Owner"))));
                            } else {
                                item = UtilEvents.getItemWithID(Main.serverdata.getConfig().getString("Auctions." + ct + ".ID"));
                                if (!Main.serverdata.getConfig().getString("Auctions." + ct + ".Destiny").equals("None")) {
                                    String[] destsplits = Main.serverdata.getConfig().getString("Auctions." + ct + ".Destiny").split("\\.");
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
                                item.setAmount(Main.serverdata.getConfig().getInt("Auctions." + ct + ".Amount"));
                                meta.setDisplayName(Main.serverdata.getConfig().getString("Auctions." + ct + ".Name"));
                                List<String> lore = Main.serverdata.getConfig().getStringList("Auctions." + ct + ".Lore");
                                lore.add(" ");
                                lore.add(ChatColor.GRAY + "Seller: " + Calls.getStringRank(Bukkit.getPlayer(UUID.fromString(Main.serverdata.getConfig().getString("Auctions." + ct + ".Owner")))) + " " + Bukkit.getPlayer(UUID.fromString(Main.serverdata.getConfig().getString("Auctions." + ct + ".Owner"))).getName());
                                lore.add(ChatColor.GRAY + "Cost: " + ChatColor.GOLD + Main.serverdata.getConfig().getInt("Auctions." + ct + ".Cost"));
                                DateFormat simple = new SimpleDateFormat("mm:ss");
                                Date result = new Date(Main.serverdata.getConfig().getLong("Auctions." + ct + ".Expire") - date.getTime());
                                lore.add(ChatColor.GRAY + "Time Left:" + ChatColor.GREEN + " " + simple.format(result));
                                meta.setLore(lore);
                                Main.serverdata.getConfig().getInt("Auctions." + ct + ".Expire");
                                item.setItemMeta(meta);
                                inv.addItem(item);
                            }
                        }
                        rnums.put(i, ct);
                        ct = cti;
                        ct++;
                    }catch(IndexOutOfBoundsException ignored){}
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
