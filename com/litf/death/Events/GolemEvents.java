package com.litf.death.Events;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class GolemEvents implements Listener {
    public static Plugin pl;
    public static EntityPlayer boss;
    public GolemEvents(Plugin mn, EntityPlayer boss){
        this.pl=mn;
        this.boss=boss;
    }
    @EventHandler
    public static void placebec(BlockPlaceEvent e){
            if(e.getBlockPlaced().getType()== Material.BEACON) {
                e.setCancelled(true);
                movegolem(e.getBlockPlaced().getLocation());
            }
            if(e.getBlockPlaced().getType()==Material.WOOD_BUTTON){
                e.setCancelled(true);
                walkgolem(e.getBlockPlaced().getLocation());
            }
    }
    public static void movegolem(Location loc){
        boss.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());
        Packet pa = new PacketPlayOutEntityTeleport(boss);
        for(Player p: Bukkit.getOnlinePlayers()){
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
        connection.sendPacket(pa);
        }
    }
    public static void walkgolem(Location loc){
        System.out.println((byte)(boss.locX-loc.getX())+" "+(byte)((boss.locX-loc.getY())-6)+" "+(byte)((boss.locX-loc.getZ())+128));
        Packet pa = new PacketPlayOutEntity.PacketPlayOutRelEntityMove(boss.getId(),(byte)(boss.locX-loc.getX()*-10), (byte)((boss.locX-loc.getY())-6*-10), (byte)((boss.locX-loc.getZ())+128*-10), true);
        //boss.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());
        for(Player p: Bukkit.getOnlinePlayers()){
            PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
            connection.sendPacket(pa);
        }
    }
}
