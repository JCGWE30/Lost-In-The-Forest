package com.litf.death.Packeets;

import com.litf.death.Entits.NPCS.NPC;
import com.litf.death.Entits.NPCS.RightClickNPC;
import com.litf.death.Events.UndegroundMineEvents;
import com.litf.death.Main;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PacketReader {
    Channel channel;
    public static Map<UUID, Channel> channels = new HashMap<UUID, Channel>();

    public void inject(Player player){
        CraftPlayer craftPlayer = (CraftPlayer) player;
        channel = craftPlayer.getHandle().playerConnection.networkManager.channel;
        channels.put(player.getUniqueId(), channel);

        if(channel.pipeline().get("PacketInjector")!=null)
            return;

        channel.pipeline().addAfter("decoder","PacketInjector", new MessageToMessageDecoder<PacketPlayInUseEntity>(){

            @Override
            protected void decode(ChannelHandlerContext channel, PacketPlayInUseEntity packet, List<Object> arg) throws Exception {
                arg.add(packet);
                readPacket(player, packet);
            }
        });

        if(channel.pipeline().get("PacketSInjector")!=null)
            return;

        channel.pipeline().addAfter("decoder","PacketSInjector", new MessageToMessageDecoder<PacketPlayInUpdateSign>(){

            @Override
            protected void decode(ChannelHandlerContext channel, PacketPlayInUpdateSign packet, List<Object> arg) throws Exception {
                arg.add(packet);
                readSPacket(player, packet);
            }
        });

        if(channel.pipeline().get("PacketMInjector")!=null)
            return;

        channel.pipeline().addAfter("decoder","PacketMInjector", new MessageToMessageDecoder<PacketPlayInBlockDig>(){
            @Override
            protected void decode(ChannelHandlerContext channel, PacketPlayInBlockDig packet, List<Object> arg) throws Exception {
                arg.add(packet);
                readMPacket(player, packet);
            }
        });
    }

    public void uninject(Player player){
        channel = channels.get(player.getUniqueId());
        if(channel.pipeline().get("PacketInjector")!=null)
            channel.pipeline().remove("PacketInjector");
        if(channel.pipeline().get("PacketSInjector")!=null)
            channel.pipeline().remove("PacketSInjector");
        if(channel.pipeline().get("PacketMInjector")!=null)
            channel.pipeline().remove("PacketMInjector");
    }

    public void readPacket(Player player, Packet<?> packet){
        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")){
            if(getValue(packet, "action").toString().equalsIgnoreCase("ATTACK"))
                return;
            if(getValue(packet, "action").toString().equalsIgnoreCase("INTERACT_AT"))
                return;
            int id = (int) getValue(packet, "a");

            if(getValue(packet, "action").toString().equalsIgnoreCase("INTERACT")){
                for(EntityPlayer npc: NPC.getNPCs()){
                    if(npc.getId()==id){
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
                            @Override
                            public void run() {
                                Bukkit.getPluginManager().callEvent(new RightClickNPC(player, npc));
                            }
                        }, 0);
                    }
                }
            }
        }
    }
    public void readSPacket(Player player, Packet<?> packet){
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
            @Override
            public void run() {
                PacketPlayInUpdateSign packetPlayInUpdateSign = (PacketPlayInUpdateSign) packet;
                Bukkit.getPluginManager().callEvent(new SignEvent(player, packetPlayInUpdateSign.a(), packetPlayInUpdateSign.b()));
            }
        }, 0);
    }
    public void readMPacket(Player player, Packet<?> packet){
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
            @Override
            public void run() {
                PacketPlayInBlockDig packetPlayInUpdateSign = (PacketPlayInBlockDig) packet;
                UndegroundMineEvents.stopmine(player,packetPlayInUpdateSign.a(),packetPlayInUpdateSign.c());
            }
        }, 0);
    }
    private Object getValue(Object instance, String name) {

        try {

            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(instance);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }
}
