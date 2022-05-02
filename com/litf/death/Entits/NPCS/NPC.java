package com.litf.death.Entits.NPCS;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.litf.death.Items.Misc;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NPC {

    private static List<EntityPlayer> NPC = new ArrayList<EntityPlayer>();
    public static List<Integer> NPCids = new ArrayList<Integer>();

    public static EntityPlayer createNPC(Location loc, String name, List<String> texts) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) loc.getWorld()).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
        EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
        npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        if (!texts.isEmpty()){
            gameProfile.getProperties().put("textures", new Property("textures", texts.get(0), texts.get(1)));
        }
        addNPCPacket(npc);
        NPC.add(npc);
        NPCids.add(npc.getId());
        return npc;
    }
    public static String[] getSkin(String name){
        try{
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();
            URL url2 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader reader2 = new InputStreamReader(url2.openStream());
            JsonObject property = new JsonParser().parse(reader2).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String texture = property.get("value").getAsString();
            String signature = property.get("signature").getAsString();
            return new String[] {texture, signature};

        }catch(Exception e){
            return null;
        }
    }
    public static void addNPCPacket(EntityPlayer npc){
        for(Player player:Bukkit.getOnlinePlayers()){
            PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte)(npc.yaw*256/360)));
            DataWatcher watcher = npc.getDataWatcher();
            byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
            watcher.watch(10, b);
            connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), watcher, true));
        }
    }
    public static void makeNPClookAtPlayer(EntityPlayer npc, Player p){
        Location npcloc = npc.getBukkitEntity().getLocation();
        npcloc = npcloc.setDirection(p.getLocation().subtract(npcloc).toVector());
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
        float yaw = npcloc.getYaw();
        float pitch = npcloc.getPitch();
        connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getId(), (byte) ((yaw%360.)*256/360), (byte) ((pitch%360.)*256/360), false));
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) ((yaw%360.)*256/360)));
    }

    public static void addJoinPacket(Player player){
        for(EntityPlayer npc:NPC){
            PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            DataWatcher watcher = npc.getDataWatcher();
            byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
            watcher.watch(10, b);
            connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), watcher, true));
            makeNPClookAtPlayer(npc, player);
        }
    }
    public static void addShutdownPacket(EntityPlayer npc){
        for(Player p:Bukkit.getOnlinePlayers()){
            PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));
        }
    }
    public static void setNPCHoldingItem(ItemStack item, EntityPlayer npc, Player p){
        PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutEntityEquipment(npc.getId(), 0, item));
    }
    public static void haveNPCSwing(EntityPlayer npc, Player p){
        PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutAnimation(npc, 0));

    }
    public static List<EntityPlayer> getNPCs(){
        return NPC;
    }
}
