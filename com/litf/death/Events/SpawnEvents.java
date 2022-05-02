package com.litf.death.Events;

import com.litf.death.Entits.ForestGuard;
import com.litf.death.Entits.ForestSkiter;
import com.litf.death.Entits.Tyrant.ForestTyrant;
import com.litf.death.Main;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.*;

public class SpawnEvents implements Listener {
    public static List<ForestSkiter> spids = new ArrayList<>();
    public static List<ForestGuard> grds = new ArrayList<>();
    public static List<ForestTyrant> tynts = new ArrayList<>();
    public static Boolean go = false;

    @EventHandler
    public static void spawnin(BlockPlaceEvent e) {
        String id = null;
        try {
            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(e.getPlayer().getItemInHand().clone());
            NBTTagCompound comp = iten.getTag();
            NBTTagList lis = (NBTTagList) comp.get("comp");
            id = lis.get(0).getString("id");
        } catch (Exception ex) {
        }
        if (id != null && id.equals("SCALE_SPAWN")) {
            e.setCancelled(true);
            ForestSkiter ent = new ForestSkiter(e.getBlockPlaced().getWorld());
            DamageEvents.mobhp.put(ent.getBukkitEntity(), 5d);
            ForestSkiter.EntityTypes.spawnEntity(ent, e.getBlockPlaced().getLocation());
        }else if(id != null && id.equals("GUARD_SPAWN")){
            e.setCancelled(true);
            ForestGuard ent = new ForestGuard(e.getBlockPlaced().getWorld());
            DamageEvents.mobhp.put(ent.getBukkitEntity(), 35d);
            ForestGuard.EntityTypes.spawnEntity(ent, e.getBlockPlaced().getLocation());
        }
    }

    public static void randomspawn(World world) {
        if (Main.serverdata.getConfig().get("Skiterlocs.1.X") == null) {
            Main.serverdata.getConfig().set("Skiterlocs.Count", 2);
            Main.serverdata.getConfig().set("Skiterlocs.Max", 3);
            Main.serverdata.getConfig().set("Skiterlocs." + 1 + ".X", 10);
            Main.serverdata.getConfig().set("Skiterlocs." + 1 + ".Y", 10);
            Main.serverdata.getConfig().set("Skiterlocs." + 1 + ".Z", 10);
            Main.serverdata.getConfig().set("Skiterlocs." + 2 + ".X", 15);
            Main.serverdata.getConfig().set("Skiterlocs." + 2 + ".Y", 10);
            Main.serverdata.getConfig().set("Skiterlocs." + 2 + ".Z", 15);
            Main.serverdata.saveConfig();
        }
        if (Main.serverdata.getConfig().get("Guardlocs.1.X") == null) {
            Main.serverdata.getConfig().set("Guardlocs.Count", 2);
            Main.serverdata.getConfig().set("Guardlocs.Max", 3);
            Main.serverdata.getConfig().set("Guardlocs." + 1 + ".X", 10);
            Main.serverdata.getConfig().set("Guardlocs." + 1 + ".Y", 10);
            Main.serverdata.getConfig().set("Guardlocs." + 1 + ".Z", 10);
            Main.serverdata.getConfig().set("Guardlocs." + 2 + ".X", 15);
            Main.serverdata.getConfig().set("Guardlocs." + 2 + ".Y", 10);
            Main.serverdata.getConfig().set("Guardlocs." + 2 + ".Z", 15);
            Main.serverdata.saveConfig();
        }
        List<Location> locs = new ArrayList<>();
        for (int i = 1; i < Main.serverdata.getConfig().getInt("Skiterlocs.Count") + 1; i++) {
            locs.add(world.getBlockAt(Main.serverdata.getConfig().getInt("Skiterlocs." + i + ".X"), Main.serverdata.getConfig().getInt("Skiterlocs." + i + ".Y"), Main.serverdata.getConfig().getInt("Skiterlocs." + i + ".Z")).getLocation());
        }
        Random rand = new Random();
        for(int i=0; i<Main.serverdata.getConfig().getInt("Skiterlocs.Max"); i++) {
            if (go && spids.size() < Main.serverdata.getConfig().getInt("Skiterlocs.Max")) {
                ForestSkiter ent = new ForestSkiter(world);
                DamageEvents.mobhp.put(ent.getBukkitEntity(), 5d);
                spids.add(ent);
                ForestSkiter.EntityTypes.spawnEntity(ent, locs.get(rand.nextInt(locs.size())));
            }
        }
        locs.clear();
        for (int i = 1; i < Main.serverdata.getConfig().getInt("Guardlocs.Count") + 1; i++) {
            locs.add(world.getBlockAt(Main.serverdata.getConfig().getInt("Guardlocs." + i + ".X"), Main.serverdata.getConfig().getInt("Guardlocs." + i + ".Y"), Main.serverdata.getConfig().getInt("Guardlocs." + i + ".Z")).getLocation());
        }
        for(int i=0; i<Main.serverdata.getConfig().getInt("Guardlocs.Max"); i++) {
            if (go && grds.size() < Main.serverdata.getConfig().getInt("Guardlocs.Max")) {
                ForestGuard ent = new ForestGuard(world);
                DamageEvents.mobhp.put(ent.getBukkitEntity(), 35d);
                grds.add(ent);
                ForestGuard.EntityTypes.spawnEntity(ent, locs.get(rand.nextInt(locs.size())));
            }
        }
    }
}