package com.litf.death.Events;

import com.litf.death.Files.LocationManager;
import com.litf.death.Invens.PlayerStats.PlayerStatsStats;
import com.litf.death.Invens.TyrantBossLoot.TyrantLoot;
import com.litf.death.Items.Armor;
import com.litf.death.Items.Items;
import com.litf.death.Items.Misc;
import com.litf.death.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class FTyrantEvents implements Listener{
    public static Plugin plg;
    public static LocationManager serverdata = Main.serverdata;
    public static List<Integer> TyrantSpots = new ArrayList<>();
    public static ArmorStand tyradrop = null;
    public static ArmorStand key = null;
    public static HashMap<Player, Double> tryadamage = new HashMap<>();
    public FTyrantEvents(Plugin plog){
        this.plg=plog;
    }
    public static void counttyrand(){
        Main.plg.getServer().getScheduler().scheduleSyncDelayedTask(Main.plg, new Runnable() {
            @Override
            public void run() {
                Date date = new Date();
                DateFormat simple = new SimpleDateFormat("mm:ss");
                Date result = new Date(Main.serverdata.getConfig().getLong("FTyrantData.LastSpawn") - date.getTime()+180000);
                if(result.getTime()>0){
                    key.setHelmet(Items.getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODYzZmQ3ODIyMmQ1OTg4YzZiZTFjYzlmYTk2ZTg1Mjg5MTViYjY5NzQ2NDY0ZDIzOGY5MzZlYmViYjIzYzUyIn19fQ==="));
                    key.setCustomName(ChatColor.RED + "Tyrant Respawns in "+simple.format(result));
                    counttyrand();
                }else {
                    key.setHelmet(Items.getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2Q4NzZmODJkNTNjNmIxM2Q4OGViMjFiZTMyMTE5ZjFhYWI2MzFkZGY2ZjZhZTlkNmJjMzc0MmVkMWRlYSJ9fX0="));
                    key.setCustomName(ChatColor.GREEN + "Tyrant Ready");
                }
            }
        }, 10);
    }
    public static void enableFTyrant(){
        if(serverdata.getConfig().getIntegerList("FTyrantData.Loc").isEmpty()){
            serverdata.getConfig().set("FTyrantData.Loc", Arrays.asList(10,10,10));
            serverdata.saveConfig();
        }

        if(serverdata.getConfig().getString("ServerInfo.MainWorld")!=null) {

            TyrantSpots = serverdata.getConfig().getIntegerList("FTyrantData.Loc");
            Block b = Bukkit.getWorld(UUID.fromString(serverdata.getConfig().getString("ServerInfo.MainWorld"))).getBlockAt(TyrantSpots.get(0), TyrantSpots.get(1), TyrantSpots.get(2));
            b.setType(Material.IRON_BLOCK);
            Location temploc = b.getLocation();
            temploc.setX(temploc.getX()+0.5d);
            temploc.setY(temploc.getY()-0.4d);
            temploc.setZ(temploc.getZ()+0.5d);
            key = Bukkit.getWorld(UUID.fromString(serverdata.getConfig().getString("ServerInfo.MainWorld"))).spawn(temploc, ArmorStand.class);
            Date date = new Date();
            DateFormat simple = new SimpleDateFormat("mm:ss");
            Date result = new Date(Main.serverdata.getConfig().getLong("FTyrantData.LastSpawn") - date.getTime()+180000);
            if(result.getTime()>0){
                key.setHelmet(Items.getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODYzZmQ3ODIyMmQ1OTg4YzZiZTFjYzlmYTk2ZTg1Mjg5MTViYjY5NzQ2NDY0ZDIzOGY5MzZlYmViYjIzYzUyIn19fQ==="));
                key.setCustomName(ChatColor.RED + "Tyrant Respawns in "+simple.format(result));
                counttyrand();
            }else {
                key.setHelmet(Items.getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2Q4NzZmODJkNTNjNmIxM2Q4OGViMjFiZTMyMTE5ZjFhYWI2MzFkZGY2ZjZhZTlkNmJjMzc0MmVkMWRlYSJ9fX0="));
                key.setCustomName(ChatColor.GREEN + "Tyrant Ready");
            }
            key.setCustomNameVisible(true);
            key.setVisible(false);
            key.setGravity(false);
        }

    }
    public static void disableFTryant(){
        if(serverdata.getConfig().getString("ServerInfo.MainWorld")!=null) {
            for(Entity ent:Bukkit.getWorld(UUID.fromString(serverdata.getConfig().getString("ServerInfo.MainWorld"))).getEntities()){
                if(ent.getType()== EntityType.ARMOR_STAND){
                    ent.remove();
                }
            }
            key.remove();
            List<Integer> temps = serverdata.getConfig().getIntegerList("FTyrantData.Loc");
            Block b =Bukkit.getWorld(UUID.fromString(serverdata.getConfig().getString("ServerInfo.MainWorld"))).getBlockAt(temps.get(0), temps.get(1), temps.get(2));
            b.setType(Material.AIR);
            if(tyradrop!=null){
                tyradrop.getWorld().getBlockAt(tyradrop.getLocation().add(0,1,0)).setType(Material.AIR);
                tyradrop.remove();
            }
        }
    }
    public static void DropTyeants(Player p, PlayerInteractAtEntityEvent e, Integer i, List<ItemStack> strs){
                    TyrantLoot gui = new TyrantLoot(strs);
                    e.getPlayer().openInventory(gui.getInventory());
                    /*org.bukkit.entity.Item itemd = e.getPlayer().getWorld().dropItemNaturally(((ArmorStand) e.getRightClicked()).getEyeLocation(), strs.get(i));
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CHICKEN_EGG_POP, 1f, 1f);
                    itemd.setVelocity(e.getPlayer().getLocation().getDirection().multiply(-0.2).setY(0.25));
                    Integer ine = i;
                    ine++;
                    DropTyeants(p, e, ine, strs);*/
                    tryadamage.remove(p);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b==&2&lLOOT CHEST OPENED! &r&2YOU GOT!&b=="));
                    for (ItemStack item : strs) {
                        p.sendMessage(item.getItemMeta().getDisplayName());
                    }
                    p.sendMessage(ChatColor.AQUA+"================================");
                    UtilEvents.checkplayers();
    }
    public static void TyrantDeath(Integer chance, HashMap<Player, Double> da) {
        Date date = new Date();
        Main.serverdata.getConfig().set("FTyrantData.LastSpawn", date.getTime());
        Main.serverdata.saveConfig();
        FTyrantEvents.enableFTyrant();
        List<Integer> TyraLootSpawn = Main.serverdata.getConfig().getIntegerList("FTyrantData.LLoc");
        Location temploc = new Location(Bukkit.getWorld(UUID.fromString(Main.serverdata.getConfig().getString("ServerInfo.MainWorld"))),TyraLootSpawn.get(0),TyraLootSpawn.get(1),TyraLootSpawn.get(2));

        temploc.setX(temploc.getBlockX()+0.5);
        temploc.setY(temploc.getBlockY()-0.4d);
        temploc.setZ(temploc.getBlockZ()+0.5);
        FTyrantEvents.tyradrop = Bukkit.getWorld(UUID.fromString(Main.serverdata.getConfig().getString("ServerInfo.MainWorld"))).spawn(temploc, ArmorStand.class);
        temploc.setY(temploc.getBlockY()+1);
        temploc.getWorld().getBlockAt(temploc).setType(Material.IRON_BLOCK);
        FTyrantEvents.tyradrop.setHelmet(Items.getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjdhYWRmZjlkZGM1NDZmZGNlYzZlZDU5MTljYzM5ZGZhOGQwYzA3ZmY0YmM2MTNhMTlmMmU2ZDdmMjU5MyJ9fX0="));
        FTyrantEvents.tyradrop.setCustomName(ChatColor.GREEN + "Right-Click to open loot chest");
        FTyrantEvents.tyradrop.setCustomNameVisible(true);
        FTyrantEvents.tyradrop.setVisible(false);
        FTyrantEvents.tyradrop.setGravity(false);
        tryadamage=da;
        List<ItemStack> drops = new ArrayList<>();
        Random rand = new Random();
        int rnd = rand.nextInt(200);
        rnd = chance + rnd;
        Object[] dbs = tryadamage.values().toArray();
        Arrays.sort(dbs, Collections.reverseOrder());
        List<Double> dobs = new ArrayList<>();
        for (Object o : dbs) {
            dobs.add(Double.valueOf(o.toString()));
        }
        for (Player p : tryadamage.keySet()) {
            boolean rdrop = false;
            try {
                p.sendMessage(ChatColor.GREEN + "=====TYRANT KILLED!=====");
                p.sendMessage(ChatColor.GREEN + "Your placing " + (dobs.indexOf(tryadamage.get(p)) + 1d));
                if (dobs.size() == 1) {
                    ItemStack item = Misc.items.get(12).clone();
                    item.setAmount(2);
                    drops.add(item);
                    if (rnd > 99 && dobs.get(0).equals(tryadamage.get(p))) {
                        int rnde = rand.nextInt(2);
                        if (rnde == 0) {
                            drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Misc.items.get(14).clone())));
                        } else {
                            drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Items.items.get(6).clone())));
                        }
                    }
                } else if (dobs.size() == 2) {
                    if (dobs.get(0).equals(tryadamage.get(p))) {
                        ItemStack item = Misc.items.get(12).clone();
                        item.setAmount(3);
                        drops.add(item);
                    } else if (dobs.get(1).equals(tryadamage.get(p))) {
                        ItemStack item = Misc.items.get(12).clone();
                        item.setAmount(2);
                        drops.add(item);
                    }
                    if (rnd > 99 && dobs.get(0).equals(tryadamage.get(p))) {
                        int rnde = rand.nextInt(2);
                        if (rnde == 0) {
                            drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Misc.items.get(14).clone())));
                        } else {
                            drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Items.items.get(6).clone())));
                        }
                    }
                } else if (dobs.size() >= 2) {
                    if (dobs.get(0).equals(tryadamage.get(p))) {
                        ItemStack item = Misc.items.get(12).clone();
                        item.setAmount(4);
                        drops.add(item);
                    } else if (dobs.get(1).equals(tryadamage.get(p))) {
                        ItemStack item = Misc.items.get(12).clone();
                        item.setAmount(3);
                        drops.add(item);
                    } else if (dobs.get(2).equals(tryadamage.get(p))) {
                        ItemStack item = Misc.items.get(12).clone();
                        item.setAmount(2);
                        drops.add(item);
                    } else {
                        ItemStack item = Misc.items.get(12).clone();
                        item.setAmount(1);
                        drops.add(item);
                    }
                    if (rnd > 99 && dobs.get(0).equals(tryadamage.get(p))) {
                        int rnde = rand.nextInt(2);
                        if (rnde == 0) {
                            drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Misc.items.get(14).clone())));
                        } else {
                            drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Items.items.get(6).clone())));
                        }
                    }
                    if (rnd > 124 && dobs.get(1).equals(tryadamage.get(p))) {
                        int rnde = rand.nextInt(2);
                        if (rnde == 0) {
                            drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Misc.items.get(14).clone())));
                        } else {
                            drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Items.items.get(6).clone())));
                        }
                    }
                }
                if (dobs.size() > 1) {
                    if (dobs.get(0).equals(tryadamage.get(p))) {
                        if (rnd > 159) {
                            if (!rdrop) {
                                rnd = rand.nextInt(3);
                                switch (rnd) {
                                    case 0:
                                        drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Armor.items.get(8))));
                                        break;
                                    case 1:
                                        drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Armor.items.get(9))));
                                        break;
                                    case 2:
                                        drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Armor.items.get(10))));
                                        break;
                                    case 3:
                                        drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Armor.items.get(11))));
                                        break;
                                }
                                rdrop=true;
                            }
                        }
                    }
                }
                rnd = rand.nextInt(190);
                rnd = chance + rnd;
                if (dobs.size() == 2) {
                    if (dobs.get(0).equals(tryadamage.get(p))) {
                        if (rnd > 189) {
                            if (!rdrop) {
                                drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Misc.items.get(13))));
                                rdrop=true;
                            }
                        }
                    }
                } else if (dobs.size() > 2) {
                    if (dobs.get(0).equals(tryadamage.get(p))) {
                        if (rnd > 169) {
                            if (!rdrop) {
                                drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Misc.items.get(13))));
                                rdrop=true;
                            }
                        }
                    }
                }
                if (dobs.size() > 2) {
                    if (dobs.get(0).equals(tryadamage.get(p)) || dobs.get(1).equals(tryadamage.get(p))) {
                        if (rnd > 159) {
                            if (!rdrop) {
                                rnd = rand.nextInt(3);
                                switch (rnd) {
                                    case 0:
                                        drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Armor.items.get(8))));
                                        break;
                                    case 1:
                                        drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Armor.items.get(9))));
                                        break;
                                    case 2:
                                        drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Armor.items.get(10))));
                                        break;
                                    case 3:
                                        drops.add(UtilEvents.getItemWithID(UtilEvents.getId(Armor.items.get(11))));
                                        break;
                                }
                                rdrop = true;
                            }
                        }
                    }
                }
                UtilEvents.tryadrops.put(p, drops);
            } catch (Exception ignored) {

            }
        }
    }
}
