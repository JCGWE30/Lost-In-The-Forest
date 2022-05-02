package com.litf.death.Invens.AuctionHouse;

import com.litf.death.Events.DestinyEvents;
import com.litf.death.Events.Scoreboardinit;
import com.litf.death.Events.UtilEvents;
import com.litf.death.Main;
import com.litf.death.Packeets.SignEvent;
import com.litf.death.Ranks.Calls;
import jdk.nashorn.internal.parser.JSONParser;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.block.Block;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class AuctionHouseEvents implements Listener{
    public static HashMap<Player, Inventory> pinv = new HashMap<>();
    public static HashMap<Player, Integer> pint = new HashMap<>();
    public static HashMap<Player, Integer> cpage = new HashMap<>();
    @EventHandler
    public static void denyclicker(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof AuctionHouseMain) {
            e.setCancelled(true);
            if (e.getSlot() == 16) {
                AuctionHouseCreate gui = new AuctionHouseCreate();
                e.getWhoClicked().openInventory(gui.getInventory());
                pint.put((Player) e.getWhoClicked(), 10);
            }
            if (e.getSlot() == 10) {
                AuctionHouseBrowse gui = new AuctionHouseBrowse(0);
                if(cpage.containsKey(e.getWhoClicked()))
                    gui = new AuctionHouseBrowse(cpage.get(e.getWhoClicked()));
                e.getWhoClicked().openInventory(gui.getInventory());
                Main.UpdateAuctionHouse((Player) e.getWhoClicked());
            }
            if (e.getSlot() == 13) {
                AuctionHouseItems gui = new AuctionHouseItems((Player) e.getWhoClicked());
                e.getWhoClicked().openInventory(gui.getInventory());
            }
        }
        if (e.getInventory().getHolder() instanceof AuctionHouseBrowse) {
            e.setCancelled(true);
            switch(e.getSlot()) {
                case 9:
                    AuctionHouseBrowse gui = new AuctionHouseBrowse(0);
                    e.getWhoClicked().openInventory(gui.getInventory());
                    cpage.put((Player) e.getWhoClicked(),0);
                    break;
                case 18:
                    gui = new AuctionHouseBrowse(1);
                    e.getWhoClicked().openInventory(gui.getInventory());
                    cpage.put((Player) e.getWhoClicked(),1);
                    break;
                case 27:
                    gui = new AuctionHouseBrowse(2);
                    e.getWhoClicked().openInventory(gui.getInventory());
                    cpage.put((Player) e.getWhoClicked(),2);
                    break;
                case 36:
                    gui = new AuctionHouseBrowse(3);
                    e.getWhoClicked().openInventory(gui.getInventory());
                    cpage.put((Player) e.getWhoClicked(),3);
                    break;
                case 49:
                    AuctionHouseMain guia = new AuctionHouseMain();
                    e.getWhoClicked().openInventory(guia.getInventory());
            }
            if (AuctionHouseBrowse.wll.contains(e.getSlot()))
                return;
            try{
            List<Integer> numbs = new ArrayList<>();
            if (Main.serverdata.getConfig().getIntegerList("Auctions.Numbers") != null) {
                numbs = Main.serverdata.getConfig().getIntegerList("Auctions.Numbers");
            }
            int i = numbs.get(AuctionHouseBrowse.rnums.get(e.getSlot()));
            Scoreboardinit.insureCoins((Player) e.getWhoClicked());
            if (UUID.fromString(Main.serverdata.getConfig().getString("Auctions." + i + ".Owner")).equals(e.getWhoClicked().getUniqueId())) {
                moveAuctionToExpired(i, (Player) e.getWhoClicked());

                AuctionHouseBrowse gui = new AuctionHouseBrowse(0);
                if(cpage.containsKey(e.getWhoClicked()))
                    gui = new AuctionHouseBrowse(cpage.get(e.getWhoClicked()));
                e.getWhoClicked().openInventory(gui.getInventory());
                e.getWhoClicked().sendMessage(ChatColor.GREEN + "Canceled Auction");
            } else {
                if (Scoreboardinit.getcoins((Player) e.getWhoClicked()) >= Main.serverdata.getConfig().getInt("Auctions." + String.valueOf(e.getSlot() + 1) + ".Cost")) {
                    Date date = new Date();
                    ItemStack item = UtilEvents.getItemWithID(Main.serverdata.getConfig().getString("Auctions." + i + ".ID"));
                    if (item == null) {
                        AuctionHouseBrowse gui = new AuctionHouseBrowse(0);
                        e.getWhoClicked().openInventory(gui.getInventory());
                    } else {
                        if (Main.serverdata.getConfig().getLong("Auctions." + i + ".Expire") < date.getTime()) {
                            moveAuctionToExpired(i, Bukkit.getPlayer(UUID.fromString(Main.serverdata.getConfig().getString("Auctions." + i + ".Owner"))));
                        } else {
                            if (!Main.serverdata.getConfig().getString("Auctions." + i + ".Destiny").equals("None")) {
                                String[] destsplits = Main.serverdata.getConfig().getString("Auctions." + i + ".Destiny").split("\\.");
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
                            numbs.remove(i - 1);
                            Main.serverdata.getConfig().set("Auctions.Numbers", numbs);
                            ItemMeta meta = item.getItemMeta();
                            item.setAmount(Main.serverdata.getConfig().getInt("Auctions." + i + ".Amount"));
                            meta.setDisplayName(Main.serverdata.getConfig().getString("Auctions." + i + ".Name"));
                            if (Bukkit.getPlayer(UUID.fromString(Main.serverdata.getConfig().getString("Auctions." + i + ".Owner"))).isOnline()) {
                                Scoreboardinit.addcoins(Bukkit.getPlayer(UUID.fromString(Main.serverdata.getConfig().getString("Auctions." + i + ".Owner"))), Main.serverdata.getConfig().getInt("Auctions." + i + ".Cost"));
                                Bukkit.getPlayer(UUID.fromString(Main.serverdata.getConfig().getString("Auctions." + i + ".Owner"))).sendMessage(ChatColor.GREEN + "Someone purchased an item of yours! as such " + Main.serverdata.getConfig().getInt("Auctions." + i + ".Cost") + " Coins have been added to your account");
                            } else {
                                Scoreboardinit.addcoinsoffline(Main.serverdata.getConfig().getString("Auctions." + i + ".Owner"), Main.serverdata.getConfig().getInt("Auctions." + i + ".Cost"));
                            }
                            Scoreboardinit.subcoins((Player) e.getWhoClicked(), Main.serverdata.getConfig().getInt("Auctions." + String.valueOf(e.getSlot() + 1) + ".Cost"));
                            Main.serverdata.getConfig().set("Auctions.Count", Main.serverdata.getConfig().getInt("Auctions.Count") - 1);
                            Main.serverdata.getConfig().set("Auctions." + i, null);
                            Main.serverdata.saveConfig();
                            e.getWhoClicked().getInventory().addItem(item);
                            AuctionHouseBrowse gui = new AuctionHouseBrowse(0);
                            e.getWhoClicked().openInventory(gui.getInventory());
                        }
                    }
                } else {
                    ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.VILLAGER_NO, 1f, 1f);
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You cannot afford this");
                }
            }
        }catch(Exception ignored){}
    }
            if (e.getInventory().getHolder() instanceof AuctionHouseItems) {
                try {
                    if (e.getCurrentItem() != null) {
                        e.setCancelled(true);
                        e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
                        List<Integer> ints = Main.data.getConfig().getIntegerList(e.getWhoClicked().getUniqueId().toString() + ".Auctions.nums");
                        Main.data.getConfig().set(e.getWhoClicked().getUniqueId().toString() + ".Auctions." + ints.get(e.getSlot()).toString(), null);
                        ints.remove(e.getSlot());
                        Main.data.getConfig().set(e.getWhoClicked().getUniqueId().toString() + ".Auctions.nums", ints);
                        AuctionHouseItems gui = new AuctionHouseItems((Player) e.getWhoClicked());
                        Main.data.saveConfig();
                        e.getWhoClicked().openInventory(gui.getInventory());
                    }
                }catch(Exception ignored){}
            }
            if (e.getInventory().getHolder() instanceof AuctionHouseCreate) {
                e.setCancelled(true);
                if (e.getClickedInventory().getHolder() instanceof AuctionHouseCreate) {
                    if (e.getSlot() == 13) {
                        if (e.getInventory().getItem(34).getDurability() == 13) {
                            e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
                            e.getInventory().setItem(13, AuctionHouseCreate.createItem(ChatColor.YELLOW + "Click the item you want to sell", Material.CHEST, null, (byte) 0));
                            e.getInventory().setItem(34, AuctionHouseCreate.createItem(ChatColor.YELLOW + "You cant create this auction", Material.STAINED_CLAY, null, (byte) 14));
                        }
                    }
                    if (e.getSlot() == 28) {
                        pinv.put((Player) e.getWhoClicked(), e.getInventory());
                        Player p = (Player)e.getWhoClicked();
                        org.bukkit.block.Block b = p.getWorld().getBlockAt(p.getLocation().getBlockX(), 256, p.getLocation().getBlockZ());
                        Material bt = b.getType();
                        b.setType(Material.SIGN);
                        BlockPosition bp = new BlockPosition(p.getLocation().getX(), 256, p.getLocation().getZ());
                        if((p.getWorld().getBlockAt(bp.getX(),bp.getY(),bp.getZ()).getState() instanceof Sign)) {
                            Sign sign = (Sign) p.getWorld().getBlockAt(bp.getX(),bp.getY(),bp.getZ()).getState();
                            sign.setLine(1, "^^^^");
                            sign.setLine(2, "Input price");
                            sign.setLine(3, "of item");
                            sign.update();
                        }
                        ((CraftPlayer)e.getWhoClicked()).getHandle().playerConnection.sendPacket(new PacketPlayOutOpenSignEditor(bp));
                        p.getWorld().getBlockAt(bp.getX(),bp.getY(),bp.getZ()).setType(bt);
                    }
                    if (e.getSlot() == 34) {
                        if (e.getCurrentItem().getDurability() == 13) {
                            Inventory inv = e.getInventory();
                            e.getWhoClicked().sendMessage(ChatColor.GREEN + "You have put " + inv.getItem(13).getItemMeta().getDisplayName() + ChatColor.DARK_GRAY + " x" + inv.getItem(13).getAmount()
                                    + ChatColor.GREEN + " on the auction house for " + ChatColor.GOLD + pint.get(e.getWhoClicked()) + " Coins");
                            e.getWhoClicked().closeInventory();
                            List<Integer> numbs = new ArrayList<>();
                            if(Main.serverdata.getConfig().getIntegerList("Auctions.Numbers")!=null){
                                numbs = Main.serverdata.getConfig().getIntegerList("Auctions.Numbers");
                            }
                            Main.serverdata.getConfig().set("Auctions.Count", Main.serverdata.getConfig().getInt("Auctions.Count") + 1);
                            numbs.add(Main.serverdata.getConfig().getInt("Auctions.Count"));
                            Main.serverdata.getConfig().set("Auctions.Numbers", numbs);
                            Main.serverdata.getConfig().set("Auctions." + Main.serverdata.getConfig().getInt("Auctions.Count") + ".Name",
                                    inv.getItem(13).getItemMeta().getDisplayName());
                            Main.serverdata.getConfig().set("Auctions." + Main.serverdata.getConfig().getInt("Auctions.Count") + ".Lore",
                                    inv.getItem(13).getItemMeta().getLore());
                            Main.serverdata.getConfig().set("Auctions." + Main.serverdata.getConfig().getInt("Auctions.Count") + ".Destiny",
                                    DestinyEvents.getDestiny(inv.getItem(13)));
                            Main.serverdata.getConfig().set("Auctions." + Main.serverdata.getConfig().getInt("Auctions.Count") + ".Owner",
                                    e.getWhoClicked().getUniqueId().toString());
                            Date date = new Date();
                            Main.serverdata.getConfig().set("Auctions." + Main.serverdata.getConfig().getInt("Auctions.Count") + ".Expire",
                                    date.getTime() + 600000);
                            Main.serverdata.getConfig().set("Auctions." + Main.serverdata.getConfig().getInt("Auctions.Count") + ".ID",
                                    UtilEvents.getId(inv.getItem(13)));
                            Main.serverdata.getConfig().set("Auctions." + Main.serverdata.getConfig().getInt("Auctions.Count") + ".Cost",
                                    pint.get(e.getWhoClicked()));
                            Main.serverdata.getConfig().set("Auctions." + Main.serverdata.getConfig().getInt("Auctions.Count") + ".Amount",
                                    inv.getItem(13).getAmount());
                            pint.remove(e.getWhoClicked());
                            Main.serverdata.saveConfig();
                        }
                    }
                } else {
                    e.getInventory().setItem(34, AuctionHouseCreate.createItem(ChatColor.YELLOW + "Confirm?", Material.STAINED_CLAY, null, (byte) 13));
                    e.getInventory().setItem(13, e.getCurrentItem());
                    e.getWhoClicked().getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
                }
            }
        }
    @EventHandler
    public static void chatmsg(AsyncPlayerChatEvent e){
        if(pinv.get(e.getPlayer())!=null){
            Inventory inv = pinv.get(e.getPlayer());
            e.setCancelled(true);
            ItemStack price = inv.getItem(28);
            ItemMeta meta = price.getItemMeta();
            try {
                if (Integer.parseInt(e.getMessage()) > 0) {
                    if(meta.hasLore()){
                        meta.setLore(new ArrayList<>());
                    }
                    pint.put(e.getPlayer(), Integer.parseInt(e.getMessage()));
                    List<String> lore = Collections.singletonList(ChatColor.GRAY +"Current price: "+e.getMessage());
                    meta.setLore(lore);
                }
            }catch(NumberFormatException ignored){}
            price.setItemMeta(meta);
            inv.setItem(28, price);
            if(inv.getItem(13).getType()!=Material.CHEST){
                inv.setItem(34, AuctionHouseCreate.createItem(ChatColor.YELLOW+"Confirm?", Material.STAINED_CLAY, null, (byte) 13));
            }
            e.getPlayer().openInventory(inv);
            pinv.remove(e.getPlayer());
        }
    }
    public static void moveAuctionToExpired(int id, Player p){
        String i = String.valueOf(id);
        if(Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Auctions.nums")==null){
            Main.data.getConfig().set(p.getUniqueId().toString()+".Auctions.nums", new ArrayList<>());
            Main.data.saveConfig();
        }
        List<Integer> numbs = new ArrayList<>();
        if(Main.serverdata.getConfig().getIntegerList("Auctions.Numbers")!=null){
            numbs = Main.serverdata.getConfig().getIntegerList("Auctions.Numbers");
        }
        numbs.remove(numbs.indexOf(id));
        Main.serverdata.getConfig().set("Auctions.Numbers", numbs);
        List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Auctions.nums");
        ints.add(id);
        Main.data.getConfig().set(p.getUniqueId().toString()+".Auctions.nums", ints);
        Main.data.getConfig().set(p.getUniqueId().toString()+".Auctions."+i+".Name",
                Main.serverdata.getConfig().getString("Auctions."+i+".Name"));

        Main.data.getConfig().set(p.getUniqueId().toString()+".Auctions."+i+".Lore",
                Main.serverdata.getConfig().getStringList("Auctions."+i+".Lore"));

        Main.data.getConfig().set(p.getUniqueId().toString()+".Auctions."+i+".Destiny",
                Main.serverdata.getConfig().getString("Auctions."+i+".Destiny"));

        Main.data.getConfig().set(p.getUniqueId().toString()+".Auctions."+i+".ID",
                Main.serverdata.getConfig().getString("Auctions."+i+".ID"));

        Main.data.getConfig().set(p.getUniqueId().toString()+".Auctions."+i+".Amount",
                Main.serverdata.getConfig().getInt("Auctions."+i+".Amount"));
        Main.data.saveConfig();
        Main.serverdata.getConfig().set("Auctions.Count",Main.serverdata.getConfig().getInt("Auctions.Count")-1);
        Main.serverdata.getConfig().set("Auctions."+i, null);
        Main.serverdata.saveConfig();
    }
    @EventHandler
    public static void SetAPrice(SignEvent e) throws Exception{
        if(e.getPos().getY()==256){
            if(pinv.get(e.getPlayer())!=null){
                Inventory inv = pinv.get(e.getPlayer());
                e.setCancelled(true);
                ItemStack price = inv.getItem(28);
                ItemMeta meta = price.getItemMeta();
                try {
                System.out.println(Integer.parseInt(e.getLines()[0].toString().split("'")[1]) > 0);
                    if (Integer.parseInt(e.getLines()[0].toString().split("'")[1]) > 0) {
                        if(meta.hasLore()){
                            meta.setLore(new ArrayList<>());
                        }
                        pint.put(e.getPlayer(), Integer.parseInt(e.getLines()[0].toString().split("'")[1]));
                        List<String> lore = Collections.singletonList(ChatColor.GRAY +"Current price: "+e.getLines()[0].toString().split("'")[1]);
                        meta.setLore(lore);
                    }
                }catch(NumberFormatException ignored){}
                price.setItemMeta(meta);
                inv.setItem(28, price);
                if(inv.getItem(13).getType()!=Material.CHEST){
                    inv.setItem(34, AuctionHouseCreate.createItem(ChatColor.YELLOW+"Confirm?", Material.STAINED_CLAY, null, (byte) 13));
                }
                e.getPlayer().openInventory(inv);
                pinv.remove(e.getPlayer());
            }
        }
    }
}
