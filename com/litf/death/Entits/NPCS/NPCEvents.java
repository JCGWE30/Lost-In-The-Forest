package com.litf.death.Entits.NPCS;

import com.litf.death.Items.Items;
import com.litf.death.Main;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NPCEvents implements Listener {
    public static List<EntityPlayer> constalooks = new ArrayList<>();
    public static HashMap<Player, Long> cooldown = new HashMap<>();
    public static boolean intalk = false;

    @EventHandler
    public static void lookat(PlayerMoveEvent e) {
        for (EntityPlayer npc : constalooks) {
            NPC.makeNPClookAtPlayer(npc, e.getPlayer());
        }
        Date date = new Date();
        if (!cooldown.containsKey(e.getPlayer())) {
            cooldown.put(e.getPlayer(), 0l);
        }
        if (cooldown.get(e.getPlayer()) + 3000 < date.getTime() && intalk) {
            if (Main.data.getConfig().get(e.getPlayer().getUniqueId().toString() + ".Undergrounddata.quests.1") == null) {
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Undergrounddata.quests.1", 0);
                Main.data.saveConfig();
            }
        }
    }

    @EventHandler
    public static void onClick(RightClickNPC e) {
        Date date = new Date();
        if (!cooldown.containsKey(e.getPlayer())) {
            cooldown.put(e.getPlayer(), 0l);
        }
        if (cooldown.get(e.getPlayer()) + 3000 < date.getTime()) {
            if (Main.data.getConfig().get(e.getPlayer().getUniqueId().toString() + ".Undergrounddata.quests.1") == null) {
                Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Undergrounddata.quests.1", 0);
                Main.data.saveConfig();
            }
            if (e.getNPC().getId() == NPC.NPCids.get(0)) {
                int i = Main.data.getConfig().getInt(e.getPlayer().getUniqueId().toString() + ".Undergrounddata.quests.1");
                String[] lines = {"Why hello there!", "Welcome to the underground.", "This place is very different from the forest your used to.",
                        "One difference you may notice quickly is that you can mine!", "You can use the minerals you mine to craft ores and other minerals",
                        "Here take this pickaxe and go do some mining! we can meet up later"};
                if(i==14){
                    e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "[Miner Joe] " + ChatColor.GREEN + "You mined some iron, sweet. I cant help you much more so head towards the mobs ahead and good luck!");
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
                }else
                if (i == 7) {
                    e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "[Miner Joe] " + ChatColor.GREEN + "Go mines some ores first, then we can chat");
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
                } else if(i<7) {
                    if (lines.length > i) {
                        e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "[Miner Joe] " + ChatColor.GREEN + lines[i]);
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
                        cooldown.put(e.getPlayer(), date.getTime());
                    } else {
                        NPC.setNPCHoldingItem(CraftItemStack.asNMSCopy(new ItemStack(Material.AIR, 1)), e.getNPC(), e.getPlayer());
                        NPC.haveNPCSwing(e.getNPC(), e.getPlayer());
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_PICKUP, 1f, 1f);
                        e.getPlayer().getInventory().addItem(Items.items.get(10));
                        e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "[Miner Joe] " + ChatColor.GREEN + "Oh almost forgot, make sure to talk with my friend Miner Sam while your over there");
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
                        cooldown.put(e.getPlayer(), date.getTime());
                    }
                    i++;
                    Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Undergrounddata.quests.1", i);
                    Main.data.saveConfig();
                }else if(i<13){
                    e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "[Miner Joe] " + ChatColor.GREEN + "Go mines some ores first, then we can chat");
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
                } else if(i>=14){

                }

            } else if (e.getNPC().getId() == NPC.NPCids.get(1)) {
                int i = Main.data.getConfig().getInt(e.getPlayer().getUniqueId().toString() + ".Undergrounddata.quests.1");
                String[] lines = {"Ah so sam must have sent you?", "Well these are the mines, a great place to be.", "The tunnel ahead leads you into the heart of the mines",
                        "Some ores can be broken with that pickaxe of yours while others are stronger", "Whatever the case, im glad to have you here in the mines!",
                        "So go out there and Mine!"};
                if (i < 7) {
                    e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "[Miner Sam] " + ChatColor.GREEN + "Talk to Miner Jeff first, then we can chat");
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
                } else {
                    try{
                        e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "[Miner Sam] " + ChatColor.GREEN + lines[i-7]);
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.VILLAGER_IDLE, 1f, 1f);
                        cooldown.put(e.getPlayer(), date.getTime());
                        i++;
                        Main.data.getConfig().set(e.getPlayer().getUniqueId().toString() + ".Undergrounddata.quests.1", i);
                        Main.data.saveConfig();
                    }catch(IndexOutOfBoundsException ignored){

                    }
                }
            }
        }
    }
}