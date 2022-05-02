package com.litf.death.Events;

import com.litf.death.Items.Misc;
import com.litf.death.Main;
import com.mysql.jdbc.Util;
import net.minecraft.server.v1_8_R3.*;
import org.apache.commons.lang.ObjectUtils;
import org.apache.logging.log4j.core.helpers.Assert;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Array;
import java.util.*;

public class UndegroundMineEvents implements Listener {
    static Plugin dapop;
    public UndegroundMineEvents(Plugin mn) {
        this.dapop=mn;
    }
    public static List<Block> blocks = new ArrayList<>();
    public static HashMap<Player, List<Object>> mines = new HashMap<>();
    public static HashMap<Player, List<BukkitTask>> fullb = new HashMap<>();
    public static Double miningpower = 0d;
    public static List<Long> btimes = Arrays.asList(
            20l,
            60l,
            120l
    );
    @EventHandler
    public static void initstartbreak(BlockDamageEvent e){
        if(e.getBlock()==null)
            return;
        Date dte = new Date();
            Set<Material> NullSet = null;
            Block b = e.getPlayer().getTargetBlock(NullSet, 4);
            e.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
            if (blocks.contains(b)) {
                List<Object> tm;
                if(mines.containsKey(e.getPlayer())){
                    tm = mines.get(e.getPlayer());
                    tm.set(1,e.getBlock().getLocation());
                }else{
                    tm=new ArrayList<>();
                    tm.add(0);
                    tm.add(e.getBlock().getLocation());
                }
                mines.put(e.getPlayer(), tm);
                if(e.getPlayer().getInventory().getItemInHand()!=null){
                    try {
                        switch (Objects.requireNonNull(UtilEvents.getId(e.getPlayer().getInventory().getItemInHand()))) {
                            case "CAVE_PICK":
                                miningpower = 1d;
                                break;
                            case "CAVE_IMPROV":
                                miningpower = 1.5;
                                break;
                            case "CAVE_FORCEDRE":
                                miningpower = 2.5;
                                break;
                            case "ADMIN_SWORD":
                                miningpower = 50d;
                                break;
                        }
                    }catch (NullPointerException ignored){

                    }
                }
                if(Main.data.getConfig().getInt(e.getPlayer().getUniqueId().toString() + ".Undergrounddata.quests.1")>12) {
                    switch (e.getBlock().getType()) {
                        case IRON_ORE:
                            if (miningpower >= 1) {
                                double a = Math.round((5/miningpower) * 100.0) / 100.0;
                                BukkitTask task = new BukkitRunnable(){
                                    @Override
                                    public void run(){
                                        List<Object> tm = mines.get(e.getPlayer());
                                        tm.set(0,(int)tm.get(0)+(new Double(miningpower)).intValue());
                                        if(((int)tm.get(0)+miningpower)>=btimes.get(0)){
                                            for(Player p:Bukkit.getOnlinePlayers()){
                                                if(p.getLocation().distance(e.getPlayer().getLocation())<50){
                                                PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                                                connection.sendPacket(new PacketPlayOutBlockBreakAnimation(0,new BlockPosition(b.getX(),b.getY(),b.getZ()),-1));
                                                }
                                            }
                                            breakastoredblock(b,e.getPlayer());
                                            this.cancel();
                                        }else{
                                        mines.put(e.getPlayer(),tm);

                                            for(Player p:Bukkit.getOnlinePlayers()) {
                                                if (p.getLocation().distance(e.getPlayer().getLocation()) < 50) {
                                                    PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                                                    connection.sendPacket(new PacketPlayOutBlockBreakAnimation(0, new BlockPosition(b.getX(), b.getY(), b.getZ()), (int) (Math.floor((int) tm.get(0) / (Math.floor(btimes.get(0) / 9))))));
                                                }
                                            }
                                        }
                                    }
                                }.runTaskTimer(dapop,0l,(new Double(a)).longValue());
                                List<BukkitTask> bt = new ArrayList<>();
                                if(fullb.containsKey(e.getPlayer()))
                                    bt=fullb.get(e.getPlayer());
                                bt.add(task);
                                fullb.put(e.getPlayer(),bt);
                            }
                            break;
                        case LAPIS_ORE:
                            if (miningpower >= 1.5) {
                                double a = Math.round((5/miningpower) * 100.0) / 100.0;
                                BukkitTask task = new BukkitRunnable(){
                                    @Override
                                    public void run(){
                                        List<Object> tm = mines.get(e.getPlayer());
                                        tm.set(0,(int)tm.get(0)+(new Double(miningpower)).intValue());
                                        if(((int)tm.get(0)+miningpower)>=btimes.get(1)){
                                            for(Player p:Bukkit.getOnlinePlayers()){
                                                if(p.getLocation().distance(e.getPlayer().getLocation())<50){
                                                    PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                                                    connection.sendPacket(new PacketPlayOutBlockBreakAnimation(0,new BlockPosition(b.getX(),b.getY(),b.getZ()),-1));
                                                }
                                            }
                                            breakastoredblock(b,e.getPlayer());
                                            this.cancel();
                                        }else{
                                            mines.put(e.getPlayer(),tm);

                                            for(Player p:Bukkit.getOnlinePlayers()) {
                                                if (p.getLocation().distance(e.getPlayer().getLocation()) < 50) {
                                                    PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                                                    connection.sendPacket(new PacketPlayOutBlockBreakAnimation(0, new BlockPosition(b.getX(), b.getY(), b.getZ()), (int) (Math.floor((int) tm.get(0) / (Math.floor(btimes.get(1) / 9))))));
                                                }
                                            }
                                        }
                                    }
                                }.runTaskTimer(dapop,0l,(new Double(a)).longValue());
                                List<BukkitTask> bt = new ArrayList<>();
                                if(fullb.containsKey(e.getPlayer()))
                                    bt=fullb.get(e.getPlayer());
                                bt.add(task);
                                fullb.put(e.getPlayer(),bt);
                            }
                            break;
                        case DIAMOND_ORE:
                            if (miningpower >= 3) {
                                double a = Math.round((5/miningpower) * 100.0) / 100.0;
                                BukkitTask task = new BukkitRunnable(){
                                    @Override
                                    public void run(){
                                        List<Object> tm = mines.get(e.getPlayer());
                                        tm.set(0,(int)tm.get(0)+(new Double(miningpower)).intValue());
                                        if(((int)tm.get(0)+miningpower)>=btimes.get(2)){
                                            for(Player p:Bukkit.getOnlinePlayers()){
                                                if(p.getLocation().distance(e.getPlayer().getLocation())<50){
                                                    PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                                                    connection.sendPacket(new PacketPlayOutBlockBreakAnimation(0,new BlockPosition(b.getX(),b.getY(),b.getZ()),-1));
                                                }
                                            }
                                            breakastoredblock(b,e.getPlayer());
                                            this.cancel();
                                        }else{
                                            mines.put(e.getPlayer(),tm);

                                            for(Player p:Bukkit.getOnlinePlayers()) {
                                                if (p.getLocation().distance(e.getPlayer().getLocation()) < 50) {
                                                    PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                                                    connection.sendPacket(new PacketPlayOutBlockBreakAnimation(0, new BlockPosition(b.getX(), b.getY(), b.getZ()), (int) (Math.floor((int) tm.get(0) / (Math.floor(btimes.get(2) / 9))))));
                                                }
                                            }
                                        }
                                    }
                                }.runTaskTimer(dapop,0l,(new Double(a)).longValue());
                                List<BukkitTask> bt = new ArrayList<>();
                                if(fullb.containsKey(e.getPlayer()))
                                    bt=fullb.get(e.getPlayer());
                                bt.add(task);
                                fullb.put(e.getPlayer(),bt);
                            }
                            break;
                    }
                }else{
                    e.getPlayer().sendMessage(ChatColor.RED+"Finish chatting with the miners before you can mine");
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.VILLAGER_NO, 1f,1f);
                    e.setCancelled(true);
                }
            }else{
                e.setCancelled(true);
            }
        }
        public static void stopmine(Player p,BlockPosition bp, PacketPlayInBlockDig.EnumPlayerDigType Epdt){
        if(Epdt.toString().equals("ABORT_DESTROY_BLOCK")){
            if(fullb.containsKey(p)){
                for(BukkitTask task:fullb.get(p)){
                    Bukkit.getScheduler().cancelTask(task.getTaskId());
                }
                fullb.remove(p);PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                connection.sendPacket(new PacketPlayOutBlockBreakAnimation(0,bp,-1));
                mines.remove(p);
            }
        }
        }
    public static void breakastoredblock(Block b,Player p){
        if(blocks.contains(b)){
            RecipeTierEvents.breakOre(p, b.getType());
            switch (b.getType()){
                case IRON_ORE:
                    b.getWorld().dropItemNaturally(b.getLocation(), Misc.items.get(15).clone());
                    break;
                case LAPIS_ORE:
                    b.getWorld().dropItemNaturally(b.getLocation(), Misc.items.get(16).clone());
                    break;
                case DIAMOND_ORE:
                    b.getWorld().dropItemNaturally(b.getLocation(), Misc.items.get(17).clone());
                    break;
            }
            b.breakNaturally(null);
            b.setType(Material.COBBLESTONE);
            dapop.getServer().getScheduler().scheduleSyncDelayedTask(dapop, new Runnable() {
                @Override
                public void run() {
                    Random rand = new Random();
                    int numbor = rand.nextInt(5);
                    if(numbor==0){
                        b.setType(Material.DIAMOND_ORE);
                    }else if(numbor<3){
                        b.setType(Material.LAPIS_ORE);
                    }else{
                        b.setType(Material.IRON_ORE);
                    }
                }
            }, 1000L);
        }
    }

    public static void initores(org.bukkit.World world){
        if(Main.serverdata.getConfig().get("Underground.orelocs")==null){
            Main.serverdata.getConfig().set("Underground.orelocs", Arrays.asList(1,1,1,1,1,1)) ;
            Main.serverdata.saveConfig();
        }
        List<Integer> locie = Main.serverdata.getConfig().getIntegerList("Underground.orelocs");
        Location loc = world.getBlockAt(locie.get(0),locie.get(1),locie.get(2)).getLocation();
        Location loc1 = world.getBlockAt(locie.get(3),locie.get(4),locie.get(5)).getLocation();
        List<List<Integer>> locs = new ArrayList<>();
        if (loc.getX() > loc1.getX()) {
            for (int x = loc1.getBlockX(); x < loc.getBlockX(); x++) {
                if (loc.getY() > loc1.getY()) {
                    for (int y = loc1.getBlockY(); y < loc.getBlockY(); y++) {
                        if (loc.getZ() > loc1.getZ()) {
                            for (int z = loc1.getBlockZ(); z < loc.getBlockZ(); z++) {
                                if (world.getBlockAt(x, y, z).getType() == Material.SPONGE) {
                                    locs.add(Arrays.asList(x, y, z));
                                }
                            }
                        } else {
                            for (int z = loc.getBlockZ(); z < loc1.getBlockZ(); z++) {
                                if (world.getBlockAt(x, y, z).getType() == Material.SPONGE) {
                                    locs.add(Arrays.asList(x, y, z));
                                }
                            }
                        }
                    }
                } else {
                    for (int y = loc.getBlockY(); y < loc1.getBlockY(); y++) {
                        if (loc.getZ() > loc1.getZ()) {
                            for (int z = loc1.getBlockZ(); z < loc.getBlockZ(); z++) {
                                if (world.getBlockAt(x, y, z).getType() == Material.SPONGE) {
                                    locs.add(Arrays.asList(x, y, z));
                                }
                            }
                        } else {
                            for (int z = loc.getBlockZ(); z < loc1.getBlockZ(); z++) {
                                if (world.getBlockAt(x, y, z).getType() == Material.SPONGE) {
                                    locs.add(Arrays.asList(x, y, z));
                                }
                            }
                        }
                    }
                }
            }
        }else{
            for (int x = loc.getBlockX(); x < loc1.getBlockX(); x++) {
                if (loc.getY() > loc1.getY()) {
                    for (int y = loc1.getBlockY(); y < loc.getBlockY(); y++) {
                        if (loc.getZ() > loc1.getZ()) {
                            for (int z = loc1.getBlockZ(); z < loc.getBlockZ(); z++) {
                                if (world.getBlockAt(x, y, z).getType() == Material.SPONGE) {
                                    locs.add(Arrays.asList(x, y, z));
                                }
                            }
                        } else {
                            for (int z = loc.getBlockZ(); z < loc1.getBlockZ(); z++) {
                                if (world.getBlockAt(x, y, z).getType() == Material.SPONGE) {
                                    locs.add(Arrays.asList(x, y, z));
                                }
                            }
                        }
                    }
                } else {
                    for (int y = loc.getBlockY(); y < loc1.getBlockY(); y++) {
                        if (loc.getZ() > loc1.getZ()) {
                            for (int z = loc1.getBlockZ(); z < loc.getBlockZ(); z++) {
                                if (world.getBlockAt(x, y, z).getType() == Material.SPONGE) {
                                    locs.add(Arrays.asList(x, y, z));
                                }
                            }
                        } else {
                            for (int z = loc.getBlockZ(); z < loc1.getBlockZ(); z++) {
                                if (world.getBlockAt(x, y, z).getType() == Material.SPONGE) {
                                    locs.add(Arrays.asList(x, y, z));
                                }
                            }
                        }
                    }
                }
            }
        }
        int i = 0;
        for(List<Integer> locoo:locs){
            Main.serverdata.getConfig().set("Underground.oredata."+i, locoo);
            blocks.add(world.getBlockAt(locoo.get(0),locoo.get(1),locoo.get(2)));
            i++;
        }
        Main.serverdata.getConfig().set("Underground.oredata.num",i);
        Main.serverdata.saveConfig();
        for(int a=0; a<Main.serverdata.getConfig().getInt("Underground.oredata.num");a++){
            List<Integer> ints = Main.serverdata.getConfig().getIntegerList("Underground.oredata."+a);
            Block b = Bukkit.getWorld(UUID.fromString(Main.serverdata.getConfig().getString("ServerInfo.MainWorld"))).getBlockAt(ints.get(0), ints.get(1), ints.get(2));
            Random rand = new Random();
            int numbor = rand.nextInt(5);
            if(numbor==0){
                b.setType(Material.DIAMOND_ORE);
            }else if(numbor<3){
                b.setType(Material.LAPIS_ORE);
            }else{
                b.setType(Material.IRON_ORE);
            }
        }
    }
}
