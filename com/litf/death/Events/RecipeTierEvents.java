package com.litf.death.Events;

import com.litf.death.Invens.CraftingMenu.CraftingMenuEvents;
import com.litf.death.Invens.CraftingMenu.CraftingMenuMain;
import com.litf.death.Main;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class RecipeTierEvents implements Listener {
    public static List<Integer> defints = Arrays.asList(
            5,
            10,
            25,
            30,
            45,
            500,
            600,
            700,
            800,
            50,
            25,
            50,
            10,
            15,
            1,
            1200,
            2500,
            5000,
            10000,
            10,
            1,
            1,
            45,
            5,
            20,
            10,
            15,
            30
    );
    public static List<String> strings = Arrays.asList(
            "Kill % more skiters to unlock",
            "Drop % more skiter scales to unlock",
            "Kill % more skiters to unlock",
            "Drop % more skiter scales to unlock",
            "Kill % more skiters to unlock",
            "Do % more damage to skiters to unlock",
            "Do % more damage to skiters to unlock",
            "Do % more damage to skiters to unlock",
            "Do % more damage to skiters to unlock",
            "Drop % more skiter scales to unlock",
            "Craft % more items to unlock",
            "Craft % more items to unlock",
            "Level up a destiny % more times to unlock",
            "Kill % more guards to unlock",
            "Craft a manipulation cube",
            "Do % more damage to forest guards to unlock",
            "Do % more damage to forest guards to unlock",
            "Do % more damage to forest guards to unlock",
            "Do % more damage to forest guards to unlock",
            "Drop % more guard tails to unlock",
            "Wear a piece of armor with a lvl 3 destiny or higher, and attack a mob",
            "Wear a set of armor with lvl 3 destinies or higher, and attack a mob",
            "Drop % more guard tails to unlock",
            "Kill % more forest tyrants to unlock",
            "Level up a destiny % more times to unlock",
            "Summon the forest tyrant % more times",
            "Mine % more iron to unlock",
            "Mine % more diamonds to unlock"
    );
    @EventHandler
    public static void kllmobs(EntityDeathEvent e){
        if(e.getEntity().getKiller()!=null){
            Player p = e.getEntity().getKiller();
            if(e.getEntity().getType()== EntityType.SPIDER) {
                if (Main.data.getConfig().get(p.getUniqueId().toString()+".Recipes")==null) {
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", defints);
                    Main.data.saveConfig();
                }
                List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes");
                if(ints.get(0)>0) {
                    ints.set(0, ints.get(0) - 1);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                }

                if((ints.get(0)==0)){
                    e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.LEVEL_UP,1f,1f);
                    e.getEntity().getKiller().sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                }
                if(ints.get(2)>0) {
                    ints.set(2, ints.get(2) - 1);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                }

                if((ints.get(2)==0)){
                    e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.LEVEL_UP,1f,1f);
                    e.getEntity().getKiller().sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                }
                if(ints.get(4)>0) {
                    ints.set(4, ints.get(4) - 1);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                }

                if((ints.get(4)==0)){
                    e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.LEVEL_UP,1f,1f);
                    e.getEntity().getKiller().sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                }
            }else if(e.getEntity().getType()==EntityType.OCELOT){
                if (Main.data.getConfig().get(p.getUniqueId().toString()+".Recipes")==null) {
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", defints);
                    Main.data.saveConfig();
                }
                List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes".toString());
                if(ints.get(13)>0) {
                    ints.set(13, ints.get(13) - 1);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                }

                if((ints.get(13)==0)){
                    e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.LEVEL_UP,1f,1f);
                    e.getEntity().getKiller().sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                }
            }else if(e.getEntity().getType()==EntityType.IRON_GOLEM){
                List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes".toString());
                if(ints.get(23)>0) {
                    ints.set(23, ints.get(23) - 1);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                }


                if((ints.get(23)==0)){
                    e.getEntity().getKiller().playSound(e.getEntity().getKiller().getLocation(), Sound.LEVEL_UP,1f,1f);
                    e.getEntity().getKiller().sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                }
            }
        }
    }
    public static void breakOre(Player p, Material b){
        confirmRecipes(p);
        List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes".toString());
        if(b==Material.IRON_ORE){
            if(ints.get(26)>0) {
                if(ints.get(26) - 1==0){
                    if(Main.data.getConfig().getInt(p.getUniqueId().toString() + ".Undergrounddata.quests.1")==13){
                        Main.data.getConfig().set(p.getUniqueId().toString() + ".Undergrounddata.quests.1",14);
                        Main.data.saveConfig();
                    }
                }
                ints.set(26, ints.get(26) - 1);
                Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                Main.data.saveConfig();
                if((ints.get(26)==0)){
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                    p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                }
            }
        }else if(b.equals(Material.DIAMOND_ORE)){
            if(ints.get(27)>0) {
                ints.set(27, ints.get(27) - 1);
                Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                Main.data.saveConfig();
            }
            if((ints.get(27)==0)){
                p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
            }
        }
    }
    public static void callSummon(Player p,EntityType ent){
        confirmRecipes(p);
        if(ent==EntityType.IRON_GOLEM){
            List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes".toString());
            if(ints.get(25)>0) {
                ints.set(25, ints.get(25) - 1);
                Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                Main.data.saveConfig();
            }
            if((ints.get(25)==0)){
                p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
            }
        }
    }
    public static void drop(String id, Player p){
        if (Main.data.getConfig().get(p.getUniqueId().toString()+".Recipes")==null) {
            Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", defints);
            Main.data.saveConfig();
        }
        List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes");
        if(id.equals("SCALE_BASE")) {
            if (ints.get(1) > 0) {
                ints.set(1, ints.get(1) - 1);
                Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                Main.data.saveConfig();
            }
            if((ints.get(1)==0)){
                p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
            }
            if (ints.get(3) > 0) {
                ints.set(3, ints.get(3) - 1);
                Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                Main.data.saveConfig();
            }
            if((ints.get(3)==0)){
                p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
            }
            if (ints.get(9) > 0) {
                ints.set(9, ints.get(9) - 1);
                Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                Main.data.saveConfig();
            }
            if((ints.get(9)==0)){
                p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
            }
        }else if(id.equals("GUARD_TAIL")){
            if (ints.get(19) > 0) {
                ints.set(19, ints.get(19) - 1);
                Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                Main.data.saveConfig();
            }
            if((ints.get(19)==0)){
                p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
            }
            if (ints.get(22) > 0) {
                ints.set(22, ints.get(22) - 1);
                Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                Main.data.saveConfig();
            }
            if((ints.get(22)==0)){
                p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
            }
        }
    }
    public static void Damage(Integer dmg,Player p, EntityType et){
        if (Main.data.getConfig().get(p.getUniqueId().toString()+".Recipes")==null) {
            Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", defints);
            Main.data.saveConfig();
        }
        int maxdestcount=0;
        if(p.getInventory().getHelmet()!=null){
            if(DestinyEvents.getDestiny(p.getInventory().getHelmet()).contains("3")||DestinyEvents.getDestiny(p.getInventory().getHelmet()).contains("4")||DestinyEvents.getDestiny(p.getInventory().getHelmet()).contains("5"))
                maxdestcount++;
        }
        if(p.getInventory().getChestplate()!=null){
            if(DestinyEvents.getDestiny(p.getInventory().getChestplate()).contains("3")||DestinyEvents.getDestiny(p.getInventory().getChestplate()).contains("4")||DestinyEvents.getDestiny(p.getInventory().getChestplate()).contains("5"))
                maxdestcount++;
        }
        if(p.getInventory().getLeggings()!=null){
            if(DestinyEvents.getDestiny(p.getInventory().getLeggings()).contains("3")||DestinyEvents.getDestiny(p.getInventory().getLeggings()).contains("4")||DestinyEvents.getDestiny(p.getInventory().getLeggings()).contains("5"))
                maxdestcount++;
        }
        if(p.getInventory().getBoots()!=null){
            if(DestinyEvents.getDestiny(p.getInventory().getBoots()).contains("3")||DestinyEvents.getDestiny(p.getInventory().getBoots()).contains("4")||DestinyEvents.getDestiny(p.getInventory().getBoots()).contains("5"))
                maxdestcount++;
        }
        List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes");
        if(maxdestcount>=1)
            ints.set(20, 0);
        if((ints.get(20)==0)){
            p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
            p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
        }
        if(maxdestcount==4)
            ints.set(21, 0);
        if((ints.get(21)==0)){
            p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
            p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
        }
        Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
        Main.data.saveConfig();
        if(et==EntityType.SPIDER){
            if (ints.get(5) > 0) {
                if (dmg > ints.get(5)) {
                    ints.set(5, 0);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                    p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                } else {
                    ints.set(5, ints.get(5) - dmg);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                }
            }
        if (ints.get(6) > 0) {
            if (dmg > ints.get(6)) {
                ints.set(6, 0);
                Main.data.getConfig().set(p.getUniqueId().toString() + ".Recipes", ints);
                Main.data.saveConfig();
                p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
            } else {
                ints.set(6, ints.get(6) - dmg);
                Main.data.getConfig().set(p.getUniqueId().toString() + ".Recipes", ints);
                Main.data.saveConfig();
            }
        }
        if (ints.get(7) > 0) {
            if (dmg > ints.get(7)) {
                ints.set(7, 0);
                Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                Main.data.saveConfig();
                p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
            } else {
                ints.set(7, ints.get(7) - dmg);
                Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                Main.data.saveConfig();
            }
        }
        if (ints.get(8) > 0) {
                if(dmg>ints.get(8)){
                    ints.set(8, 0);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                    p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                }else{
                    ints.set(8, ints.get(8) - dmg);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                }
            }
    }
        if(et==EntityType.OCELOT){
            if (ints.get(15) > 0) {
                if(dmg>ints.get(15)){
                    ints.set(15, 0);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                    p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                }else{
                    ints.set(15, ints.get(15) - dmg);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                }
            }
            if (ints.get(16) > 0) {
                if(dmg>ints.get(16)){
                    ints.set(16, 0);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                    p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                }else{
                    ints.set(16, ints.get(16) - dmg);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                }
            }
            if (ints.get(17) > 0) {
                if(dmg>ints.get(17)){
                    ints.set(17, 0);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                    p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                }else{
                    ints.set(17, ints.get(17) - dmg);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                }
            }
            if (ints.get(18) > 0) {
                if(dmg>ints.get(18)){
                    ints.set(18, 0);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                    p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
                    p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
                }else{
                    ints.set(18, ints.get(18) - dmg);
                    Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
                    Main.data.saveConfig();
                }
            }
        }}
    public static void callCraft(Player p, ItemStack item){
        if (Main.data.getConfig().get(p.getUniqueId().toString()+".Recipes")==null) {
            Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", defints);
            Main.data.saveConfig();
        }
        List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes");
        if(ints.get(10)>0) {
            ints.set(10, ints.get(10) - 1);
            Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
            Main.data.saveConfig();
        }
        if((ints.get(10)==0)){
            p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
            p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
        }
        if(ints.get(11)>0) {
            ints.set(11, ints.get(11) - 1);
            Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
            Main.data.saveConfig();
        }
        if((ints.get(11)==0)){
            p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
            p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
        }
        String id = " ";
        try {
            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
            NBTTagCompound comp = iten.getTag();
            NBTTagList lis = (NBTTagList) comp.get("comp");
            id = lis.get(0).getString("id");
        } catch (NullPointerException ex) {

        }
        if(id.equals("GUARD_CUBE")){
            ints.set(14, 0);
            Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
            Main.data.saveConfig();
        }
        if((ints.get(14)==0)){
            p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
            p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
        }
    }
    public static Boolean isUnlocked(List<ItemStack> stack, Player p){
        if(Main.data.getConfig().get(p.getUniqueId().toString()+".Recipes")==null)
            return false;
        List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes");
        if(ints.get(CraftingMenuEvents.recs.indexOf(stack))==0){
            return true;
        }
        return false;
    }
    public static void confirmRecipes(Player p){
        if (Main.data.getConfig().get(p.getUniqueId().toString()+".Recipes")==null) {
            Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", defints);
            Main.data.saveConfig();
        }
        List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes");
        if(ints.size()!=defints.size()) {
            for (int i = ints.size(); i < defints.size(); i++) {
                ints.add(null);
            }
            for (Integer i : ints) {
                if (i == null) {
                    ints.set(ints.indexOf(i), defints.get(ints.indexOf(i)));
                }
                Main.data.getConfig().set(p.getUniqueId().toString() + ".Recipes", ints);
                Main.data.saveConfig();
            }
        }
    }
    public static void callDestinyLevel(Player p){
        if (Main.data.getConfig().get(p.getUniqueId().toString()+".Recipes")==null) {
            Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", defints);
            Main.data.saveConfig();
        }
        List<Integer> ints = Main.data.getConfig().getIntegerList(p.getUniqueId().toString()+".Recipes");
        if(ints.get(12)>0) {
            ints.set(12, ints.get(12) - 1);
            Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
            Main.data.saveConfig();
        }
        if((ints.get(12)==0)){
            p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
            p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
        }
        if(ints.get(24)>0) {
            ints.set(24, ints.get(24) - 1);
            Main.data.getConfig().set(p.getUniqueId().toString()+".Recipes", ints);
            Main.data.saveConfig();
        }
        if((ints.get(24)==0)){
            p.playSound(p.getLocation(), Sound.LEVEL_UP,1f,1f);
            p.sendMessage(ChatColor.GREEN+"Recipe Unlocked! Check the recipe book to see what is it");
        }
    }
}
