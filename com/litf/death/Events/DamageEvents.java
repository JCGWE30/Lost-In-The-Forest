package com.litf.death.Events;

import com.litf.death.Entits.ForestGuard;
import com.litf.death.Entits.ForestSkiter;
import com.litf.death.Entits.Tyrant.ForestTyrant;
import com.litf.death.Items.Armor;
import com.litf.death.Items.Items;
import com.litf.death.Items.Misc;
import com.litf.death.Main;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.*;

public class DamageEvents implements Listener {
    public static HashMap<Entity, Double> mobhp = new HashMap<>();
    @EventHandler
    public static void voiddamage(PlayerItemDamageEvent e){
        e.setDamage(0);
    }
    @EventHandler
    public static void hitent(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
        if (e.getDamager() instanceof Player&& !(e.getEntity() instanceof ArmorStand)) {
            double dmg = 1;
            Player p = (Player) e.getDamager();
            String id = "Noil";
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                NBTTagCompound comp = iten.getTag();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                id = lis.get(0).getString("id");
            } catch (Exception ex) {
            }
            if (id != null && id.equals("SCALE_BLADE")) {
                dmg = dmg * 1.5;
            } else if (id != null && id.equals("SCALE_DAGGER")) {
                dmg = dmg * 2;
            } else if (id != null && id.equals("SCALE_EXECUTOR")) {
                dmg = dmg * 3;
            } else if (id != null && id.equals("GUARD_SWORD")) {
                dmg = dmg * 5;
            }else if (id != null && id.equals("TYRA_DAGGER")) {
                dmg = dmg * 7.5;
            }else if(id!=null&&id.equals("ADMIN_SWORD")){
                dmg=dmg*20000;
            }
            if(armorPieceCheck(p, "GUARD")<4){
                if(armorPieceCheck(p, "GUARD")!=0) {
                    try {
                        if (p.getHealth() > mobhp.get(e.getEntity()))
                            dmg = dmg + ((dmg*1.5) * armorPieceCheck(p, "GUARD"));
                    }catch(NullPointerException ignored){}
                }
            }else if(armorPieceCheck(p, "GUARD")==4){
                dmg = dmg +((dmg*1.7)*4);
            }
            if (armorPieceCheck(p, "SCALE")==4 && e.getEntity().getType() == EntityType.SPIDER) {
                dmg = dmg * 2;
            }
                dmg = dmg +((dmg*3)*armorPieceCheck(p, "TYRA"));
            for(int y = 0;y<8;y++){
                ItemStack item = ((Player)e.getDamager()).getInventory().getItem(y);
                try {
                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound comp = iten.getTag();
                    NBTTagList lis = (NBTTagList) comp.get("comp");
                    id = lis.get(0).getString("id");
                    if(id.equals("GUARD_CUBE")){
                        if(DestinyEvents.checkCubeStat(item, "PSHIELD")){
                            dmg= dmg+(dmg*0.2d);
                        }
                    }
                }catch(NullPointerException ignored){

                }
            }
            if (e.getEntity().getType() == EntityType.SPIDER) {
                if (Main.serverdata.getConfig().get("Skiterlocs.Type") == null) {
                    Main.serverdata.getConfig().set("Skiterlocs.Type", "X");
                    Main.serverdata.getConfig().set("Skiterlocs.Limit", 126);
                    Main.serverdata.getConfig().set("Skiterlocs.LimitBack", 100);
                    Main.serverdata.saveConfig();
                }
                boolean crossed = false;
                switch (Main.serverdata.getConfig().getString("Skiterlocs.Type")) {
                    case "X":
                        if (e.getEntity().getLocation().getX() > Main.serverdata.getConfig().getInt("Skiterlocs.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getX() < Main.serverdata.getConfig().getInt("Skiterlocs.LimitBack"))
                            crossed = true;
                        break;
                    case "Y":
                        if (e.getEntity().getLocation().getY() > Main.serverdata.getConfig().getInt("Skiterlocs.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getY() < Main.serverdata.getConfig().getInt("Skiterlocs.LimitBack"))
                            crossed = true;
                        break;
                    case "Z":
                        if (e.getEntity().getLocation().getZ() > Main.serverdata.getConfig().getInt("Skiterlocs.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getZ() < Main.serverdata.getConfig().getInt("Skiterlocs.LimitBack"))
                            crossed = true;
                        break;
                }
                if (crossed) {
                    e.getEntity().remove();
                    e.setCancelled(true);
                    List<Entity> spids2 = new ArrayList<>();
                    for(ForestSkiter spid:SpawnEvents.spids){
                        spids2.add(spid.getBukkitEntity());
                    }
                    SpawnEvents.spids.remove(spids2.indexOf(e.getEntity()));
                    SpawnEvents.randomspawn(e.getEntity().getWorld());
                } else {
                    e.setDamage(0);
                    if (mobhp.get(e.getEntity()) == null) {
                        e.getEntity().remove();
                        e.setCancelled(true);
                        List<Entity> spids2 = new ArrayList<>();
                        for(ForestSkiter spid:SpawnEvents.spids){
                            spids2.add(spid.getBukkitEntity());
                        }
                        SpawnEvents.spids.remove(spids2.indexOf(e.getEntity()));
                        SpawnEvents.randomspawn(e.getEntity().getWorld());
                    } else {
                        RecipeTierEvents.Damage((int) Math.round(dmg), p, EntityType.SPIDER);
                        if (mobhp.get(e.getEntity()) - dmg <= 0) {
                            mobhp.remove(e.getEntity());
                            e.setDamage(999999999);
                        } else {
                            mobhp.put(e.getEntity(), (double) Math.round(mobhp.get(e.getEntity()) - dmg));
                            e.getEntity().setCustomName(ChatColor.GREEN + "Forest Skiter " + ChatColor.RED + mobhp.get(e.getEntity()) + "/5");
                        }
                    }
                }
            }else if(e.getEntity().getType()==EntityType.OCELOT){
                if (Main.serverdata.getConfig().get("Guardlocs.Type") == null) {
                    Main.serverdata.getConfig().set("Guardlocs.Type", "X");
                    Main.serverdata.getConfig().set("Guardlocs.Limit", 126);
                    Main.serverdata.getConfig().set("Guardlocs.LimitBack", 100);
                    Main.serverdata.saveConfig();
                }
                boolean crossed = false;
                switch (Main.serverdata.getConfig().getString("Guardlocs.Type")) {
                    case "X":
                        if (e.getEntity().getLocation().getX() > Main.serverdata.getConfig().getInt("Guardlocs.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getX() < Main.serverdata.getConfig().getInt("Guardlocs.LimitBack"))
                            crossed = true;
                        break;
                    case "Y":
                        if (e.getEntity().getLocation().getY() > Main.serverdata.getConfig().getInt("Guardlocs.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getY() < Main.serverdata.getConfig().getInt("Guardlocs.LimitBack"))
                            crossed = true;
                        break;
                    case "Z":
                        if (e.getEntity().getLocation().getZ() > Main.serverdata.getConfig().getInt("Guardlocs.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getZ() < Main.serverdata.getConfig().getInt("Guardlocs.LimitBack"))
                            crossed = true;
                        break;
                }
                if (crossed) {
                    e.getEntity().remove();
                    e.setCancelled(true);
                    List<Entity> spids2 = new ArrayList<>();
                    for(ForestGuard spid:SpawnEvents.grds){
                        spids2.add(spid.getBukkitEntity());
                    }
                    SpawnEvents.grds.remove(spids2.indexOf(e.getEntity()));
                    SpawnEvents.randomspawn(e.getEntity().getWorld());
                } else {
                    e.setDamage(0);
                    if(p.getInventory().getHelmet()!=null){
                        p.getInventory().setHelmet(DestinyEvents.calldmg(p.getInventory().getHelmet(), dmg, p));
                    }
                    if(p.getInventory().getChestplate()!=null){
                        p.getInventory().setChestplate(DestinyEvents.calldmg(p.getInventory().getChestplate(), dmg, p));
                    }
                    if(p.getInventory().getLeggings()!=null){
                        p.getInventory().setLeggings(DestinyEvents.calldmg(p.getInventory().getLeggings(), dmg, p));
                    }
                    if(p.getInventory().getBoots()!=null){
                        p.getInventory().setBoots(DestinyEvents.calldmg(p.getInventory().getBoots(), dmg, p));
                    }
                    if (mobhp.get(e.getEntity()) == null) {
                        e.getEntity().remove();
                        e.setCancelled(true);
                        SpawnEvents.randomspawn(e.getEntity().getWorld());
                    } else {
                        if (mobhp.get(e.getEntity()) - dmg <= 0) {
                            mobhp.remove(e.getEntity());
                            e.setDamage(999999999);
                        } else {
                            RecipeTierEvents.Damage((int) Math.round(dmg), p, EntityType.OCELOT);
                            mobhp.put(e.getEntity(), (double) Math.round(mobhp.get(e.getEntity()) - dmg));
                            e.getEntity().setCustomName(ChatColor.GREEN + "Forest Guard " + ChatColor.RED + mobhp.get(e.getEntity()) + "/35");
                        }
                    }
                }
            }else if(e.getEntity().getType()==EntityType.IRON_GOLEM){
                if (Main.serverdata.getConfig().get("FTyrantData.Type") == null) {
                    Main.serverdata.getConfig().set("FTyrantData.Type", "X");
                    Main.serverdata.getConfig().set("FTyrantData.Limit", 126);
                    Main.serverdata.getConfig().set("FTyrantData.LimitBack", 100);
                    Main.serverdata.saveConfig();
                }
                boolean crossed = false;
                switch (Main.serverdata.getConfig().getString("FTyrantData.Type")) {
                    case "X":
                        if (e.getEntity().getLocation().getX() > Main.serverdata.getConfig().getInt("FTyrantData.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getX() < Main.serverdata.getConfig().getInt("FTyrantData.LimitBack"))
                            crossed = true;
                        break;
                    case "Y":
                        if (e.getEntity().getLocation().getY() > Main.serverdata.getConfig().getInt("FTyrantData.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getY() < Main.serverdata.getConfig().getInt("FTyrantData.LimitBack"))
                            crossed = true;
                        break;
                    case "Z":
                        if (e.getEntity().getLocation().getZ() > Main.serverdata.getConfig().getInt("FTyrantData.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getZ() < Main.serverdata.getConfig().getInt("FTyrantData.LimitBack"))
                            crossed = true;
                        break;
                }
                if (crossed) {
                    e.getEntity().remove();
                    e.setCancelled(true);
                    List<Entity> spids2 = new ArrayList<>();
                    for(ForestTyrant spid:SpawnEvents.tynts){
                        spids2.add(spid.getBukkitEntity());
                    }
                    SpawnEvents.tynts.remove(spids2.indexOf(e.getEntity()));
                    FTyrantEvents.enableFTyrant();
                } else {
                    e.setDamage(0);
                    if(p.getInventory().getHelmet()!=null){
                        p.getInventory().setHelmet(DestinyEvents.calldmg(p.getInventory().getHelmet(), dmg, p));
                    }
                    if(p.getInventory().getChestplate()!=null){
                        p.getInventory().setChestplate(DestinyEvents.calldmg(p.getInventory().getChestplate(), dmg, p));
                    }
                    if(p.getInventory().getLeggings()!=null){
                        p.getInventory().setLeggings(DestinyEvents.calldmg(p.getInventory().getLeggings(), dmg, p));
                    }
                    if(p.getInventory().getBoots()!=null){
                        p.getInventory().setBoots(DestinyEvents.calldmg(p.getInventory().getBoots(), dmg, p));
                    }
                    if (mobhp.get(e.getEntity()) == null) {
                        e.getEntity().remove();
                        e.setCancelled(true);
                        FTyrantEvents.enableFTyrant();
                    } else {
                        if (mobhp.get(e.getEntity()) - dmg <= 0) {
                            if(UtilEvents.tryadamage.containsKey(e.getDamager())) {
                                UtilEvents.tryadamage.put((Player) e.getDamager(), UtilEvents.tryadamage.get(e.getDamager()) + dmg);
                            }else{
                                UtilEvents.tryadamage.put((Player) e.getDamager(), dmg);
                            }
                            mobhp.remove(e.getEntity());
                            e.setDamage(999999999);
                        } else {
                            RecipeTierEvents.Damage((int) Math.round(dmg), p, EntityType.IRON_GOLEM);
                            if(UtilEvents.tryadamage.containsKey(e.getDamager())) {
                                UtilEvents.tryadamage.put((Player) e.getDamager(), UtilEvents.tryadamage.get(e.getDamager()) + dmg);
                            }else{
                                UtilEvents.tryadamage.put((Player) e.getDamager(), dmg);
                            }                            mobhp.put(e.getEntity(), (double) Math.round(mobhp.get(e.getEntity()) - dmg));
                            e.getEntity().setCustomName(ChatColor.RED + "Forest Tyrant " + ChatColor.RED + mobhp.get(e.getEntity()) + "/250");
                        }
                    }
                }
            }
                Location loc = e.getEntity().getLocation();
                loc.setY(loc.getY() + 1);
                ArmorStand hit = (ArmorStand) e.getDamager().getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                hit.setCustomName(ChatColor.GRAY + "" + dmg);
                hit.setCustomNameVisible(true);
                hit.setMarker(true);
                hit.setVisible(false);
                hit.setGravity(false);
                Main.HandleIndicator(hit);
            } else if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            e.setDamage(0);
            double dmg = 0;
            if (e.getDamager().getType() == EntityType.SPIDER) {
                dmg = 3.5;
            }
            if(e.getDamager().getType() == EntityType.OCELOT){
                dmg = 10;
            }
            if(e.getDamager().getType() == EntityType.IRON_GOLEM){
                dmg = 30;
            }
            if (p.getInventory().getHelmet() != null) {
                List<ItemStack> armoritems = Arrays.asList(
                        p.getInventory().getHelmet(),
                        p.getInventory().getChestplate(),
                        p.getInventory().getLeggings(),
                        p.getInventory().getBoots()
                );
                double sub = dmg*(armorPieceCheck(p, "SCALE")*0.05);
                dmg = dmg - sub;
                if(checkset(p).equals("SCALE")){
                    sub = dmg * 0.10;
                    dmg = dmg - sub;
                }
                if(armorPieceCheck(p, "TYRA")*0.3>0){
                    sub = dmg*(armorPieceCheck(p, "TYRA")*0.3);
                    dmg = dmg - sub;
                }
                if(armorPieceCheck(p, "GUARD")*0.1>0){
                    sub = dmg*(armorPieceCheck(p, "GUARD")*0.1);
                    dmg = dmg - sub;
                }
                for(int y = 0;y<8;y++){
                    ItemStack item = ((Player)e.getEntity()).getInventory().getItem(y);
                    try {
                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                        NBTTagCompound comp = iten.getTag();
                        NBTTagList lis = (NBTTagList) comp.get("comp");
                        String id = lis.get(0).getString("id");
                        if(id.equals("GUARD_CUBE")){
                            if(DestinyEvents.checkCubeStat(item, "PSHIELD")){
                                sub = dmg*(Math.floor(mobhp.get(e.getDamager())/20d)*0.05d);
                                dmg= dmg-sub;
                            }
                        }
                    }catch(NullPointerException ignored){

                    }
                }
                for (ItemStack item:armoritems){
                    try {
                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                        NBTTagCompound comp = iten.getTag();
                        NBTTagList lis = (NBTTagList) comp.get("comp");
                        String id = lis.get(0).getString("id");
                        dmg=callarmordestiny(p, item,dmg);
                    } catch (NullPointerException ignored) {}
                }
            }
            if (Main.serverdata.getConfig().get("Skiterlocs.Type") == null) {
                Main.serverdata.getConfig().set("Skiterlocs.Type", "X");
                Main.serverdata.getConfig().set("Skiterlocs.Limit", 126);
                Main.serverdata.getConfig().set("Skiterlocs.LimitBack", 100);
                Main.serverdata.saveConfig();
            }
            boolean crossed = false;
            if(e.getDamager().getType()==EntityType.SPIDER) {
                switch (Main.serverdata.getConfig().getString("Skiterlocs.Type")) {
                    case "X":
                        if (e.getDamager().getLocation().getX() > Main.serverdata.getConfig().getInt("Skiterlocs.Limit"))
                            crossed = true;
                        if (e.getDamager().getLocation().getX() < Main.serverdata.getConfig().getInt("Skiterlocs.LimitBack"))
                            crossed = true;
                        break;
                    case "Y":
                        if (e.getDamager().getLocation().getY() > Main.serverdata.getConfig().getInt("Skiterlocs.Limit"))
                            crossed = true;
                        if (e.getDamager().getLocation().getY() < Main.serverdata.getConfig().getInt("Skiterlocs.LimitBack"))
                            crossed = true;
                        break;
                    case "Z":
                        if (e.getDamager().getLocation().getZ() > Main.serverdata.getConfig().getInt("Skiterlocs.Limit"))
                            crossed = true;
                        if (e.getDamager().getLocation().getZ() < Main.serverdata.getConfig().getInt("Skiterlocs.LimitBack"))
                            crossed = true;
                        break;
                }
            }else if(e.getEntityType()==EntityType.OCELOT){
                switch (Main.serverdata.getConfig().getString("Guardlocs.Type")) {
                    case "X":
                        if (e.getEntity().getLocation().getX() > Main.serverdata.getConfig().getInt("Guardlocs.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getX() < Main.serverdata.getConfig().getInt("Guardlocs.LimitBack"))
                            crossed = true;
                        break;
                    case "Y":
                        if (e.getEntity().getLocation().getY() > Main.serverdata.getConfig().getInt("Guardlocs.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getY() < Main.serverdata.getConfig().getInt("Guardlocs.LimitBack"))
                            crossed = true;
                        break;
                    case "Z":
                        if (e.getEntity().getLocation().getZ() > Main.serverdata.getConfig().getInt("Guardlocs.Limit"))
                            crossed = true;
                        if (e.getEntity().getLocation().getZ() < Main.serverdata.getConfig().getInt("Guardlocs.LimitBack"))
                            crossed = true;
                        break;
                }
            }
            if (crossed) {
                e.setCancelled(true);
                List<Entity> spids2 = new ArrayList<>();
                if(e.getEntityType()==EntityType.SPIDER){
                    for(ForestSkiter spid:SpawnEvents.spids){
                        spids2.add(spid.getBukkitEntity());
                    }
                    SpawnEvents.spids.remove(spids2.indexOf(e.getDamager()));
                }else if(e.getEntityType()==EntityType.OCELOT){
                    for(ForestGuard spid:SpawnEvents.grds){
                        spids2.add(spid.getBukkitEntity());
                    }
                    SpawnEvents.grds.remove(spids2.indexOf(e.getDamager()));
                }
                e.getDamager().remove();
                SpawnEvents.randomspawn(e.getEntity().getWorld());
            } else {
                if (p.getHealth() > dmg) {
                    p.setHealth(p.getHealth() - dmg);
                } else if (dmg >= p.getHealth()) {
                    if (Main.serverdata.getConfig().get("SpawnLocation.X") == null) {
                        Main.serverdata.getConfig().set("SpawnLocation.X", 10);
                        Main.serverdata.getConfig().set("SpawnLocation.Y", 10);
                        Main.serverdata.getConfig().set("SpawnLocation.Z", 10);
                        Main.serverdata.saveConfig();
                    }
                    Location loc = p.getWorld().getBlockAt(Main.serverdata.getConfig().getInt("SpawnLocation.X", 10), Main.serverdata.getConfig().getInt("SpawnLocation.Y", 10), Main.serverdata.getConfig().getInt("SpawnLocation.Z", 10)).getLocation();
                    if (e.getDamager() == null) {
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED + "You Were Killed, mysteriously");
                        p.teleport(loc);
                        p.playSound(p.getLocation(), Sound.ENDERMAN_DEATH, 1f, 1f);
                        p.setHealth(p.getMaxHealth());
                    } else {
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED + "You Were Killed By " + e.getDamager().getCustomName());
                        p.teleport(loc);
                        p.playSound(p.getLocation(), Sound.ENDERMAN_DEATH, 1f, 1f);
                        p.setHealth(p.getMaxHealth());
                        if(e.getDamager().getType()==EntityType.SPIDER) {
                            List<Entity> spids2 = new ArrayList<>();
                            for (ForestSkiter spid : SpawnEvents.spids) {
                                spids2.add(spid.getBukkitEntity());
                            }
                            SpawnEvents.spids.remove(spids2.indexOf(e.getDamager()));
                            SpawnEvents.randomspawn(e.getDamager().getWorld());
                            e.getDamager().remove();
                        }
                        if(e.getDamager().getType()==EntityType.OCELOT) {
                            List<Entity> spids2 = new ArrayList<>();
                            for (ForestGuard spid : SpawnEvents.grds) {
                                spids2.add(spid.getBukkitEntity());
                            }
                            SpawnEvents.grds.remove(spids2.indexOf(e.getDamager()));
                            SpawnEvents.randomspawn(e.getDamager().getWorld());
                            e.getDamager().remove();
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public static void deaththing(EntityDeathEvent e){
        if(e.getEntity().getKiller() != null&& e.getEntity().getKiller() != null){
                e.getDrops().clear();
                e.setDroppedExp(0);
                Random rand = new Random();
                int rnd = rand.nextInt(100);
                String id = "Noil";
                boolean ext = false;
                try {
                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(e.getEntity().getKiller().getItemInHand().clone());
                    NBTTagCompound comp = iten.getTag();
                    NBTTagList lis = (NBTTagList) comp.get("comp");
                    id = lis.get(0).getString("id");
                }catch(Exception ignored){
                }
            int chance=0;
                int multis = 1;
            if(e.getEntity().getType()==EntityType.SPIDER) {
                if (Main.data.getConfig().getIntegerList(e.getEntity().getKiller().getUniqueId().toString()+".Kills").size()<3) {
                    Main.data.getConfig().set(e.getEntity().getKiller().getUniqueId().toString()+".Kills", Arrays.asList(0,0,0));
                    Main.data.saveConfig();
                }
                List<Integer> kls = Main.data.getConfig().getIntegerList(e.getEntity().getKiller().getUniqueId().toString()+".Kills");
                kls.set(0, kls.get(0)+1);
                Main.data.getConfig().set(e.getEntity().getKiller().getUniqueId().toString()+".Kills", kls);
                Main.data.saveConfig();
                chance = 25;
            }else if(e.getEntity().getType()==EntityType.OCELOT){
                if (Main.data.getConfig().getIntegerList(e.getEntity().getKiller().getUniqueId().toString()+".Kills").size()<3) {
                    Main.data.getConfig().set(e.getEntity().getKiller().getUniqueId().toString()+".Kills", Arrays.asList(0,0,0));
                    Main.data.saveConfig();
                }
                List<Integer> kls = Main.data.getConfig().getIntegerList(e.getEntity().getKiller().getUniqueId().toString()+".Kills");
                kls.set(1, kls.get(1)+1);
                Main.data.getConfig().set(e.getEntity().getKiller().getUniqueId().toString()+".Kills", kls);
                Main.data.saveConfig();
                chance=15;
            }else if(e.getEntity().getType()==EntityType.IRON_GOLEM){
                if (Main.data.getConfig().getIntegerList(e.getEntity().getKiller().getUniqueId().toString()+".Kills").size()<3) {
                    Main.data.getConfig().set(e.getEntity().getKiller().getUniqueId().toString()+".Kills", Arrays.asList(0,0,0));
                    Main.data.saveConfig();
                }
                List<Integer> kls = Main.data.getConfig().getIntegerList(e.getEntity().getKiller().getUniqueId().toString()+".Kills");
                kls.set(2, kls.get(2)+1);
                Main.data.getConfig().set(e.getEntity().getKiller().getUniqueId().toString()+".Kills", kls);
                Main.data.saveConfig();
                chance=5;
            }
            switch (id) {
                case "SCALE_BLADE":
                    chance = chance + (int) Math.round((chance * 0.2));
                    break;
                case "SCALE_DAGGER":
                    multis = 2;
                    chance = chance + (int) Math.round((chance * 0.4));
                    break;
                case "SCALE_EXECUTOR":
                    multis = 2;
                    chance = chance + (int) Math.round((chance * 0.55));
                    ext = true;
                    break;
            }
            for(int y = 0;y<8;y++){
                ItemStack item = e.getEntity().getKiller().getInventory().getItem(y);
                try {
                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound comp = iten.getTag();
                    NBTTagList lis = (NBTTagList) comp.get("comp");
                    id = lis.get(0).getString("id");
                    if(id.equals("GUARD_CUBE")){
                        if(DestinyEvents.checkCubeStat(item, "ROLLER")){
                            chance =chance*3;
                        }
                    }
                }catch(NullPointerException ignored){

                }
            }
            String referitem = " ";
                if(e.getEntity().getKiller().getInventory().getItemInHand().getType()!= Material.AIR) {
                    referitem = DestinyEvents.getDestiny(e.getEntity().getKiller().getInventory().getItemInHand().clone());
                }
            assert referitem != null;
            if(referitem.contains("SKITING")){
                Integer numbor = Integer.parseInt(referitem.split("\\.")[1]);
                    if(numbor==1){
                        chance=chance+(int)Math.round((chance*0.02));
                    }else if(numbor==2){
                        chance=chance+(int)Math.round((chance*0.04));
                    }else if(numbor==3){
                        chance=chance+(int)Math.round((chance*0.06));
                    }else if(numbor==4){
                        chance=chance+(int)Math.round((chance*0.08));
                    }else if(numbor==5){
                        chance=chance+(int)Math.round((chance*0.1));
                    }else if(numbor==10){
                        chance=chance+(int)Math.round((chance*0.2));
                    }
                }
            if(e.getEntity().getType()==EntityType.SPIDER){
                Scoreboardinit.addcoins(e.getEntity().getKiller(), 1);
                List<Entity> spids2 = new ArrayList<>();
                for(ForestSkiter spid:SpawnEvents.spids){
                    spids2.add(spid.getBukkitEntity());
                }
                SpawnEvents.spids.remove(spids2.indexOf(e.getEntity()));
                SpawnEvents.randomspawn(e.getEntity().getWorld());
                System.out.println(rnd+" "+chance);
                if(rnd<chance) {
                        if (referitem.contains("5") || referitem.contains("10")) {
                            if (referitem.contains("SKITING")) {
                                boolean alreadyhit = false;
                                for (int i = 0; i < 8; i++) {
                                    if (DestinyEvents.checkCubeStat(e.getEntity().getKiller().getInventory().getItem(i), "LINK")) {
                                        alreadyhit = true;
                                    }
                                }
                                if (alreadyhit) {
                                    ItemStack item = (Misc.items.get(2).clone());
                                    item.setAmount(multis);
                                    e.getEntity().getKiller().getInventory().addItem(item);
                                    RecipeTierEvents.drop("SCALE_REFINED", e.getEntity().getKiller());
                                } else {
                                    ItemStack item = (Misc.items.get(2).clone());
                                    item.setAmount(multis);
                                    e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
                                    RecipeTierEvents.drop("SCALE_REFINED", e.getEntity().getKiller());
                                }
                            }
                        } else {
                            boolean alreadyhit = false;
                            for (int i = 0; i < 8; i++) {
                                if (DestinyEvents.checkCubeStat(e.getEntity().getKiller().getInventory().getItem(i), "LINK")) {
                                    alreadyhit = true;
                                }
                            }
                            if (alreadyhit) {
                                ItemStack item = (Misc.items.get(1).clone());
                                item.setAmount(multis);
                                e.getEntity().getKiller().getInventory().addItem(item);
                                RecipeTierEvents.drop("SCALE_BASE", e.getEntity().getKiller());
                            } else {
                                ItemStack item = (Misc.items.get(1).clone());
                                item.setAmount(multis);
                                e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
                                RecipeTierEvents.drop("SCALE_BASE", e.getEntity().getKiller());
                            }
                        }
                }
                if(ext){
                    rnd = rand.nextInt(101);
                    chance=chance/3;
                    int rnds = rnd;
                    if(chance>14){
                        rnds=rnd+15;
                    }else{
                        rnds=rnd+chance;
                    }
                    if(rnds>=114){
                        Item i = e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), UtilEvents.getItemWithID(UtilEvents.getId(Misc.items.get(10))));
                        e.getEntity().getKiller().sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lCRAZY DROP &r&7wow you just got a "+i.getItemStack().getItemMeta().getDisplayName()));
                        e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.ENDERDRAGON_GROWL, 1f,1f);
                    }
                    if(rnd<chance){
                        boolean alreadyhit = false;
                        for(int i=0;i<8;i++){
                            if(DestinyEvents.checkCubeStat(e.getEntity().getKiller().getInventory().getItem(i), "LINK")){
                                alreadyhit=true;
                            }
                        }
                        if(alreadyhit) {
                            e.getEntity().getKiller().getInventory().addItem(Misc.items.get(4).clone());
                            e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.NOTE_PLING, 1f, 1f);
                            e.getEntity().getKiller().sendMessage(ChatColor.DARK_PURPLE + "Skiter Matter" + ChatColor.GRAY + " Has dropped!");
                        }else{
                            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), Misc.items.get(4).clone());
                            e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.NOTE_PLING, 1f, 1f);
                            e.getEntity().getKiller().sendMessage(ChatColor.DARK_PURPLE + "Skiter Matter" + ChatColor.GRAY + " Has dropped!");
                        }
                    }
                }
        }else if(e.getEntity().getType()==EntityType.OCELOT){
                Scoreboardinit.addcoins(e.getEntity().getKiller(), 5);
                List<Entity> spids2 = new ArrayList<>();
                for(ForestGuard spid:SpawnEvents.grds){
                    spids2.add(spid.getBukkitEntity());
                }
                SpawnEvents.grds.remove(spids2.indexOf(e.getEntity()));
                SpawnEvents.randomspawn(e.getEntity().getWorld());
                if(rnd<chance){
                    e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), Misc.items.get(7).clone());
                    RecipeTierEvents.drop("GUARD_TAIL", e.getEntity().getKiller());
                }
            }else if(e.getEntity().getType()==EntityType.IRON_GOLEM) {
                FTyrantEvents.TyrantDeath(chance,UtilEvents.tryadamage);
            }
    }
}
public static String checkset(Player p){
    List<ItemStack> armoritems = Arrays.asList(
            p.getInventory().getHelmet(),
            p.getInventory().getChestplate(),
            p.getInventory().getLeggings(),
            p.getInventory().getBoots()
    );
    boolean scale = true;
    boolean guard = true;
    for (ItemStack item:armoritems){
        try {
            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
            NBTTagCompound comp = iten.getTag();
            NBTTagList lis = (NBTTagList) comp.get("comp");
            String id = lis.get(0).getString("id");
            if(!id.contains("SCALE")){
                scale=false;
            }
            if(!id.contains("GUARD")){
                guard=false;
            }
        } catch (NullPointerException ignored) {}
    }
    if(scale){
        return("SCALE");
    }
    if(guard){
        return("GUARD");
    }
    return "Noil";
}
public static Double callarmordestiny(Player p,ItemStack item, Double dmg){
    if(DestinyEvents.getDestiny(item).contains("TAILION")){
        String dest = DestinyEvents.getDestiny(item);
        Integer numbor = Integer.parseInt(dest.split("\\.")[1]);
        if(numbor==1){
            if(p.getMaxHealth()==p.getHealth()){

            }else if(Math.floor(p.getMaxHealth()-p.getHealth())<5){
                double calc = (Math.floor(p.getMaxHealth()-p.getHealth())*0.0005);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }else{
                double calc = (5*0.0005);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }
        }
        if(numbor==2){
            if(p.getMaxHealth()==p.getHealth()){

            }else if(Math.floor(p.getMaxHealth()-p.getHealth())<5){
                double calc = (Math.floor(p.getMaxHealth()-p.getHealth())*0.0015);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }else{
                double calc = (5*0.0015);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }
        }
        if(numbor==3){
            if(p.getMaxHealth()==p.getHealth()){

            }else if(Math.floor(p.getMaxHealth()-p.getHealth())<5){
                double calc = (Math.floor(p.getMaxHealth()-p.getHealth())*0.002);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }else{
                double calc = (5*0.002);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }
        }
        if(numbor==4){
            if(p.getMaxHealth()==p.getHealth()){

            }else if(Math.floor(p.getMaxHealth()-p.getHealth())<5){
                double calc = (Math.floor(p.getMaxHealth()-p.getHealth())*0.0025);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }else{
                double calc = (5*0.0025);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }
        }
        if(numbor==5){
            if(p.getMaxHealth()==p.getHealth()){

            }else if(Math.floor(p.getMaxHealth()-p.getHealth())<5){
                double calc = (Math.floor(p.getMaxHealth()-p.getHealth())*0.0035);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }else{
                double calc = (5*0.0035);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }
        }
        if(numbor==10){
            if(p.getMaxHealth()==p.getHealth()){

            }else if(Math.floor(p.getMaxHealth()-p.getHealth())<5){
                double calc = (Math.floor(p.getMaxHealth()-p.getHealth())*0.0070);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }else{
                double calc = (5*0.0070);
                double calcf = dmg*calc;
                dmg = dmg-calcf;
            }
        }
    }
    return dmg;
}
public static Integer armorPieceCheck(Player p, String set){
    List<ItemStack> armoritems = Arrays.asList(
            p.getInventory().getHelmet(),
            p.getInventory().getChestplate(),
            p.getInventory().getLeggings(),
            p.getInventory().getBoots()
    );
    int i = 0;
    for (ItemStack item:armoritems){
        try {
            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
            NBTTagCompound comp = iten.getTag();
            NBTTagList lis = (NBTTagList) comp.get("comp");
            String id = lis.get(0).getString("id");
            if(id.contains(set)){
                i++;
            }
        } catch (NullPointerException ignored) {}
    }
    return i;
}
}