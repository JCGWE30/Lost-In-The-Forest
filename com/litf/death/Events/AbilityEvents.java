package com.litf.death.Events;

import com.litf.death.Entits.Tyrant.ForestTyrant;
import com.litf.death.Items.Items;
import com.litf.death.Main;
import net.minecraft.server.v1_8_R3.DamageSource;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.EulerAngle;

import java.util.*;

public class AbilityEvents implements Listener {
    static Plugin plg;
    public AbilityEvents(Plugin plog){
        this.plg=plog;
    }
    public static void HandleAbilityBolt(ArmorStand e){
        Main.plg.getServer().getScheduler().scheduleSyncDelayedTask(plg, new Runnable() {
            @Override
            public void run() {
                if (e.getEyeLocation().getBlock().getType() != Material.AIR) {
                    e.remove();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.playEffect(e.getEyeLocation().add(0, 1, 0), Effect.EXPLOSION_LARGE, 20);
                        p.playSound(e.getEyeLocation(), Sound.EXPLODE, 2f, 2f);
                    }
                    List<Entity> hits = new ArrayList<>();
                    if (AbilityEvents.shooter.containsKey(e)) {
                        for (org.bukkit.entity.Entity ee : DamageEvents.mobhp.keySet()) {
                            if (e.getLocation().distance(ee.getLocation()) < 3) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    p.playEffect(e.getEyeLocation().add(0, 1, 0), Effect.EXPLOSION_LARGE, 20);
                                }
                                hits.add(ee);
                            }
                        }
                        for(org.bukkit.entity.Entity ee:hits){
                            ((CraftEntity) ee).getHandle().damageEntity(DamageSource.playerAttack(((CraftPlayer) AbilityEvents.shooter.get(e)).getHandle()), 0);
                        }
                        AbilityEvents.shooter.remove(e);
                    }
                } else {
                    e.setVelocity(e.getLocation().getDirection().multiply(0.5));
                    e.teleport(e.getLocation().add(e.getLocation().getDirection().multiply(0.6)));
                    HandleAbilityBolt(e);
                    boolean hitmob = false;
                    List<org.bukkit.entity.Entity> hits = new ArrayList<>();
                    if (AbilityEvents.shooter.containsKey(e)) {
                        for (org.bukkit.entity.Entity ee : DamageEvents.mobhp.keySet()) {
                            if (e.getLocation().distance(ee.getLocation()) < 1.2) {
                                e.remove();
                                hitmob=true;
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    p.playEffect(e.getEyeLocation().add(0, 1, 0), Effect.EXPLOSION_LARGE, 20);
                                    p.playSound(e.getEyeLocation(), Sound.EXPLODE, 2f, 2f);
                                }
                            }
                        }
                        if(hitmob){
                            for (org.bukkit.entity.Entity ee : DamageEvents.mobhp.keySet()) {
                                if (e.getLocation().distance(ee.getLocation()) < 3)
                                    hits.add(ee);
                            }
                            for(Entity ee:hits){
                                ((CraftEntity) ee).getHandle().damageEntity(DamageSource.playerAttack(((CraftPlayer) AbilityEvents.shooter.get(e)).getHandle()), 0);
                            }
                            AbilityEvents.shooter.remove(e);
                        }
                    }
                }
            }
        }, 1);
    }
    public static void HandleAbilityDest(ArmorStand e){
        Main.plg.getServer().getScheduler().scheduleSyncDelayedTask(plg, new Runnable() {
            @Override
            public void run() {
                e.remove();
            }
        }, 50);
    }
    public static HashMap<ArmorStand, Player> shooter = new HashMap<>();
    public static HashMap<Player, Long> cooldown = new HashMap<>();
    @EventHandler
    public static void clickevent(PlayerInteractEvent e){
        Date date = new Date();
        if(e.getAction()== Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK){
            if(e.getPlayer().getInventory().getItemInHand()!=null) {
                if(!cooldown.containsKey(e.getPlayer())){
                    cooldown.put(e.getPlayer(), 0l);
                }
                if (date.getTime() > cooldown.get(e.getPlayer()) + 300) {
                    try {
                        if (UtilEvents.getId(e.getPlayer().getInventory().getItemInHand()).equals("TYRA_DAGGER")) {
                            cooldown.put(e.getPlayer(), date.getTime());
                            ArmorStand bob = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), ArmorStand.class);
                            bob.setHeadPose(new EulerAngle(Math.toRadians(e.getPlayer().getEyeLocation().getPitch()), Math.toRadians(0), Math.toRadians(0)));
                            bob.setVisible(false);
                            bob.setGravity(false);
                            bob.setHelmet(Items.getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjBjZDEzMjIzYThkOWMxNzNjZWRjZTZjNGJlYmViYTA2YTI0YTFiYTI3NWRkM2ViNWM3OTMzZjlhNzRiYTAxMSJ9fX0="));
                            bob.setVelocity(e.getPlayer().getLocation().getDirection().multiply(0.75));
                            shooter.put(bob, e.getPlayer());
                            HandleAbilityBolt(bob);
                            HandleAbilityDest(bob);
                        }
                    }catch(NullPointerException ignored){

                    }
                }
            }
        }
    }
    @EventHandler
    public static void clickeventenvnt(PlayerInteractAtEntityEvent e){
        Date date = new Date();
            if(e.getPlayer().getInventory().getItemInHand()!=null) {
                if(!cooldown.containsKey(e.getPlayer())){
                    cooldown.put(e.getPlayer(), 0l);
                }
                if (date.getTime() > cooldown.get(e.getPlayer()) + 300) {
                    try{
                    if (UtilEvents.getId(e.getPlayer().getInventory().getItemInHand()).equals("TYRA_DAGGER")) {
                        cooldown.put(e.getPlayer(), date.getTime());
                        ArmorStand bob = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), ArmorStand.class);
                        bob.setHeadPose(new EulerAngle(Math.toRadians(e.getPlayer().getEyeLocation().getPitch()), Math.toRadians(0), Math.toRadians(0)));
                        bob.setVisible(false);
                        bob.setGravity(false);
                        bob.setHelmet(Items.getcustomhead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjBjZDEzMjIzYThkOWMxNzNjZWRjZTZjNGJlYmViYTA2YTI0YTFiYTI3NWRkM2ViNWM3OTMzZjlhNzRiYTAxMSJ9fX0="));
                        bob.setVelocity(e.getPlayer().getLocation().getDirection().multiply(0.75));
                        shooter.put(bob, e.getPlayer());
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.IRONGOLEM_HIT, 1f, 1f);
                        HandleAbilityBolt(bob);
                        HandleAbilityDest(bob);
                    }
                    }catch(NullPointerException ignored){

                    }
                }
            }
    }
}
